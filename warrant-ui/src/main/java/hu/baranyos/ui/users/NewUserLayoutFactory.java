package hu.baranyos.ui.users;

import com.vaadin.data.Binder;
import com.vaadin.data.Validator;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

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
    private Button saveButton;
    private Button clearButton;

    Binder<User> binder;

    public static final String NAME = "new_user";

    private NewUserLayoutFactory() {
        setSpacing(false);
        this.setMargin(false);
        setCaption("Új felhasználó létrehozása");

        firstName = new TextField(UserStringUtils.FIRST_NAME.getString());
        lastName = new TextField(UserStringUtils.LAST_NAME.getString());
        age = new TextField(UserStringUtils.AGE.getString());
        gender = new RadioButtonGroup<>(UserStringUtils.GENDER.getString());
        password = new PasswordField(UserStringUtils.PASSWORD.getString());
        saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
        clearButton = new Button(UserStringUtils.CLEAR_BUTTON.getString());
        
        gender.setItems(Gender.MALE.getString(),Gender.FEMALE.getString());
        
        binder = new Binder<>();
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        addLayout();
    }

    private void addLayout() {
        binder.forField(firstName)
        .asRequired()
        .bind(User::getFirstName, User::setFirstName);

        binder.forField(lastName)
        .asRequired()
        .bind(User::getLastName, User::setLastName);
        
        binder.forField(age)
        .asRequired()
        .withConverter(new StringToIntegerConverter("Kérlek számot írj bele!"))
        .bind(User::getAge, User::setAge);
        
        binder.forField(gender)
        .asRequired()
        .bind(User::getGender, User::setGender);
        
        binder.forField(password)
        .asRequired()
        .bind(User::getPassword, User::setPassword);
        
        binder.setBean(new User());
        
        saveButton.setEnabled(false);
        saveButton.addClickListener(
                event -> registerNewUser(binder.getBean()));
        
        binder.addStatusChangeListener(event -> saveButton.setEnabled(binder.isValid()));

    }
}
