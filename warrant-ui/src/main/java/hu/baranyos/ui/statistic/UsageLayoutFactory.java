package hu.baranyos.ui.statistic;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.ui.commons.WarrantMainUI;

@SpringView(name = UsageLayoutFactory.NAME, ui = WarrantMainUI.class)
public class UsageLayoutFactory extends VerticalLayout implements View {

    private static final long serialVersionUID = 7042590837932824908L;

    public static final String NAME = "usage_stat";

}
