package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.gui.components.TopPanel;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.HotelSearch;
import org.bonn.se.services.util.Roles;

import java.util.List;

public class MainView extends VerticalLayout implements View {

    private int anzahl = 0;
    private Hotel selected = null;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    public void setUp(){

        this.addComponent(new TopPanel());
        this.addComponent( new Label("<hr />", ContentMode.HTML));

        //final VerticalLayout layout = new VerticalLayout(); kann hier rausgenommen werden \o/
        final HorizontalLayout horizon = new HorizontalLayout();

        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

        final Label label = new Label(user.getVorname() + ", gebe einen Ort ein:");
        Button button = new Button("Suche", FontAwesome.SEARCH);
        Button buchen = new Button("Buchen", FontAwesome.BOOK);
        final TextField textinput = new TextField();

        //Wenn die suche direkt aus dem Textfeld heraus kommen soll
        //textinput.addValueChangeListener(e -> updateList());

        //Leeres Label falls man das mal braucht
        //final Label empty = new Label("nbsp", ContentMode.HTML);

        horizon.addComponents(label, textinput, button);
        addComponent(horizon);

        //Setzt eine Komponente mittig in den Frame
        setComponentAlignment(horizon, Alignment.MIDDLE_CENTER);
        horizon.setComponentAlignment(label, Alignment.MIDDLE_CENTER);

        // Create a grid bound to the list
        Grid<Hotel> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);

        SingleSelect<Hotel> selection = grid.asSingleSelect();

        grid.addSelectionListener(event -> {
            this.selected = selection.getValue();
        });

        button.addClickListener(e -> {
            String ort = textinput.getValue();
            List<Hotel> liste = HotelSearch.getInstance().getHotelbyOrt(ort);

            if (ort.equals("")) {
                Notification.show(null, "Bitte Ort eingeben!", Notification.Type.WARNING_MESSAGE);
            }
            anzahl += 1;
            grid.removeAllColumns();
            grid.setCaption("Treffer für " + ort + " (Anzahl der Suchen: " + anzahl + ")");
            grid.setItems(liste);
            grid.addColumn(Hotel::getName).setCaption("Name");
            grid.addColumn(Hotel::getId).setCaption("ID");
            grid.addColumn(Hotel::getOrt).setCaption("Ort");
            grid.addColumn(Hotel::getDesc).setCaption("Beschreibung");

        });

        buchen.addClickListener(e -> {
            Notification.show(null, "Auswahl: " + this.selected.toString(), Notification.Type.WARNING_MESSAGE);
        });

        addComponent(grid);
        addComponent(buchen);
        setComponentAlignment(buchen, Alignment.MIDDLE_CENTER);

        //setContent(layout); ebenfalls nicht benötigt
    }

}
