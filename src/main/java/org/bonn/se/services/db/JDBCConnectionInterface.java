package org.bonn.se.services.db;

import org.bonn.se.process.control.exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.Statement;

public interface JDBCConnectionInterface {

    // Implement as Singleton

    void initConnection() throws DatabaseException;
    void openConnection() throws DatabaseException;
    Statement getStatement() throws DatabaseException;
    PreparedStatement getPreparedStatement(String sql) throws DatabaseException;
    void closeConnection();

}
