package hu.baranyos.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = SignupUI.PATH)
@Theme("valo")
public class SignupUI extends UI {
    public static final String PATH = "/signup";

    @Autowired
    private SignupFormFactory signupFormFactory;

    @Override
    protected void init(final VaadinRequest request) {
        setContent(signupFormFactory.createComponent());
    }
}
