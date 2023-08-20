package bustedJars.MultipleChoice.ui.components;

import bustedJars.MultipleChoice.core.Anwser;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.awt.*;

public class SelectEntryComponent extends HorizontalLayout {

    private final HorizontalLayout parentComponent;
    private boolean isSelected = false;

    private Anwser anwser;

    public SelectEntryComponent(Anwser anwser) {
        parentComponent = new HorizontalLayout();
        this.anwser = anwser;

        H2 displayText = new H2(anwser.anwser());

        parentComponent.add(displayText);
        parentComponent.setAlignItems(Alignment.CENTER);
        parentComponent.setJustifyContentMode(JustifyContentMode.CENTER);
          parentComponent.getStyle().set("background-color", "white");
        parentComponent.setWidth("600px");
        parentComponent.setMaxWidth("600px");
        displayText.getStyle().set("color", "black");
        getStyle().set("padding", "5px");

        add(parentComponent);
        addClickListener(e -> {
            isSelected = isSelected ? false : true;
            if (isSelected) {
                getStyle().set("background-color", "yellow");
            } else {
                getStyle().set("background-color", "white");
            }
        });
        getStyle().set("border", "1px solid gray");
    }

    public Boolean resolveAnwserIsCorrect() {
        if (isSelected) {
            if (anwser.iscorrect()) {
                getStyle().set("background-color", "#90EE90");
               add("Correct!");
                return true;
            } else {
                getStyle().set("background-color", "red");
                add("Wrong!!");
                return false;
            }
        } else {
            if (anwser.iscorrect()) {
                getStyle().set("background-color", "green");
            } else {
                getStyle().set("background-color", "#d3d3d3");
            }
        }
        return null;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Anwser getAnwser() {
        return anwser;
    }
}
