package hu.baranyos.ui.statistic;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

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
        barConfig.data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().backgroundColor("rgba(220,220,220,0.5)")
                        .label("Dataset 1").yAxisID("y-axis-1"))
                .addDataset(new BarDataset().backgroundColor("rgba(151,187,205,0.5)")
                        .label("Dataset 2").yAxisID("y-axis-2").hidden(true))
                .addDataset(new BarDataset()
                        .backgroundColor(ColorUtils.randomColor(0.7), ColorUtils
                                .randomColor(0.7), ColorUtils.randomColor(0.7), ColorUtils
                                        .randomColor(0.7), ColorUtils.randomColor(0.7), ColorUtils
                                                .randomColor(0.7), ColorUtils.randomColor(0.7))
                        .label("Dataset 3").yAxisID("y-axis-1"))
                .and();
        barConfig.options()
                .responsive(true)
                .hover()
                .mode(InteractionMode.INDEX)
                .intersect(true)
                .animationDuration(400)
                .and()
                .title()
                .display(true)
                .text("Chart.js Bar Chart - Multi Axis")
                .and()
                .scales()
                .add(Axis.Y, new LinearScale().display(true).position(Position.LEFT).id("y-axis-1"))
                .add(Axis.Y, new LinearScale().display(true).position(Position.RIGHT).id("y-axis-2")
                        .gridLines().drawOnChartArea(false).and())
                .and()
                .done();

        final List<String> labels = barConfig.data().getLabels();
        for (final Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            final BarDataset lds = (BarDataset) ds;
            final List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
        }

        final ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        this.addComponent(chart);
    }

}
