package hu.baranyos.ui.travels;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.ui.commons.WarrantMainUI;

@SpringView(name = TravelsLayoutFactory.NAME, ui = WarrantMainUI.class)
public class TravelsLayoutFactory extends VerticalLayout implements View {

    public static final String NAME = "travels";

    @Override
    public void enter(final ViewChangeEvent event) {
        addComponent(new Label("This is the travels layout"));
    }
}
