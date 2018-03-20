package hu.baranyos.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ItemClick;
import com.vaadin.ui.Tree.ItemClickListener;
import com.vaadin.ui.VerticalLayout;

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

                    final String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
                    WarrantNavigator.navigate(path);
                }
            });
            return this;
        }

        public WarrantMenu setLayout() {
            setWidth("100");
            setHeightUndefined();

            menuData.addItem(null, StringUtils.LOGIN.getString())
                    .addItem(null, StringUtils.TRAVELS.getString())
                    .addItem(null, StringUtils.FUELING.getString())
                    .addItem(null, StringUtils.REPORTS.getString())
                    .addItem(null, StringUtils.USERS.getString());
            menuData.addItem(StringUtils.USERS.getString(), StringUtils.NEW_USER.getString());

            mainMenu.setDataProvider(new TreeDataProvider<>(menuData));

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
