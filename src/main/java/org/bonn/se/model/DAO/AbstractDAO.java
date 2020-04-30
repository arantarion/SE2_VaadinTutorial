package org.bonn.se.model.DAO;

import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

import java.sql.Statement;

public class AbstractDAO {

    protected Statement getStatement(){
        Statement statement = null;

        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException e) {
            return null;
        }
        return statement;
    }

}
