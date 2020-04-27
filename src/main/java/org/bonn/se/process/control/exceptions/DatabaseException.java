package org.bonn.se.process.control.exceptions;

public class DatabaseException extends Exception {

    private String reason = null;

    public DatabaseException() {
        super("There was an error with the database!");
    }

    public DatabaseException(String message) {
        super(message);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
