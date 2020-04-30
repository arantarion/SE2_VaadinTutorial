package org.bonn.se.model.objects.Entity;

import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.model.objects.dto.User;

import java.util.Date;

public class Booking {

    private int id;
    private Date anreise;
    private Date abreise;
    private Date datumBuchung;
    private String iban;
    private int number;
    private Hotel hotel;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAnreise() {
        return anreise;
    }

    public void setAnreise(Date anreise) {
        this.anreise = anreise;
    }

    public Date getAbreise() {
        return abreise;
    }

    public void setAbreise(Date abreise) {
        this.abreise = abreise;
    }

    public Date getDatumBuchung() {
        return datumBuchung;
    }

    public void setDatumBuchung(Date datumBuchung) {
        this.datumBuchung = datumBuchung;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
