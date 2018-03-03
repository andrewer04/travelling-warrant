package hu.baranyos.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

import hu.baranyos.utils.StringUtils;

@org.springframework.stereotype.Component
public class WarrantMenuFactory implements UIComponentBuilder {

    private class WarrantMenu extends VerticalLayout {
        private Tree<String> mainMenu;
        private TreeData<String> menuData;

        public WarrantMenu init() {
            mainMenu = new Tree<>();
            menuData = new TreeData<>();
            return this;
        }

        public WarrantMenu setLayout() {
            setWidth("100");
            setHeightUndefined();

            menuData.addItem(null, StringUtils.BASE_MENU.getString())
                    .addItem(StringUtils.BASE_MENU.getString(), StringUtils.CHILDREN_MENU.getString())
                    .addItem(StringUtils.BASE_MENU.getString(), StringUtils.CHILDREN_MENU2.getString());

            mainMenu.setDataProvider(new TreeDataProvider<>(menuData));
            mainMenu.expand(StringUtils.BASE_MENU.getString());

            addComponent(mainMenu);

            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new WarrantMenu().init().setLayout();
    }

}
