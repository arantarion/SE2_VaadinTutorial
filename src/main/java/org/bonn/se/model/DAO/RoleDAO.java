package org.bonn.se.model.DAO;

import org.bonn.se.model.objects.dto.Role;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleDAO {

    public static RoleDAO dao = null;

    private RoleDAO() {
    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(User user) {
        Statement statement = null;

        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException e) {
            return null;
        }

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM realm.user_to_rolle WHERE realm.user_to_rolle.login = \'" + user.getLogin() + "\' ");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) return null;

        List<Role> liste = new ArrayList<>();
        Role role = null;

        try {
            while (rs.next()) {
                role = new Role();
                role.setBezeichnung(rs.getString(2));
                liste.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;

    }

}
