package hu.baranyos.ui.users;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import hu.baranyos.model.entity.User;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.Gender;
import hu.baranyos.utils.UserStringUtils;

@SpringView(name = NewUserLayoutFactory.NAME, ui = WarrantMainUI.class)
public class NewUserLayoutFactory extends FormLayout implements View {

    private final TextField firstName;
    private final TextField lastName;
    private final TextField age;
    private final RadioButtonGroup<String> gender;
    private final PasswordField password;
    private final Button saveButton;
    private final Button clearButton;

    Binder<User> binder;

    public static final String NAME = "new_user";

    private NewUserLayoutFactory() {
        super();
        setSpacing(false);
        this.setMargin(true);
        setCaption("Új felhasználó létrehozása");

        firstName = new TextField(UserStringUtils.FIRST_NAME.getString());
        lastName = new TextField(UserStringUtils.LAST_NAME.getString());
        age = new TextField(UserStringUtils.AGE.getString());
        gender = new RadioButtonGroup<>(UserStringUtils.GENDER.getString());
        password = new PasswordField(UserStringUtils.PASSWORD.getString());
        saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
        clearButton = new Button(UserStringUtils.CLEAR_BUTTON.getString());

        gender.setItems(Gender.MALE.getString(), Gender.FEMALE.getString());
        gender.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

        saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        saveButton.setEnabled(false);

        clearButton.setStyleName(ValoTheme.BUTTON_DANGER);

        binder = new Binder<>();
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        bind();
        addLayout();
    }

    private void addLayout() {
        addComponentAsFirst(firstName);
        this.addComponent(lastName);
        this.addComponent(age);
        this.addComponent(gender);
        this.addComponent(password);
        this.addComponent(firstName);
        this.addComponent(new HorizontalLayout(saveButton, clearButton));

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

        binder.forField(password)
                .asRequired()
                .withValidator(new StringLengthValidator(UserStringUtils.PASSWORD_WARNING
                        .getString(), 5, null))
                .bind(User::getPassword, User::setPassword);

        binder.setBean(new User());

        /*
         * saveButton.addClickListener( event -> registerNewUser(binder.getBean()));
         */

        binder.addStatusChangeListener(new StatusChangeListener() {
            @Override
            public void statusChange(final StatusChangeEvent event) {
                saveButton.setEnabled(binder.isValid());
            }
        });

    }
}
