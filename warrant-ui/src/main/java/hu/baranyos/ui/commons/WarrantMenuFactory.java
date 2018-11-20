package hu.baranyos.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ItemClick;
import com.vaadin.ui.Tree.ItemClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.springframework.security.core.context.SecurityContextHolder;

import hu.baranyos.ui.navigator.WarrantNavigator;
import hu.baranyos.utils.StringUtils;

@org.springframework.stereotype.Component
public class WarrantMenuFactory implements UIComponentBuilder {

    private class WarrantMenu extends VerticalLayout {
        private Tree<String> mainMenu;
        private TreeData<String> menuData;

        public WarrantMenu init() {
            mainMenu = new Tree<>();
            menuData = new TreeData<>();
            mainMenu.addItemClickListener(new ItemClickListener<String>() {

                @Override
                public void itemClick(final ItemClick<String> event) {
                    final String selectedItemPath =
                            StringUtils.fromString(event.getItem()).toString();

                    if (selectedItemPath == null) {
                        return;
                    }

                    if (selectedItemPath.equals("LOGOUT")) {
                        SecurityContextHolder.clearContext();
                        UI.getCurrent().getPage().setLocation("/ui");
                    }

                    final String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
                    WarrantNavigator.navigate(path);
                }
            });
            return this;
        }

        public WarrantMenu setLayout() {
            setWidth("100");
            setHeightUndefined();

            menuData.addItem(null, StringUtils.TRAVELS.getString())
                    .addItem(null, StringUtils.FUELING.getString())
                    .addItem(null, StringUtils.VEHICLES.getString())
                    .addItem(null, StringUtils.LOCATIONS.getString())
                    .addItem(null, StringUtils.REPORTS.getString())
                    //.addItem(null, StringUtils.STATISTIC.getString())
                    .addItem(null, StringUtils.USERS.getString())
                    .addItem(null, StringUtils.LOGOUT.getString());
            menuData.addItem(StringUtils.TRAVELS.getString(), StringUtils.NEW_TRAVEL.getString())
                    .addItem(StringUtils.FUELING.getString(), StringUtils.ADD_FUELING.getString())
                    .addItem(StringUtils.VEHICLES.getString(), StringUtils.NEW_VEHICLE.getString())
                    .addItem(StringUtils.LOCATIONS.getString(), StringUtils.NEW_LOCATION.getString());
                    //.addItem(StringUtils.STATISTIC.getString(), StringUtils.USAGE_STAT.getString());

            mainMenu.setDataProvider(new TreeDataProvider<>(menuData));
            mainMenu.expand(StringUtils.TRAVELS.getString(),StringUtils.FUELING.getString(),StringUtils.VEHICLES.getString(),StringUtils.LOCATIONS.getString());

            addComponent(mainMenu);

            setSizeFull();
            this.setMargin(new MarginInfo(false, false, false, false));
            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new WarrantMenu().init().setLayout();
    }

}
