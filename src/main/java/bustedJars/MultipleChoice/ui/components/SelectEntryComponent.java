package bustedJars.MultipleChoice.ui.components;

import com.vaadin.flow.component.button.Button;

public class SelectEntryComponent  extends Button {
     private String  display;
     private boolean isSelected= false;
    public SelectEntryComponent(String display) {
        this.display=display;
        setText(display);
        getStyle().set("background-color", "white");
        setWidth("600px");
        addClickListener(e -> {
            isSelected= isSelected?false:true;
            if(isSelected){
                getStyle().set("background-color", "green");
            }else{
                getStyle().set("background-color", "white");
            }
        });

    }
    public boolean isSelected() {
        return isSelected;
    }
}
