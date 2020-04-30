package org.bonn.se.gui.windows;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.model.objects.Entity.Booking;
import org.bonn.se.model.objects.dto.BookingDetail;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.process.control.BookingProcess;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.List;

public class ListBookingWindow extends Window {

    private int currID;
    private List<BookingDetail> liste;

    public ListBookingWindow() {
        super("Liste aller Buchungen");
        center();
        VerticalLayout layout = new VerticalLayout();

        Grid<BookingDetail> grid = new Grid<>(BookingDetail.class);
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        liste = BookingProcess.getInstance().getAllBookingsForUser();

        grid.setCaption("");
        grid.setItems(liste);
        //grid.addColumn(Hotel::getName).setCaption("Name");
        //grid.addColumn(Hotel::getId).setCaption("ID");
        //grid.addColumn(Hotel::getOrt).setCaption("Ort");
        //grid.addColumn(Hotel::getDesc).setCaption("Beschreibung");

        this.setSizeFull();

        layout.addComponent(grid);

        Button deleteB = new Button("Reise stornieren");
        deleteB.addClickListener(e -> {
            BookingProcess.getInstance().deleteBookingByID(currID);

            grid.removeAllColumns();

            liste = BookingProcess.getInstance().getAllBookingsForUser();
            grid.setItems(liste);
        });

        layout.addComponent(deleteB);
        layout.setComponentAlignment(deleteB, Alignment.MIDDLE_CENTER);

        grid.addItemClickListener(e -> {
            System.out.println(e.getItem());
            ListBookingWindow.this.currID = e.getItem().getId();
        });

        this.setContent(layout);

    }

}
