package hu.baranyos.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class WarrantMenuFactory implements UIComponentBuilder {

    private class WarrantMenu extends VerticalLayout {
        private Tree<String> mainMenu;
        private TreeData<String> menuData;

        public WarrantMenu init() {
            mainMenu = new Tree<String>();
            menuData = new TreeData<String>();
            return this;
        }

        public WarrantMenu setLayout() {
            setWidth("100");
            setHeightUndefined();

            menuData.addItem(null, "Base");
            menuData.addItem("Base", "Children");

            mainMenu.setDataProvider(new TreeDataProvider<String>(menuData));

            addComponent(mainMenu);

            return this;
        }
    }

    public Component createComponent() {
        return new WarrantMenu().init().setLayout();
    }

}
