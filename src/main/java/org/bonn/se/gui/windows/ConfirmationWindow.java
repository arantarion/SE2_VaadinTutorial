package org.bonn.se.gui.windows;

import com.vaadin.ui.*;


public class ConfirmationWindow extends Window {

    public ConfirmationWindow( String text){

        super("Confirmation");
        center();

        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label(text));
        content.setMargin(true);
        setContent(content);

        Button buchungsButton = new Button("OK");
        buchungsButton.addClickListener(e -> {
            close();
        });

        content.addComponent(buchungsButton);
        content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);

    }

}
