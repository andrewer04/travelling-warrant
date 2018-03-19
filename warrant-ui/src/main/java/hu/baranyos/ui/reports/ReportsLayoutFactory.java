package hu.baranyos.ui.reports;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.ui.commons.WarrantMainUI;

@SpringView(name = ReportsLayoutFactory.NAME, ui = WarrantMainUI.class)
public class ReportsLayoutFactory extends VerticalLayout implements View {

    public static final String NAME = "reports";

    @Override
    public void enter(final ViewChangeEvent event) {
        addComponent(new Label("This is the reports layout"));
    }
}
