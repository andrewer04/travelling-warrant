package hu.baranyos.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = WarrantMainUI.NAME)
@Title("TRAVELLING WARRANT")
@Theme("valo")
public class WarrantMainUI extends UI {

    public static final String NAME = "/ui";

    private final Panel changeTab = new Panel();

    @Autowired
    private WarrantLogoLayoutFactory warrantLogoLayoutFactory;

    @Autowired
    private WarrantMenuFactory warrantMenuFactory;

    @Override
    protected void init(final VaadinRequest request) {

        changeTab.setHeight("100");

        final VerticalLayout rootLayout = new VerticalLayout();

        rootLayout.setSizeFull();
        rootLayout.setMargin(true);

        final Panel contentPanel = new Panel();
        contentPanel.setWidth("75%");
        contentPanel.setHeight("100%");

        final Panel logoPanel = new Panel();
        logoPanel.setWidth("75%");
        logoPanel.setHeightUndefined();

        final HorizontalLayout uiLayout = new HorizontalLayout();
        uiLayout.setSizeFull();
        uiLayout.setMargin(true);

        final Component menu = warrantMenuFactory.createComponent();
        final Component logo = warrantLogoLayoutFactory.createComponent();

        uiLayout.addComponent(menu);
        uiLayout.addComponent(changeTab);

        uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
        uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);

        uiLayout.setExpandRatio(menu, 1);
        uiLayout.setExpandRatio(changeTab, 2);

        logoPanel.setContent(logo);
        contentPanel.setContent(uiLayout);

        rootLayout.addComponent(logoPanel);
        rootLayout.addComponent(contentPanel);
        rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
        rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
        rootLayout.setExpandRatio(contentPanel, 1);

        setContent(rootLayout);
    }

}
