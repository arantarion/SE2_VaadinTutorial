package org.bonn.se.gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.gui.windows.ListBookingWindow;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.services.util.Roles;

public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        Label headLabel = new Label("<h2>Mein Hotel - <i> das Reservierungssystem</i></h2>", ContentMode.HTML);
        headLabel.setSizeUndefined();

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        HorizontalLayout horLayout = new HorizontalLayout();
        //User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;
        if (user != null) {
            vorname = user.getVorname();
        }

        Label logLabel = new Label("Welcome: " + vorname + "!");
        logLabel.setSizeUndefined();

        horLayout.addComponent(logLabel);
        horLayout.setComponentAlignment(logLabel, Alignment.MIDDLE_CENTER);

        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menu", null);

        item1.addItem("Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                LoginControl.logoutUser();
            }
        });

        if (user.hasRole(Roles.POWER_USER)){

            item1.addItem("Cancel", FontAwesome.UNLINK, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    ListBookingWindow window = new ListBookingWindow();
                    UI.getCurrent().addWindow(window);
                }
            });
        }

        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);

    }
}
