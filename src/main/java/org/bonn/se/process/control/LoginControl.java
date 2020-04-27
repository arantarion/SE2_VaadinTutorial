package org.bonn.se.process.control;

import com.vaadin.ui.UI;
import org.bonn.se.process.controll.exceptions.DatabaseException;
import org.bonn.se.process.controll.exceptions.NoSuchUserOrPasswordException;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginControl {

    public static void checkAuthentication(String user, String pw) throws NoSuchUserOrPasswordException, DatabaseException, SQLException {

        Statement state = JDBCConnection.getInstance().getStatement();
        ResultSet set =  state.executeQuery("SELECT * FROM real.user WHERE login = " + user + " .. ");
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

    }
}
