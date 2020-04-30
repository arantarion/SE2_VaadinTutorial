package org.bonn.se.services.db;

import org.bonn.se.process.control.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection implements JDBCConnectionInterface {

    private static JDBCConnection connection = null;
    private final String login = "jvetmi2s";
    private final String password = "jvetmi2s";
    private final String URL = "jdbc:postgresql://dumbo.inf.h-brs.de/jvetmi2s";

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

    public void openConnection() throws DatabaseException {

        try {

            Properties probs = new Properties();
            probs.setProperty("user", this.login);
            probs.setProperty("password", this.password);

            this.conn = DriverManager.getConnection(this.URL, probs);

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler bei Zugriff auf die Datenbank. Ist eine sichere Verbindung vorhanden?");
        }
    }

    public Statement getStatement() throws DatabaseException {

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

    public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
        try {

            if (this.conn.isClosed()) {
                this.openConnection();
            }

            return this.conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
