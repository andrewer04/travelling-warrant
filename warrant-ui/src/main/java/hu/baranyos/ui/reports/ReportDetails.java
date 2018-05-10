package hu.baranyos.ui.reports;

import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import java.util.Date;

public class ReportDetails extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private final TextField textField;
    private final Date date;
    private final DateField dateField;

    public ReportDetails() {
        textField = new TextField();
        date = new Date();
        dateField = new DateField();

        this.addComponent(textField);
        this.addComponent(dateField);
    }

}
