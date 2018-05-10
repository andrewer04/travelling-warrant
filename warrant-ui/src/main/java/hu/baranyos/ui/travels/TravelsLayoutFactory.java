package hu.baranyos.ui.travels;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Travel;
import hu.baranyos.service.travel.TravelService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.TravelStringUtils;

@SpringView(name = TravelsLayoutFactory.NAME, ui = WarrantMainUI.class)
public class TravelsLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "travels";

    Grid<Travel> travelGrid;
    ListDataProvider<Travel> dataProvider;
    List<Travel> travelList;

    @Autowired
    private TravelService travelService;

    public TravelsLayoutFactory() {
        super();
        this.setMargin(false);
        setSizeFull();

        travelGrid = new Grid<Travel>();
        travelGrid.setSizeFull();

        travelGrid.addColumn(Travel::getDate).setCaption(TravelStringUtils.DATE.getString());
        travelGrid.addColumn(Travel::getVehicle).setCaption(TravelStringUtils.VEHICLE.getString());
        travelGrid.addColumn(Travel::getUsers).setCaption(TravelStringUtils.USERS.getString());
        travelGrid.addColumn(Travel::getDistance)
                .setCaption(TravelStringUtils.DISTANCE.getString());
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        load();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(travelGrid);
    }

    private void load() {
        travelList = travelService.getAllTravel();
        dataProvider = DataProvider.ofCollection(travelList);
        travelGrid.setDataProvider(dataProvider);
    }
}
