package hu.baranyos.ui.statistic;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.ui.commons.WarrantMainUI;

@SpringView(name = UsageLayoutFactory.NAME, ui = WarrantMainUI.class)
public class UsageLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 7042590837932824908L;

    public static final String NAME = "usage_stat";

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        getChart();
    }

    private void getChart() {
        final BarChartConfig barConfig = new BarChartConfig();

        final ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        this.addComponent(chart);
    }

}
