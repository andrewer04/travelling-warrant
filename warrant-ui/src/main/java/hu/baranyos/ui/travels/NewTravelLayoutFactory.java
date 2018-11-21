package hu.baranyos.ui.travels;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Location;
import hu.baranyos.model.entity.Travel;
import hu.baranyos.model.entity.User;
import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.service.location.LocationService;
import hu.baranyos.service.travel.TravelService;
import hu.baranyos.service.user.UserService;
import hu.baranyos.service.vehicle.VehicleService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.TravelStringUtils;
import hu.baranyos.utils.UserStringUtils;

@SpringView(name = NewTravelLayoutFactory.NAME, ui = WarrantMainUI.class)
public class NewTravelLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "new_travel";

    @Autowired
    TravelService travelService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    LocationService locationService;

    @Autowired
    UserService userService;

    private final ComboBox<Vehicle> vehicles;
    private final ComboBox<Location> from;
    private final ComboBox<Location> to;
    private final TwinColSelect<User> addUser;
    private final TextField start;
    private final TextField end;
    private final Button saveButton;
    private final Button clearButton;

    private Binder<Travel> binder;
    // private Binder.BindingBuilder<Travel, Integer> endBindingBuilder;
    // private Binder.Binding<Travel, Integer> endBinder;

    private NewTravelLayoutFactory() {
        super();
        setSpacing(false);
        setMargin(true);

        vehicles = new ComboBox<Vehicle>(TravelStringUtils.VEHICLE.getString());
        from = new ComboBox<Location>(TravelStringUtils.START_LOCATION.getString());
        to = new ComboBox<Location>(TravelStringUtils.END_LOCATION.getString());
        addUser = new TwinColSelect<User>(TravelStringUtils.USERS.getString());
        start = new TextField(TravelStringUtils.START_KM.getString());
        end = new TextField(TravelStringUtils.END_KM.getString());

        saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
        clearButton = new Button(UserStringUtils.CLEAR_BUTTON.getString());
    }

    private void init() {
        vehicles.setItems(vehicleService.getAllVehicle());
        from.setItems(locationService.getAllLocation());
        to.setItems(locationService.getAllLocation());
        start.setEnabled(false);

        final List<User> travellers = userService.getAllUser();

        travellers.remove(userService.getUser(1));

        addUser.setItems(travellers);
        addUser.setRows(4);
        addUser.select(userService.getCurrentUser());

        saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        saveButton.setEnabled(false);

        clearButton.setStyleName(ValoTheme.BUTTON_DANGER);

        binder = new Binder<>();
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        init();
        bind();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(vehicles);
        this.addComponent(from);
        this.addComponent(to);
        this.addComponent(start);
        this.addComponent(end);
        this.addComponent(addUser);
        this.addComponent(new HorizontalLayout(saveButton, clearButton));

    }

    private void bind() {
        binder.forField(vehicles)
                .asRequired()
                .bind(Travel::getVehicle, Travel::setVehicle);
        binder.forField(from)
                .bind(Travel::getFrom, Travel::setFrom);
        binder.forField(to)
                .bind(Travel::getTo, Travel::setTo);
        binder.forField(start)
                .asRequired()
                .withConverter(new StringToIntegerConverter(""))
                .bind(Travel::getStart, Travel::setStart);

        binder.forField(end)
                .asRequired()
                .withConverter(new StringToIntegerConverter(""))
                .bind(Travel::getEnd, Travel::setEnd);

        binder.forField(addUser)
                .asRequired()
                .bind(Travel::getUsers, Travel::setUsers);

/*
 * endBindingBuilder = binder.forField(end) .asRequired() .withConverter(new
 * StringToIntegerConverter("")) .withValidator(new IntegerRangeValidator("", Integer
 * .parseInt(start.getValue()), 999999)); endBinder = endBindingBuilder.bind(Travel::getEnd,
 * Travel::setEnd);
 */

        binder.setBean(new Travel());

        saveButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                travelService.saveTravel(binder.getBean());
                vehicleService
                        .updateSpeedometer(vehicles.getValue(), Integer.parseInt(end.getValue()));
                clearFields();
            }
        });

        clearButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                clearFields();
            }
        });

        binder.addStatusChangeListener(new StatusChangeListener() {
            @Override
            public void statusChange(final StatusChangeEvent event) {
                saveButton.setEnabled(binder.isValid());
            }
        });

        vehicles.addValueChangeListener(new ValueChangeListener<Vehicle>() {
            @Override
            public void valueChange(final ValueChangeEvent<Vehicle> event) {
                if (vehicles.getValue() == null) {
                    start.setValue("");
                } else {
                    start.setValue(Integer.toString(vehicles.getValue().getSpeedometer()));
                    end.setValue(Integer.toString(vehicles.getValue().getSpeedometer() + 1));
                }

                // endBinder.validate();
            }
        });

    }

    private void clearFields() {
        vehicles.clear();
        from.clear();
        to.clear();
        start.clear();
        end.clear();
        addUser.clear();
    }
}
