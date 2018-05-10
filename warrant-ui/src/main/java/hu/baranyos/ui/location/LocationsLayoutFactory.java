package hu.baranyos.ui.location;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Location;
import hu.baranyos.service.location.LocationService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.LocationStringUtils;

@SpringView(name = LocationsLayoutFactory.NAME, ui = WarrantMainUI.class)
public class LocationsLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "locations";

    Grid<Location> locationGrid;
    ListDataProvider<Location> dataProvider;
    List<Location> locationList;

    @Autowired
    private LocationService locationService;

    public LocationsLayoutFactory() {
        super();
        this.setMargin(false);
        setSizeFull();

        locationGrid = new Grid<Location>();
        locationGrid.setSizeFull();

        locationGrid.addColumn(Location::getName).setCaption(LocationStringUtils.NAME.getString());
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        load();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(locationGrid);
    }

    private void load() {
        locationList = locationService.getAllLocation();
        dataProvider = DataProvider.ofCollection(locationList);
        locationGrid.setDataProvider(dataProvider);
    }

}
