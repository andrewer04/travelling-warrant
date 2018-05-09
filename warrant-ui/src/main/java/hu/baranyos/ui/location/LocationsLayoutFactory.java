package hu.baranyos.ui.location;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.ui.commons.WarrantMainUI;

@SpringView(name = LocationsLayoutFactory.NAME, ui = WarrantMainUI.class)
public class LocationsLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "location";

    @Override
    public void enter(final ViewChangeEvent event) {
        addComponent(new Label("This is the location layout"));
    }

}
