package com.ef;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;

/**
 *The parser for the access log provided.
 *
 */
public class AccessLogParser {


    private File accessLog;
    private Connection connection;


    /**
     * Reads the provided log file and loads the request data into the database
     * @param filePath the file path of the log to be parsed
     * @param startDate the start date
     * @param duration the duration of the requests
     * @param threshold the minimum number of requests
     * @throws IOException if error occurs reading the file
     * @throws SQLException if error occurs inserting into the database
     * @throws ParseException if error occurs parsing the date
     */
    public void parse(String filePath, String startDate, String duration, int threshold) throws IOException, SQLException, ParseException {
        if (filePath == null) {
            System.out.println("No access log provided");
            System.out.println("Trying to read log database if it already exists");
            findRequests(startDate, duration, threshold, DatabaseConnection.getConnection());
            return;
        }
        accessLog = new File(filePath);
        FileReader fileReader = new FileReader(accessLog);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String log;
        LogData logData = new LogData();
        connection = DatabaseConnection.getConnection();
        String insertQuery = "INSERT INTO all_log_data VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);

        System.out.println("Loading log data into MySQL Database...\n");

        while ((log = bufferedReader.readLine()) != null) {
            String[] logArray = StringUtils.split(log, "|");
            logData.setRequestDate(DateUtils.parseLogDate(logArray[0]));
            logData.setIpAddress(logArray[1]);
            logData.setRequest(logArray[2]);
            logData.setStatus(logArray[3]);
            logData.setUserAgent(logArray[4]);
            saveToDatabase(logData, statement);
        }

        System.out.println("Finished loading log data into database\n");

        if (statement != null) {
            statement.close();
        }

        findRequests(startDate, duration, threshold, connection);

        if (connection != null) {
            connection.close();
        }
    }


    /**
     * Finds requests made by IPs starting from a specified with the given duration.
     * The requests that are found are loaded into MySQL table
     * @param startDate the starting date
     * @param duration the duration
     * @param threshold the minimum number of requests
     * @param connection the database connection
     * @throws ParseException if error occurs parsing the date
     * @throws SQLException if error occurs querying the database
     */
    private void findRequests(String startDate, String duration, int threshold, Connection connection) throws ParseException, SQLException {

        if(startDate==null){
            throw new IllegalArgumentException("No start date provided");
        }

        if(duration==null){
            throw new IllegalArgumentException("No duration provided");
        }

        Date fromDate = DateUtils.parseDateArgument(startDate);
        Date toDate = DateUtils.getDateAfterDuration(startDate, duration);

        String selectQuery = "SELECT ip_address, num_of_request FROM (SELECT ip_address, COUNT(*) AS num_of_request FROM all_log_data WHERE request_date>=? AND request_date<=? GROUP BY ip_address) AS t WHERE t.num_of_request>=?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setTimestamp(1, new Timestamp(fromDate.getTime()));
        statement.setTimestamp(2, new Timestamp(toDate.getTime()));
        statement.setInt(3, threshold);
        ResultSet result = statement.executeQuery();

        System.out.println("Requests from " + fromDate.toString() + " to " + toDate.toString() + " and threshold of " + threshold+":\n");

        String table = duration + "_log_data";

        String insertQuery = "INSERT INTO " + table + " VALUES (?,?,?)";
        statement = connection.prepareStatement(insertQuery);
        while (result.next()) {
            String ipAddress = result.getString("ip_address");
            int numRequest = result.getInt("num_of_request");
            System.out.println("IP Address: " + ipAddress + ", Number of Request: " + numRequest);
            statement.setString(1, ipAddress);
            statement.setInt(2, numRequest);
            statement.setString(3, "");
            statement.executeUpdate();

        }
    }

    /**
     * Saves the given log data to the database
     * @param logData the log data
     * @param statement the statement to execute
     * @throws SQLException if error occurs storing data
     */
    private void saveToDatabase(LogData logData, PreparedStatement statement) throws SQLException {

        statement.setTimestamp(1, new Timestamp(logData.getRequestDate().getTime()));
        statement.setString(2, logData.getIpAddress());
        statement.setString(3, logData.getRequest());
        statement.setString(4, logData.getStatus());
        statement.setString(5, logData.getUserAgent());
        statement.executeUpdate();
    }
}
