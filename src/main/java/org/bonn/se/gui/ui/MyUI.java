package org.bonn.se.gui.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.bonn.se.gui.views.LoginView;
import org.bonn.se.gui.views.MainView;
import org.bonn.se.services.util.Views;

import javax.servlet.annotation.WebServlet;


@Theme("mytheme")
@Title("My First Web App")
@PreserveOnRefresh
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Navigator navi = new Navigator(this, this);

        navi.addView(Views.MAIN, MainView.class);
        navi.addView(Views.LOGIN, LoginView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
