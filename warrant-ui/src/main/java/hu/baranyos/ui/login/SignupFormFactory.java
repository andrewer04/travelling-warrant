package hu.baranyos.ui.login;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

import hu.baranyos.model.entity.User;
import hu.baranyos.service.user.UserService;
import hu.baranyos.utils.Gender;
import hu.baranyos.utils.UserStringUtils;

@Component
public class SignupFormFactory extends FormLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField passwordAgainField;
    private final TextField firstName;
    private final TextField lastName;
    private final TextField age;
    private final RadioButtonGroup<String> gender;

    private final Button saveButton;
    private final Button cancelButton;

    Binder<User> binder;

    public SignupFormFactory() {
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

        saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        saveButton.setEnabled(false);

        cancelButton.setStyleName(ValoTheme.BUTTON_DANGER);

        binder = new Binder<>();
    }

    public FormLayout createFormLayout() {
        this.addComponent(firstName);
        this.addComponent(lastName);
        this.addComponent(age);
        this.addComponent(gender);
        this.addComponent(usernameField);
        this.addComponent(passwordField);
        this.addComponent(passwordAgainField);
        this.addComponent(new HorizontalLayout(saveButton, cancelButton));
        bind();
        setSizeUndefined();
        this.setMargin(true);
        return this;
    }

    private void bind() {
        binder.forField(firstName)
                .asRequired(UserStringUtils.REQUIRED.getString())
                .bind(User::getFirstName, User::setFirstName);

        binder.forField(lastName)
                .asRequired(UserStringUtils.REQUIRED.getString())
                .bind(User::getLastName, User::setLastName);

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

        binder.withValidator(Validator.from(new SerializablePredicate<User>() {
            @Override
            public boolean test(final User account) {
                if (passwordField.isEmpty() || passwordAgainField.isEmpty()) {
                    return true;
                } else {
                    return Objects.equals(passwordField.getValue(), passwordAgainField.getValue());
                }
            }
        }, "Entered password and confirmation password must match"));

        binder.setBean(new User());

        saveButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                userService.saveUser(binder.getBean());
                UI.getCurrent().getPage().setLocation("/login");
            }
        });

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

    }

}
