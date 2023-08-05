package bustedJars.MultipleChoice.ui.components;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.dom.Style;

public class SelectEntryComponent extends HorizontalLayout {
    private String display;
    private boolean isSelected = false;

    public SelectEntryComponent(String display) {
        this.display = display;
        Text displayText = new Text(display);
        add(displayText);
        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "white");
        setWidth("600px");
        setMaxWidth("600px");
        getStyle().set("padding", "5px");


        addClickListener(e -> {
            isSelected = isSelected ? false : true;
            if (isSelected) {
                getStyle().set("background-color", "green");
            } else {
                getStyle().set("background-color", "white");
            }
        });
        getStyle().set("border", "1px solid gray");
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getDisplay() {
        return display;
    }
}
