package hu.baranyos.ui.vehicle;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.service.vehicle.VehicleService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.UserStringUtils;
import hu.baranyos.utils.VehicleStringUtils;

@SpringView(name = NewVehicleLayoutFactory.NAME, ui = WarrantMainUI.class)
public class NewVehicleLayoutFactory extends FormLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "new_vehicle";

    @Autowired
    private VehicleService vehicleService;

    private final TextField name;
    private final TextField consumption;
    private final TextField licencePlateNumber;
    private final Button saveButton;
    private final Button clearButton;

    private final Binder<Vehicle> binder;

    private NewVehicleLayoutFactory() {
        super();
        setSpacing(false);
        setMargin(true);

        name = new TextField(VehicleStringUtils.NAME.getString());
        consumption = new TextField(VehicleStringUtils.CONSUMPTION.getString());
        licencePlateNumber = new TextField(VehicleStringUtils.LICENCE_PLATE_NUMBER.getString());
        saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
        clearButton = new Button(UserStringUtils.CLEAR_BUTTON.getString());

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
        this.addComponent(name);
        this.addComponent(licencePlateNumber);
        this.addComponent(consumption);
        this.addComponent(new HorizontalLayout(saveButton, clearButton));

    }

    private void bind() {
        binder.forField(name)
                .asRequired()
                .bind(Vehicle::getName, Vehicle::setName);

        binder.forField(consumption)
                .asRequired()
                .withConverter(new StringToDoubleConverter(VehicleStringUtils.CONSUMPTION_WARNING
                        .getString()))
                .bind(Vehicle::getConsumption, Vehicle::setConsumption);

        binder.forField(licencePlateNumber)
                .asRequired()
                .bind(Vehicle::getLicencePlateNumber, Vehicle::setLicencePlateNumber);

        binder.setBean(new Vehicle());

        saveButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                vehicleService.saveVehicle(binder.getBean());
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
    }

    private void clearFields() {
        name.clear();
        consumption.clear();
        licencePlateNumber.clear();
    }
}
