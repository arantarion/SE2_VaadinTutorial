package org.bonn.se.services.db;

import org.bonn.se.process.controll.exceptions.DatabaseException;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {

    private static JDBCConnection connection = null;
    private final String login = "jvetmi2s";
    private final String password = "jvetmi2s";
    private final String URL = "jdbc:postgresql://dumbo.inf.h-brs.de/jvetmi2s";
    private String Test = "Anton hat einen big PP";
    private Connection conn;

    private JDBCConnection() throws DatabaseException {
        this.initConnection();
    }

    public static JDBCConnection getInstance() throws DatabaseException {
        if (connection == null) {
            connection = new JDBCConnection();
        }
        return connection;
    }

    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, e);
            throw new DatabaseException();
        }
        this.openConnection();
    }

    public void openConnection() {

        Properties probs = new Properties();
        probs.setProperty("user", "jvetmi2s");
        probs.setProperty("password", "jvetmi2s");

        try {
            this.conn = DriverManager.getConnection(this.URL, probs);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Statement getStatement() {

        try {

            if (this.conn.isClosed()) {
                this.openConnection();
            }

            return this.conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void closeConnection() throws SQLException {
        this.conn.close();
    }

}
