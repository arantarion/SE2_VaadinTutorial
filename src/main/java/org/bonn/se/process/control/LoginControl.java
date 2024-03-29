package org.bonn.se.process.control;

import com.vaadin.ui.UI;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.process.control.exceptions.NoSuchUserOrPasswordException;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControl {

    public static void checkAuthentication(String user, String pw) throws NoSuchUserOrPasswordException, DatabaseException {

        ResultSet set = null;

        try {
            Statement state = JDBCConnection.getInstance().getStatement();
            set = state.executeQuery("SELECT * " +
                    "FROM realm.user " +
                    "WHERE realm.user.login = \'" + user + "\'" +
                    "AND realm.user.password = \'" + pw + "\'");

        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL Befehl");
        }

        User userDTO = null;

        try {
            if (set.next()) {

                userDTO = new User();
                userDTO.setLogin(set.getString(1));
                userDTO.setVorname(set.getString(3));

            } else {
                throw new NoSuchUserOrPasswordException();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        //VaadinSession session = UI.getCurrent().getSession();
        //session.setAttribute(Roles.CURRENT_USER, userDTO);

        ((MyUI) UI.getCurrent()).setUser(userDTO);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

    }

    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("");
        //UI.getCurrent().getSession().close();
    }

}
