package hu.baranyos.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.utils.UserStringUtils;

@SpringUI(path = SignupUI.PATH)
@Theme("valo")
public class SignupUI extends UI {
    public static final String PATH = "/signup";

    @Autowired
    private SignupFormFactory signupFormFactory;

    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setMargin(true);
        rootLayout.setHeight("100%");

        final Panel rootPanel = new Panel(UserStringUtils.LOGIN.getString());
        rootPanel.setSizeUndefined();
        rootPanel.setContent(signupFormFactory.createFormLayout());

        rootLayout.addComponent(rootPanel);
        rootLayout.setComponentAlignment(rootPanel, Alignment.MIDDLE_CENTER);

        setContent(rootLayout);
    }
}
