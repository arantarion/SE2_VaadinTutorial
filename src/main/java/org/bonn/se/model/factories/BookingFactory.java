package org.bonn.se.model.factories;

import org.bonn.se.model.objects.Entity.Booking;
import org.bonn.se.model.objects.dto.BookingRequest;
import org.bonn.se.model.objects.dto.User;

import java.time.LocalDate;
import java.util.Date;

public class BookingFactory {
    public static Booking createBooking(BookingRequest request, User user) {

        Booking book = new Booking();

        book.setAbreise(request.getAbreise());
        book.setAnreise(request.getAnreise());
        book.setHotel(request.getHotel());
        book.setIban(request.getIBAN());
        book.setNumber(request.getNumber());

        book.setUser(user);

        book.setDatumBuchung(LocalDate.now());
        return book;

    }
}
