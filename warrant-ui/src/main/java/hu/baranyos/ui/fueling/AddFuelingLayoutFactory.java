package hu.baranyos.ui.fueling;

import com.vaadin.data.Binder;
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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.service.fueling.FuelingService;
import hu.baranyos.service.vehicle.VehicleService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.FuelingStringUtils;
import hu.baranyos.utils.UserStringUtils;

@SpringView(name = AddFuelingLayoutFactory.NAME, ui = WarrantMainUI.class)
public class AddFuelingLayoutFactory extends FormLayout implements View {

    private static final long serialVersionUID = -8628736846386636047L;

    private final TextField amount;
    private final ComboBox<Vehicle> vehicle;
    private final Button saveButton;
    private final Button clearButton;

    @Autowired
    private FuelingService fuelingService;

    @Autowired
    private VehicleService vehicleService;

    Binder<Fueling> binder;

    public static final String NAME = "add_fueling";

    private AddFuelingLayoutFactory() {
        super();
        setSpacing(false);
        this.setMargin(true);

        amount = new TextField(FuelingStringUtils.AMOUNT.getString());
        vehicle = new ComboBox<>(FuelingStringUtils.VEHICLE.getString());
        saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
        clearButton = new Button(UserStringUtils.CLEAR_BUTTON.getString());
    }

    public void init() {
        vehicle.setItems(vehicleService.getAllVehicle());
        vehicle.setPlaceholder(FuelingStringUtils.VEHICLE_PLACEHOLDER.getString());
        vehicle.setItemCaptionGenerator(Vehicle::getName);

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
        this.addComponent(vehicle);
        this.addComponent(amount);
        this.addComponent(new HorizontalLayout(saveButton, clearButton));

    }

    private void bind() {
        binder.forField(vehicle)
                .asRequired()
                .bind(Fueling::getVehicle, Fueling::setVehicle);

        binder.forField(amount)
                .asRequired()
                .withConverter(new StringToIntegerConverter(FuelingStringUtils.AMOUNT_WARNING
                        .getString()))
                .bind(Fueling::getAmount, Fueling::setAmount);

        binder.setBean(new Fueling());

        saveButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                fuelingService.save(binder.getBean());
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
        vehicle.clear();
        amount.clear();
    }
}
