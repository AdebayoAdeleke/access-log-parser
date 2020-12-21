package com.ef;


import java.util.Date;


public final class LogData {

    private Date requestDate;
    private String ipAddress;
    private String request;
    private String status;
    private String userAgent;


    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }



    @Override
    public String toString() {
        return "LogData{" +
                "requestDate=" + requestDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", request='" + request + '\'' +
                ", status='" + status + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
