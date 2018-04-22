package hu.baranyos.ui.login;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.server.Setter;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import hu.baranyos.model.entity.User;
import hu.baranyos.service.user.UserService;
import hu.baranyos.utils.Gender;
import hu.baranyos.utils.UserStringUtils;

@org.springframework.stereotype.Component
public class SignupFormFactory {

    @Autowired
    private UserService userService;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    private class SignupForm {
        private VerticalLayout root;
        private Panel panel;

        private TextField usernameField;
        private TextField passwordField;
        private TextField passwordAgainField;
        private TextField firstName;
        private TextField lastName;
        private TextField age;
        private RadioButtonGroup<String> gender;

        private Button saveButton;
        private Button cancelButton;

        private HorizontalLayout buttonLayout;

        Binder<User> binder;

        public SignupForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel(UserStringUtils.LOGIN.getString());
            panel.setSizeUndefined();

            usernameField = new TextField(UserStringUtils.USERNAME.getString());
            passwordField = new PasswordField(UserStringUtils.PASSWORD.getString());
            passwordAgainField = new PasswordField(UserStringUtils.PASSWORD_AGAIN.getString());
            firstName = new TextField(UserStringUtils.FIRST_NAME.getString());
            lastName = new TextField(UserStringUtils.LAST_NAME.getString());
            age = new TextField(UserStringUtils.AGE.getString());
            gender = new RadioButtonGroup<>(UserStringUtils.GENDER.getString());

            gender.setItems(Gender.MALE.getString(), Gender.FEMALE.getString());
            gender.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

            saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
            cancelButton = new Button(UserStringUtils.CANCEL_BUTTON.getString());

            buttonLayout = new HorizontalLayout(saveButton, cancelButton);

            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            saveButton.setEnabled(false);

            cancelButton.setStyleName(ValoTheme.BUTTON_DANGER);

            binder = new Binder<>();

            return this;
        }

        public Component createFormLayout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            final FormLayout signupLayout = new FormLayout();

            signupLayout.addComponent(firstName);
            signupLayout.addComponent(lastName);
            signupLayout.addComponent(age);
            signupLayout.addComponent(gender);
            signupLayout.addComponent(usernameField);
            signupLayout.addComponent(passwordField);
            signupLayout.addComponent(passwordAgainField);
            signupLayout.addComponent(buttonLayout);

            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);

            panel.setContent(signupLayout);

            return root;
        }

        public SignupForm bind() {
            binder.forField(firstName)
                    .asRequired(UserStringUtils.REQUIRED.getString())
                    .bind(User::getFirstName, User::setFirstName);

            binder.forField(lastName)
                    .asRequired(UserStringUtils.REQUIRED.getString())
                    .bind(User::getLastName, User::setLastName);

            binder.forField(usernameField)
                    .asRequired(UserStringUtils.REQUIRED.getString())
                    .bind(User::getUsername, User::setUsername);

            binder.forField(age)
                    .asRequired(UserStringUtils.REQUIRED.getString())
                    .withConverter(new StringToIntegerConverter(UserStringUtils.AGE_WARNING
                            .getString()))
                    .withValidator(new IntegerRangeValidator(UserStringUtils.AGE_RANGE_WARNING
                            .getString(), 10, 99))
                    .bind(User::getAge, User::setAge);

            binder.forField(gender)
                    .asRequired(UserStringUtils.REQUIRED.getString())
                    .bind(User::getGender, User::setGender);

            binder.forField(passwordField)
                    .asRequired()
                    .withValidator(new StringLengthValidator(UserStringUtils.PASSWORD_WARNING
                            .getString(), 5, null))
                    .bind(User::getPassword, User::setPassword);

            binder.forField(passwordAgainField)
                    .asRequired("Must confirm password")
                    .bind(User::getPassword, new Setter<User, String>() {
                        @Override
                        public void accept(final User user, final String password) {}
                    });

            binder.withValidator(Validator.from(new SerializablePredicate<User>() {
                @Override
                public boolean test(final User account) {
                    if (passwordField.isEmpty() || passwordAgainField.isEmpty()) {
                        return true;
                    } else {
                        return Objects
                                .equals(passwordField.getValue(), passwordAgainField.getValue());
                    }
                }
            }, "Entered password and confirmation password must match"));

            final Label validationStatus = new Label();
            binder.setStatusLabel(validationStatus);

            binder.setBean(new User());

            saveButton.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    userService.saveUser(binder.getBean());
                    UI.getCurrent().getPage().setLocation("/login");
                }
            });

            saveButton.setClickShortcut(KeyCode.ENTER);

            cancelButton.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    UI.getCurrent().getPage().setLocation("/login");
                }
            });

            binder.addStatusChangeListener(new StatusChangeListener() {
                @Override
                public void statusChange(final StatusChangeEvent event) {
                    saveButton.setEnabled(binder.isValid());
                }
            });

            return this;
        }
    }

    public Component createComponent() {
        return new SignupForm().init().bind().createFormLayout();
    }

}
