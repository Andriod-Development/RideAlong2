package com.ridealong.models;

/**
 * Created by HP on 5/20/2016.
 */
public class ServerRequest {

    private String operation;
    private User user;
    private DriverDetails driverDetails;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }
}
