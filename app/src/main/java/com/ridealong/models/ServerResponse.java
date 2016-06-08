package com.ridealong.models;

/**
 * Created by HP on 5/20/2016.
 */
public class ServerResponse {

    private String result;
    private String message;
    private User user;
    private DriverDetails driverDetails;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public DriverDetails getDriverDetails() {
        return driverDetails;
    }
}
