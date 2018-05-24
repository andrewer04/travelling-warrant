package hu.baranyos.ui.location;

import com.vaadin.data.Binder;
import com.vaadin.data.StatusChangeEvent;
import com.vaadin.data.StatusChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import hu.baranyos.model.entity.Location;
import hu.baranyos.service.location.LocationService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.LocationStringUtils;
import hu.baranyos.utils.ReportStringUtils;
import hu.baranyos.utils.UserStringUtils;

@SpringView(name = NewLocationLayoutFactory.NAME, ui = WarrantMainUI.class)
public class NewLocationLayoutFactory extends FormLayout implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "new_location";

    @Autowired
    private LocationService locationService;

    private final TextField name;
    private final Button saveButton;
    private final Button clearButton;

    private final Binder<Location> binder;

    private NewLocationLayoutFactory() {
        super();
        setSpacing(false);
        setMargin(true);

        name = new TextField(LocationStringUtils.NAME.getString());
        saveButton = new Button(UserStringUtils.SAVE_BUTTON.getString());
        clearButton = new Button(UserStringUtils.CLEAR_BUTTON.getString());

        saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        saveButton.setEnabled(false);

        clearButton.setStyleName(ValoTheme.BUTTON_DANGER);

        binder = new Binder<>();
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        removeAllComponents();
        bind();
        addLayout();
    }

    private void addLayout() {
        this.addComponent(name);
        this.addComponent(new HorizontalLayout(saveButton, clearButton));

    }

    private void bind() {
        binder.forField(name)
                .asRequired()
                .bind(Location::getName, Location::setName);

        binder.setBean(new Location());

        saveButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                try {
                    locationService.saveLocation(binder.getBean());
                } catch (final AccessDeniedException e) {
                    Notification.show(ReportStringUtils.WARNING.getString());
                }
                finally {
                    clearFields();
                }
            }

        });

        clearButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                clearFields();
            }
        });

        binder.addStatusChangeListener(new StatusChangeListener() {
            @Override
            public void statusChange(final StatusChangeEvent event) {
                saveButton.setEnabled(binder.isValid());
            }
        });
    }

    private void clearFields() {
        name.clear();
    }
}
