package hu.baranyos.ui.vehicle;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Vehicle;
import hu.baranyos.service.vehicle.VehicleService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.VehicleStringUtils;

@SpringView(name = VehiclesLayoutFactory.NAME, ui = WarrantMainUI.class)
public class VehiclesLayoutFactory extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;

    public static final String NAME = "vehicles";

    Grid<Vehicle> vehicleGrid;
    ListDataProvider<Vehicle> dataProvider;
    List<Vehicle> vehicleList;

    @Autowired
    private VehicleService vehicleService;

    public VehiclesLayoutFactory() {
        super();
        this.setMargin(false);
        setSizeFull();

        vehicleGrid = new Grid<Vehicle>();
        vehicleGrid.setSizeFull();

        vehicleGrid.addColumn(Vehicle::getName).setCaption(VehicleStringUtils.NAME.getString());
        vehicleGrid.addColumn(Vehicle::getLicencePlateNumber)
                .setCaption(VehicleStringUtils.LICENCE_PLATE_NUMBER.getString());
        vehicleGrid.addColumn(Vehicle::getConsumption)
                .setCaption(VehicleStringUtils.CONSUMPTION.getString());
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        load();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(vehicleGrid);

    }

    private void load() {
        vehicleList = vehicleService.getAllVehicle();
        dataProvider = DataProvider.ofCollection(vehicleList);
        vehicleGrid.setDataProvider(dataProvider);
    }

}
