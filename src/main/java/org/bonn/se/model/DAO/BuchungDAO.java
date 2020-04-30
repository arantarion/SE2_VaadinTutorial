package org.bonn.se.model.DAO;

import org.bonn.se.model.objects.Entity.Booking;
import org.bonn.se.model.objects.dto.BookingDetail;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
            statement.setDate(1, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(booking.getAnreise().toString()).getTime()));
            statement.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(booking.getAbreise().toString()).getTime()));
            statement.setString(3, booking.getIban());
            statement.setInt(4, booking.getNumber());
            statement.setString(5, booking.getUser().getLogin());
            statement.setDate(6, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(booking.getDatumBuchung().toString()).getTime()));
            statement.setInt(7, booking.getHotel().getId());

            statement.executeUpdate();

            setBuchungsID(booking);
            return true;
        } catch (SQLException | ParseException e) {
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
            assert rs != null;
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
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public List<BookingDetail> getAllBookingsForUser(User user) {

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT realm.hotel.name, realm.booking.id, realm.booking.anreise, realm.booking.abreise, realm.booking.datumbuchung" +
                    "FROM realm.booking JOIN realm.hotel ON (realm.booking.hotelid = realm.hotel.id)" +
                    "WHERE realm.booking.userid = \'" + user.getLogin() + "\' ");
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) return null;

        List<BookingDetail> liste = new ArrayList<>();
        BookingDetail booking = null;

        try {
            while (rs.next()) {
                booking = new BookingDetail();
                booking.setHotel(rs.getString(1));
                booking.setId(rs.getInt(2));
                booking.setAnreise(rs.getDate(3));
                booking.setAbreise(rs.getDate(4));
                booking.setDatumBuchung(rs.getDate(5));

                liste.add(booking);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BuchungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;

    }
}
