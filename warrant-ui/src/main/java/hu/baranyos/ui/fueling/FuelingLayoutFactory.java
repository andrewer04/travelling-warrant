package hu.baranyos.ui.fueling;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.service.fueling.FuelingService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.FuelingStringUtils;

@SpringView(name = FuelingLayoutFactory.NAME, ui = WarrantMainUI.class)
public class FuelingLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "fueling";

    Grid<Fueling> fuelingGrid;
    ListDataProvider<Fueling> dataProvider;
    List<Fueling> fuelingList;

    @Autowired
    private FuelingService fuelingService;

    public FuelingLayoutFactory() {
        super();
        this.setMargin(false);
        setSizeFull();

        fuelingGrid = new Grid<Fueling>();
        fuelingGrid.setSizeFull();

        fuelingGrid.addColumn(Fueling::getDate).setCaption(FuelingStringUtils.DATE.getString());
        fuelingGrid.addColumn(Fueling::getVehicle)
                .setCaption(FuelingStringUtils.VEHICLE.getString());
        fuelingGrid.addColumn(Fueling::getAmount).setCaption(FuelingStringUtils.AMOUNT.getString());
        fuelingGrid.addColumn(Fueling::getUser).setCaption(FuelingStringUtils.USER.getString());
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        load();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(fuelingGrid);

    }

    private void load() {
        fuelingList = fuelingService.getAllFuelings();
        dataProvider = DataProvider.ofCollection(fuelingList);
        fuelingGrid.setDataProvider(dataProvider);
    }
}
