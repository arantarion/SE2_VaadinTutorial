package org.bonn.se.process.control;

import org.bonn.se.process.controll.exceptions.DatabaseException;
import org.bonn.se.process.controll.exceptions.NoSuchUserOrPasswordException;
import org.bonn.se.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {

    public static void checkAuthentication(String user, String pw) throws NoSuchUserOrPasswordException {

        ResultSet set = null;

        try {
            Statement state = JDBCConnection.getInstance().getStatement();
            set =  state.executeQuery("SELECT * " +
                    "FROM realm.user " +
                    "WHERE realm.user.login = \'" + user + "\'" +
                    "AND realm.user.password = \'" + pw + "\'");

        } catch (SQLException | DatabaseException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
