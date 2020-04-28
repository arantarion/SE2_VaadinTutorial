package org.bonn.se.gui.ui;

import com.vaadin.server.VaadinRequest;
import org.bonn.se.model.objects.dto.User;

public interface MyUIIterface {

    User getUser();
    void setUser(User user);
    void init(VaadinRequest vaadinRequest);
    MyUI getMyUI();

}
