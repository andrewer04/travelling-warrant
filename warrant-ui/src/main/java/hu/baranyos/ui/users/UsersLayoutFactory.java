package hu.baranyos.ui.users;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import hu.baranyos.model.entity.User;
import hu.baranyos.service.user.UserService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.UserStringUtils;

@SpringView(name = UsersLayoutFactory.NAME, ui = WarrantMainUI.class)
@Secured("ROLE_ADMIN")
public class UsersLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "users";

    Grid<User> userGrid;
    ListDataProvider<User> dataProvider;
    List<User> userList;

    @Autowired
    private UserService userService;

    public UsersLayoutFactory() {
        super();
        this.setMargin(false);
        setSizeFull();

        userGrid = new Grid<>();
        userGrid.setSizeFull();

        userGrid.addColumn(User::getFirstName).setCaption(UserStringUtils.FIRST_NAME.getString());
        userGrid.addColumn(User::getLastName).setCaption(UserStringUtils.LAST_NAME.getString());
        userGrid.addColumn(User::getAge).setCaption(UserStringUtils.AGE.getString());
        userGrid.addColumn(User::getGender).setCaption(UserStringUtils.GENDER.getString());

    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        loadUsers();
        addLayout();
    }

    private void loadUsers() {
        userList = userService.getAllUser();
        userList.remove(userService.getCurrentUser());
        dataProvider = DataProvider.ofCollection(userList);
        userGrid.setDataProvider(dataProvider);
    }

    private void addLayout() {
        this.addComponent(userGrid);
    }
}
