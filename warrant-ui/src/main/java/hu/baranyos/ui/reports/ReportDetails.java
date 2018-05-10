package hu.baranyos.ui.reports;

import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.Date;

import hu.baranyos.model.entity.Report;
import hu.baranyos.model.entity.Vehicle;

public class ReportDetails extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private Vehicle vehicle;

    private final TextField textField;
    private final Date date;
    private final DateField dateField;

    public ReportDetails(final Report report) {
        textField = new TextField();
        date = new Date();
        dateField = new DateField();

        this.addComponent(textField);
        this.addComponent(dateField);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
