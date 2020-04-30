package org.bonn.se.process.control;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.gui.windows.ConfirmationWindow;
import org.bonn.se.model.DAO.BuchungDAO;
import org.bonn.se.model.factories.BookingFactory;
import org.bonn.se.model.objects.Entity.Booking;
import org.bonn.se.model.objects.dto.BookingDetail;
import org.bonn.se.model.objects.dto.BookingRequest;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;

import java.util.List;

public class BookingProcess {

    private static BookingProcess process;

    private BookingProcess() {
    }

    public static BookingProcess getInstance() {
        if (process == null) {
            process = new BookingProcess();
        }
        return process;
    }

    public void createBooking(BookingRequest request, Window window) throws DatabaseException {

        User user = ((MyUI) UI.getCurrent()).getUser();

        Booking booking = BookingFactory.createBooking(request, user);

        boolean result = BuchungDAO.getInstance().addBooking(booking);

        if (result) {
            window.close();
            UI.getCurrent().addWindow(new ConfirmationWindow("Buchung erfolgreich! ID: " + booking.getId()));
        } else {
            UI.getCurrent().addWindow(new ConfirmationWindow("Es ist ein Fehler aufgetreten!"));
        }

    }

    public void deleteBookingByID(int id) {
        BuchungDAO.getInstance().deleteBookingBy(id);
        UI.getCurrent().addWindow(new ConfirmationWindow("Die Reise wurde stoniert!"));
    }

    public List<BookingDetail> getAllBookingsForUser() {

        final User user = ((MyUI) UI.getCurrent()).getUser();
        return BuchungDAO.getInstance().getAllBookingsForUser(user);

    }
}
