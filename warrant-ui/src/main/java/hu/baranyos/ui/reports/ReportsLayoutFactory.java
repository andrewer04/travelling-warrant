package hu.baranyos.ui.reports;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Report;
import hu.baranyos.service.report.ReportService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.ReportStringUtils;

@SpringView(name = ReportsLayoutFactory.NAME, ui = WarrantMainUI.class)
public class ReportsLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "reports";

    Grid<Report> reportGrid;
    ListDataProvider<Report> dataProvider;
    List<Report> reportList;
    Button createButton;

    @Autowired
    private ReportService reportService;

    public ReportsLayoutFactory() {
        super();
        this.setMargin(false);
        setSizeFull();

        reportGrid = new Grid<Report>();
        reportGrid.setSizeFull();

        reportGrid.addColumn(Report::getDate).setCaption(ReportStringUtils.DATE.getString());

        createButton = new Button(ReportStringUtils.CREATE.getString());
        createButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        createButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent clickEvent) {
                reportService.saveReport();
            }
        });
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        load();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(reportGrid);
        this.addComponent(createButton);
    }

    private void load() {
        reportList = reportService.getAllReport();
        dataProvider = DataProvider.ofCollection(reportList);
        reportGrid.setDataProvider(dataProvider);
    }
}
