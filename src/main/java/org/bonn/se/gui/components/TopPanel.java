package org.bonn.se.gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.services.util.Roles;


public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        Label headLabel = new Label("Mein Hotel - <i> das Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        HorizontalLayout horLayout = new HorizontalLayout();
        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

        Label logLabel = new Label("Welcome: " + user.getVorname() + "!");
        logLabel.setSizeUndefined();

        horLayout.addComponent(logLabel);
        horLayout.setComponentAlignment(logLabel, Alignment.MIDDLE_CENTER);

        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menu", null);

        item1.addItem("Logout", FontAwesome.SIGN_OUT, null);
        item1.addItem("Cancel", FontAwesome.UNLINK, null);

        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);

    }
}
