package hu.baranyos.ui.login;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import hu.baranyos.utils.UserStringUtils;

@Component
public class LoginFormFactory extends FormLayout {

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    private final TextField usernameField;
    private final TextField passwordField;

    private final Button loginButton;
    private final Button signupButton;

    public LoginFormFactory() {
        usernameField = new TextField(UserStringUtils.USERNAME.getString());
        passwordField = new PasswordField(UserStringUtils.PASSWORD.getString());

        loginButton = new Button(UserStringUtils.LOGIN.getString());
        signupButton = new Button(UserStringUtils.SIGNUP_BUTTON.getString());

        loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        loginButton.setEnabled(true);

        signupButton.setStyleName(ValoTheme.BUTTON_DANGER);

        loginButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                try {

                    final Authentication auth =
                            new UsernamePasswordAuthenticationToken(usernameField
                                    .getValue(), passwordField.getValue());
                    SecurityContextHolder.getContext()
                            .setAuthentication(authenticationProvider.authenticate(auth));

                } catch (final AuthenticationException e) {
                    Notification.show(UserStringUtils.LOGIN_FAILED.getString(), Type.ERROR_MESSAGE);
                }

            }

        });

        signupButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                UI.getCurrent().getPage().setLocation("/signup");

            }
        });
    }

    public FormLayout createFormLayout() {
        this.addComponent(usernameField);
        this.addComponent(passwordField);
        this.addComponent(new HorizontalLayout(loginButton, signupButton));
        setSizeUndefined();
        this.setMargin(true);
        return this;
    }

}
