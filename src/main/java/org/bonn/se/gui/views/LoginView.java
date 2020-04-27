package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.process.controll.exceptions.DatabaseException;
import org.bonn.se.process.controll.exceptions.NoSuchUserOrPasswordException;

import java.sql.SQLException;

public class LoginView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    private void setUp() {

        //Layout auf ges. Bildschirm ausweiten
        this.setSizeFull();

        final TextField userLogin = new TextField();
        userLogin.setCaption("UserID:");

        final PasswordField passwd = new PasswordField();
        passwd.setCaption("Passwort:");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(userLogin, passwd);

        Panel panel = new Panel("Bitte Login Daten angeben:");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);

        Button loginButton = new Button("Login", FontAwesome.SEND);
        layout.addComponent(loginButton);
        layout.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);

        //Panel auf Feldgröße anpassen
        panel.setSizeUndefined();

        loginButton.addClickListener(e -> {
            String login = userLogin.getValue();
            String password = passwd.getValue();

            try {

                LoginControl.checkAuthentication(login, password);

            } catch (NoSuchUserOrPasswordException noSuchUserOrPasswordException) {

                Notification.show("Dies ist keine gültige Kombination", Notification.Type.ERROR_MESSAGE);
                passwd.setValue("");

            } catch (DatabaseException | SQLException databaseException) {
                databaseException.printStackTrace();
            }

        });

    }
}
