package bustedJars.MultipleChoice.ui;

import bustedJars.MultipleChoice.core.Exam;
import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;
import bustedJars.MultipleChoice.spring.TopicStorage;
import bustedJars.MultipleChoice.ui.components.ExamComponent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.ComboBoxVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Route
public class AppMainView extends AppLayout {


    private VerticalLayout tabs;
    private Button createExamButton;
    private ComboBox<Topic> topicComboBox;
    private TopicStorage topicStorage;
    private Map<H2, MainView> tabMap;

    private int tempCounter = 0;

    public AppMainView(TopicStorage topicStorage) {
        this.tabMap = new HashMap<>();
        this.topicStorage = topicStorage;
        getElement().setAttribute("theme", Lumo.DARK);


        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Quiz App");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        VerticalLayout rightAlignedComponents = new VerticalLayout(title, toggle);
        rightAlignedComponents.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        rightAlignedComponents.setMaxWidth("200px");


        createTopicComboBox(topicStorage);
        createExamButton();
        HorizontalLayout middleAlignedComponents = new HorizontalLayout();
        middleAlignedComponents.setSizeFull();
        middleAlignedComponents.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        middleAlignedComponents.add(topicComboBox, createExamButton);


        addToNavbar(rightAlignedComponents);
        addToNavbar(middleAlignedComponents);

        this.tabs = new VerticalLayout();

        addToDrawer(tabs);

    }

    private void createTopicComboBox(TopicStorage topicStorage) {
        Set<Topic> topicsSet = topicStorage.getTopicDictionary().keySet();
//        H2 selectTopicLabel=new H2("Select Topic: ");
        topicComboBox = new ComboBox<>("Select a Topic");
        topicComboBox.setItems(topicsSet);
        topicComboBox.addThemeVariants(ComboBoxVariant.LUMO_ALIGN_LEFT, ComboBoxVariant.LUMO_SMALL);

    }

    private void createExamButton() {
        createExamButton = new Button("Get new Exam");
//        createExamButton.getStyle().set("background-color", "#d3d3d3");
        createExamButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        createExamButton.addClickListener(e -> {
            initExamContainer();
        });

    }

    private void initExamContainer() {
        if (topicComboBox.getValue() == null) {
            System.out.println("select a topic");
            Notification.show("Please select a topic", 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }

        Set<Question> questionList = topicStorage.getTopicQuestions(topicComboBox.getValue());
        Exam exam = new Exam(Topic.MONG_TEST, questionList);
        ExamComponent examComponent = new ExamComponent(exam);

        MainView newView = new MainView(topicComboBox);
        newView.setExamComponent(examComponent);
        newView.add(examComponent);
//
        H2 newTab = new H2("exam " + tempCounter + " " + topicComboBox.getValue());
        tempCounter++;
        tabMap.put(newTab, newView);

        newTab.addClickListener(e -> {
            this.remove(tabMap.get(newTab));
            setContent(tabMap.get(newTab));
        });

        tabs.add(newTab);

//



        setContent(newView);
    }
}
