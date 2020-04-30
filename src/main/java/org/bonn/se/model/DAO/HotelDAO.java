package org.bonn.se.model.DAO;

import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HotelDAO extends AbstractDAO {

    public static HotelDAO dao = null;

    private HotelDAO() {
    }

    public static HotelDAO getInstance() {
        if (dao == null) {
            dao = new HotelDAO();
        }
        return dao;
    }

    public List<Hotel> getHotelbyLocation(String ort) {

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM realm.hotel WHERE realm.hotel.ort = \'" + ort + "\' ");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) return null;

        List<Hotel> liste = new ArrayList<>();
        Hotel hotel = null;

        try {
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt(1));
                hotel.setName(rs.getString(2));
                hotel.setOrt(rs.getString(3));
                hotel.setDesc(rs.getString(4));
                liste.add(hotel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;

    }


}
