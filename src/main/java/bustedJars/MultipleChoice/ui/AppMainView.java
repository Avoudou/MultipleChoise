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
import com.vaadin.flow.component.combobox.ComboBox;
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
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Route
public class AppMainView extends AppLayout {


    private MainView mainDisplay;
    private  VerticalLayout tabs;
    private Button createExamButton;
    private ComboBox<Topic> topicComboBox;
    private TopicStorage topicStorage;
    private Map<Tab, Component> tabMap;

    private int tempCounter=0;

    public AppMainView(TopicStorage topicStorage) {
        this.tabMap= new HashMap<>();
        this.topicStorage = topicStorage;

        mainDisplay = new MainView(topicComboBox);
        setContent(mainDisplay);

        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("Quiz App");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");
        VerticalLayout rightAlignedComponents = new VerticalLayout( title,toggle);
        rightAlignedComponents.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        rightAlignedComponents.setMaxWidth("200px");


        createTopicComboBox(topicStorage);
        createExamButton();
        HorizontalLayout middleAlignedComponents = new HorizontalLayout();
        middleAlignedComponents.setSizeFull();
        middleAlignedComponents.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        middleAlignedComponents.add(topicComboBox,createExamButton);


        addToNavbar(rightAlignedComponents);
        addToNavbar(middleAlignedComponents);

      this.tabs= new VerticalLayout();

        addToDrawer(tabs);

    }

    private void createTopicComboBox(TopicStorage topicStorage) {
        Set<Topic> topicsSet = topicStorage.getTopicDictionary().keySet();
//        H2 selectTopicLabel=new H2("Select Topic: ");
        topicComboBox = new ComboBox<>("Select a Topic");
        topicComboBox.setItems(topicsSet);
        topicComboBox.getStyle().set("background-color", "#d3d3d3");

    }

    private void createExamButton() {
        createExamButton = new Button("Get new Exam");
        createExamButton.getStyle().set("background-color", "#d3d3d3");
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
        if (mainDisplay.getExamComponent() != null) {
            mainDisplay.remove(mainDisplay.getExamComponent());
        }
        Set<Question> questionList = topicStorage.getTopicQuestions(topicComboBox.getValue());
        Exam exam = new Exam(Topic.MONG_TEST, questionList);
        ExamComponent examComponent = new ExamComponent(exam);

        MainView newView= new MainView(topicComboBox);
        newView.setExamComponent(examComponent);
        newView.add(examComponent);
//
        Tab newTab= new Tab("exam "+ tempCounter+  " "+  topicComboBox.getValue());
        tempCounter++;
        tabs.add(newTab);
        newTab.setSelected(true);
//



        this.remove(mainDisplay);
        this.mainDisplay= newView;
        setContent(newView);
    }
}
