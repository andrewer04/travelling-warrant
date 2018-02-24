package hu.baranyos.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = WarrantMainUI.NAME)
@Title("TRAVELLING WARRANT")
@Theme("valo")
public class WarrantMainUI extends UI {

    public static final String NAME = "/ui";

    @Override
    protected void init(final VaadinRequest request) {

        final VerticalLayout rootLayout = new VerticalLayout();

        rootLayout.addComponent(new Label("TEST"));

        setContent(rootLayout);
    }

}
