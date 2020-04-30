package org.bonn.se.model.DAO;

import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class AbstractDAO<E> {

    protected Statement getStatement(){
        Statement statement = null;

        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }

    protected PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
        PreparedStatement statement = null;

        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }

}
