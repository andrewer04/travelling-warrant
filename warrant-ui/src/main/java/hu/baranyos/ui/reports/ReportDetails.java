package hu.baranyos.ui.reports;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

import hu.baranyos.model.entity.Report;
import hu.baranyos.model.entity.User;
import hu.baranyos.utils.ReportStringUtils;

public class ReportDetails extends GridLayout {

    private static final long serialVersionUID = 1L;

    private final Label dateField;
    private final Label vehicleField;
    private final Label distanceSum;
    private final Label fuelingSum;
    private final Label kmCost;

    ListDataProvider<KeyValue> dataProvider;
    List<KeyValue> userKmList;
    List<KeyValue> userBalanceList;
    List<KeyValue> userFuelingsList;

    private final Grid<KeyValue> userKmGrid;
    private final Grid<KeyValue> userFuelingsGrid;
    private final Grid<KeyValue> userBalanceGrid;

    public ReportDetails(final Report report) {
        super();

        dateField = new Label(report.getDate().toString());
        vehicleField = new Label(report.getVehicle().toString());
        distanceSum = new Label(Integer.toString(report.getDistanceSum()));
        fuelingSum = new Label(Integer.toString(report.getFuelingSum()));
        kmCost = new Label(Integer.toString(report.getFuelingSum() / report.getDistanceSum()));

        dateField.setCaption(ReportStringUtils.DATE.getString());
        vehicleField.setCaption(ReportStringUtils.VEHICLE.getString());
        distanceSum.setCaption(ReportStringUtils.DISTANCE_SUM.getString());
        fuelingSum.setCaption(ReportStringUtils.FUELING_SUM.getString());
        kmCost.setCaption(ReportStringUtils.KM_COST.getString());

        userKmGrid = new Grid<>();
        userBalanceGrid = new Grid<>();
        userFuelingsGrid = new Grid<>();

        userKmList = getUserKmList(report);
        userBalanceList = getUserBalanceList(report);
        userFuelingsList = getUserFuelingList(report);

        userKmGrid.addColumn(KeyValue::getUser).setCaption(ReportStringUtils.USER.getString());
        userKmGrid.addColumn(KeyValue::getInteger)
                .setCaption(ReportStringUtils.USER_KM.getString());

        userBalanceGrid.addColumn(KeyValue::getUser).setCaption(ReportStringUtils.USER.getString());
        userBalanceGrid.addColumn(KeyValue::getInteger)
                .setCaption(ReportStringUtils.USER_BALANCE.getString());

        userFuelingsGrid.addColumn(KeyValue::getUser)
                .setCaption(ReportStringUtils.USER.getString());
        userFuelingsGrid.addColumn(KeyValue::getInteger)
                .setCaption(ReportStringUtils.USER_FUELINGS.getString());

        generateMatrixGrid(2, 2);

    }

    private List<KeyValue> getUserFuelingList(final Report report) {
        final List<KeyValue> list = new ArrayList<>();
        for (final User user : report.getUsers()) {
            list.add(new KeyValue(user, report.getUserFuelings().get(user)));
        }
        return list;
    }

    private List<KeyValue> getUserBalanceList(final Report report) {
        final List<KeyValue> list = new ArrayList<>();
        for (final User user : report.getUsers()) {
            list.add(new KeyValue(user, report.getUserBalance().get(user)));
        }
        return list;
    }

    private List<KeyValue> getUserKmList(final Report report) {
        final List<KeyValue> list = new ArrayList<>();
        for (final User user : report.getUsers()) {
            list.add(new KeyValue(user, report.getUserKm().get(user)));
        }
        return list;
    }

    private void generateMatrixGrid(final int rows, final int columns) {
        removeAllComponents();
        setRows(rows);
        setColumns(columns);

        dataProvider = DataProvider.ofCollection(userKmList);
        userKmGrid.setDataProvider(dataProvider);

        dataProvider = DataProvider.ofCollection(userFuelingsList);
        userFuelingsGrid.setDataProvider(dataProvider);

        dataProvider = DataProvider.ofCollection(userBalanceList);
        userBalanceGrid.setDataProvider(dataProvider);

        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(dateField);
        layout.addComponent(vehicleField);
        layout.addComponent(distanceSum);
        layout.addComponent(fuelingSum);
        layout.addComponent(kmCost);

        addComponent(layout, 0, 0);
        addComponent(userBalanceGrid, 0, 1);
        addComponent(userKmGrid, 1, 0);
        addComponent(userFuelingsGrid, 1, 1);
    }

    private class KeyValue {
        private User user;
        private Integer value;

        public KeyValue(final User user, final Integer value) {
            this.user = user;
            this.value = value;
        }

        public User getUser() {
            return user;
        }

        public void setUser(final User user) {
            this.user = user;
        }

        public Integer getInteger() {
            return value;
        }

        public void setInteger(final Integer integer) {
            value = integer;
        }

    }
}
