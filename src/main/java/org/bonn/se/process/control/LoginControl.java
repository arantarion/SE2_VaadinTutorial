package org.bonn.se.process.control;

import com.vaadin.ui.UI;
import org.bonn.se.process.controll.exceptions.NoSuchUserOrPasswordException;
import org.bonn.se.services.util.Views;

public class LoginControl {

    public static void checkAuthentication(String user, String pw) throws NoSuchUserOrPasswordException {

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

    }
}
