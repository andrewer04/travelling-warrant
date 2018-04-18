package hu.baranyos.ui.fueling;

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
import com.vaadin.ui.themes.ValoTheme;

import org.springframework.beans.factory.annotation.Autowired;

import hu.baranyos.model.entity.Fueling;
import hu.baranyos.service.fueling.FuelingService;
import hu.baranyos.ui.commons.WarrantMainUI;
import hu.baranyos.utils.UserStringUtils;

@SpringView(name = AddFuelingLayoutFactory.NAME, ui = WarrantMainUI.class)
public class AddFuelingLayoutFactory extends FormLayout implements View {

    private final Button saveButton;
    private final Button clearButton;

    @Autowired
    private FuelingService fuelingService;

    Binder<Fueling> binder;

    public static final String NAME = "add_fueling";

    private AddFuelingLayoutFactory() {
        super();
        setSpacing(false);
        this.setMargin(true);

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

        this.addComponent(new HorizontalLayout(saveButton, clearButton));

    }

    private void bind() {

        binder.setBean(new Fueling());

        saveButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                fuelingService.save(binder.getBean());
                clearFields();
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

    private void clearFields() {}
}
