package hu.baranyos.ui.users;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.ui.commons.WarrantMainUI;

@SpringView(name = UsersLayoutFactory.NAME, ui = WarrantMainUI.class)
public class UsersLayoutFactory extends VerticalLayout implements View {

    public static final String NAME = "users";

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        addLayout();
    }

    private void addLayout() {
        setMargin(true);

    }
}
