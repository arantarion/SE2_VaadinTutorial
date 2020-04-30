package org.bonn.se.model.DAO;

import org.bonn.se.model.objects.Entity.Booking;
import org.bonn.se.process.control.exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuchungDAO extends AbstractDAO {

    public static BuchungDAO dao = null;

    private BuchungDAO() {
    }

    public static BuchungDAO getInstance() {
        if (dao == null) {
            dao = new BuchungDAO();
        }
        return dao;
    }

    public boolean addBooking(Booking booking) throws DatabaseException {
        String sql = "insert into realm.booking values (default, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setDate(1, new java.sql.Date(booking.getAnreise().getTime()));
            statement.setDate(2, new java.sql.Date(booking.getAbreise().getTime()));
            statement.setString(3, booking.getIban());
            statement.setInt(4, booking.getNumber());
            statement.setString(5, booking.getUser().getLogin());
            statement.setDate(6, new java.sql.Date(booking.getDatumBuchung().getTime()));
            statement.setInt(7, booking.getHotel().getId());

            statement.executeUpdate();

            setBuchungsID(booking);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    private void setBuchungsID(Booking booking) {

        Statement statement = this.getStatement();
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT max(realm.booking.id) FROM realm.booking");
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        int currVal = 0;
        try {
            rs.next();
            currVal = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        booking.setId(currVal);


    }

    public void deleteBookingBy(int id) {
        Statement statement = this.getStatement();

        try {
            statement.executeQuery("DELETE FROM realm.booking WHERE realm.booking.id = \'" + id + "\';");
        } catch (SQLException ex) {
            //TODO
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
