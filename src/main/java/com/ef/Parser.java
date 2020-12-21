package com.ef;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class Parser {

    private static String accessLogPath;
    private static String startDate;
    private static String duration;
    private static int threshold;


    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("No access log and arguments provided");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--accesslog")) {
                accessLogPath = StringUtils.substringAfter(args[i], "=");
            }
            if (args[i].startsWith("--startDate")) {
                startDate = StringUtils.substringAfter(args[i], "=");
            }
            if (args[i].startsWith("--duration")) {
                duration = StringUtils.substringAfter(args[i], "=");
            }
            if (args[i].startsWith("--threshold")) {
                threshold = NumberUtils.toInt(StringUtils.substringAfter(args[i], "="));
            }
        }

        AccessLogParser logParser = new AccessLogParser();

        try {
            logParser.parse(accessLogPath, startDate, duration, threshold);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}