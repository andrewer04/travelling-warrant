package hu.baranyos.ui.login;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import hu.baranyos.utils.UserStringUtils;

@org.springframework.stereotype.Component
public class LoginFormFactory {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    private class LoginForm {
        private VerticalLayout root;
        private Panel panel;

        private TextField usernameField;
        private TextField passwordField;

        private Button loginButton;
        private Button signupButton;

        private HorizontalLayout buttonLayout;

        private LoginForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel(UserStringUtils.LOGIN.getString());
            panel.setSizeUndefined();

            usernameField = new TextField(UserStringUtils.USERNAME.getString());
            passwordField = new PasswordField(UserStringUtils.PASSWORD.getString());

            loginButton = new Button(UserStringUtils.LOGIN.getString());
            signupButton = new Button(UserStringUtils.SIGNUP_BUTTON.getString());

            buttonLayout = new HorizontalLayout(loginButton, signupButton);

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
                        final Authentication authenticated =
                                daoAuthenticationProvider.authenticate(auth);
                        SecurityContextHolder.getContext().setAuthentication(authenticated);

                        UI.getCurrent().getPage().setLocation("/ui");

                        usernameField.clear();
                        passwordField.clear();

                    } catch (final AuthenticationException e) {
                        Notification
                                .show(UserStringUtils.LOGIN_FAILED.getString(), Type.ERROR_MESSAGE);
                    }
                }
            });

            loginButton.setClickShortcut(KeyCode.ENTER);

            signupButton.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    UI.getCurrent().getPage().setLocation("/signup");
                }
            });

            return this;
        }

        public Component createFormLayout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            final FormLayout loginLayout = new FormLayout();

            loginLayout.addComponent(usernameField);
            loginLayout.addComponent(passwordField);
            loginLayout.addComponent(buttonLayout);
            loginLayout.setSizeUndefined();
            loginLayout.setMargin(true);

            panel.setContent(loginLayout);
            return root;
        }
    }

    public Component createComponent() {
        return new LoginForm().init().createFormLayout();
    }
}
