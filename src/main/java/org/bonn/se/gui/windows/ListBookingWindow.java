package org.bonn.se.gui.windows;

import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.process.control.BookingProcess;

import java.util.List;

public class ListBookingWindow extends Window {

    private int currID;
    private List<BookingDetail> liste;

    public ListBookingWindow() {
        super("Liste aller Buchungen");
        center();
        VerticalLayout content = new VerticalLayout();

        Grid<Hotel> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        liste = BookingProcess.getInstance().getAllBookingsForUser();

        grid.removeAllColumns();
        grid.setCaption("");
        grid.setItems(liste);
        grid.addColumn(Hotel::getName).setCaption("Name");
        grid.addColumn(Hotel::getId).setCaption("ID");
        grid.addColumn(Hotel::getOrt).setCaption("Ort");
        grid.addColumn(Hotel::getDesc).setCaption("Beschreibung");

    }

}
