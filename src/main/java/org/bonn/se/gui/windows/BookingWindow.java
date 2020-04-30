package org.bonn.se.gui.windows;


import com.vaadin.ui.*;
import org.bonn.se.model.objects.dto.BookingRequest;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.process.control.BookingProcess;
import org.bonn.se.process.control.exceptions.DatabaseException;

import java.time.LocalDate;


public class BookingWindow extends Window {

    public BookingWindow( final Hotel hotel){
        super("Buchung");
        center();

        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Buchung für Hotel: " + hotel.getName()));
        content.setMargin(true);
        setContent(content);

        final DateField dateAnreise = new DateField();
        content.addComponent(dateAnreise);
        dateAnreise.setCaption("Anreise:");
        dateAnreise.setDateFormat("yyyy-MM-dd");
        dateAnreise.setValue(LocalDate.now());

        final DateField dateAbreise = new DateField();
        content.addComponent(dateAbreise);
        dateAbreise.setCaption("Abreise:");
        dateAbreise.setDateFormat("yyyy-MM-dd");
        dateAbreise.setValue(LocalDate.now());

        final ComboBox<Integer> personNo = new ComboBox<>();
        personNo.setCaption("Anzahl Personen");
        content.addComponent(personNo);
        personNo.setItems(1, 2, 3);

        final TextField iban = new TextField();
        iban.setCaption("IBAN:");
        content.addComponent(iban);

        setClosable(true);

        Button buche = new Button("Buchen");
        buche.addClickListener(e -> {
            BookingRequest request = new BookingRequest();
            request.setAbreise(dateAbreise.getValue());
            request.setAbreise(dateAnreise.getValue());
            request.setIBAN(iban.getValue());
            request.setNumber(personNo.getValue());
            request.setHotel(hotel);

            try {
                BookingProcess.getInstance().createBooking(request);
            } catch (DatabaseException databaseException) {
                databaseException.printStackTrace();
            }

        } );

        content.addComponent(buche);
        content.setComponentAlignment(buche, Alignment.MIDDLE_CENTER);

    }

}
