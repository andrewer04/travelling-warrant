package hu.baranyos.ui.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

public class WarrantNavigator extends Navigator {

    public WarrantNavigator(final UI ui, final SingleComponentContainer container) {
        super(ui, container);
    }

    public static WarrantNavigator getNavigator() {
        final UI ui = UI.getCurrent();
        final Navigator navigator = ui.getNavigator();

        return (WarrantNavigator) navigator;
    }

    public static void navigate(final String path) {
        WarrantNavigator.getNavigator().navigateTo(path);
    }

    @Override
    public void navigateTo(final String viewName) {
        if (viewName != null) {
            super.navigateTo(viewName);
        } else {
            super.navigateTo("");
        }
    }
}
