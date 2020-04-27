package org.bonn.se.process.controll.exceptions;

public class DatabaseException extends Exception {
    public DatabaseException() {
        super("There was an error with the database!");
    }

    public DatabaseException(String message) {
        super(message);
    }

}
