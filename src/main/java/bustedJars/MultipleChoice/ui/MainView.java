package bustedJars.MultipleChoice.ui;

import bustedJars.MultipleChoice.core.Exam;
import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;
import bustedJars.MultipleChoice.spring.TopicStorage;
import bustedJars.MultipleChoice.ui.components.ExamComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Set;

//@Route
public class MainView extends VerticalLayout {


    private TopicStorage topicStorage;
    private ExamComponent examComponent;

    private ComboBox<Topic> topicComboBox;


    public MainView(ComboBox topicCB) {
        this.topicComboBox = topicCB;


        setAlignItems(Alignment.CENTER);
        getStyle().set("background-color", "black");
        getStyle().set("overflow", "auto");
        setSizeFull();


    }


    private void initExamContainer() {
        if(topicComboBox.getValue()==null){
            System.out.println("select a topic");
            Notification.show("Please select a topic", 3000, Notification.Position.TOP_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }
        if(examComponent!=null){
            remove(examComponent);
        }
        Set<Question> questionList = topicStorage.getTopicQuestions(topicComboBox.getValue());
        Exam exam = new Exam(Topic.MONG_TEST, questionList);
        examComponent = new ExamComponent(exam);
        add(examComponent);
    }

    public ExamComponent getExamComponent() {
        return examComponent;
    }

    public void setExamComponent(ExamComponent examComponent) {
        this.examComponent = examComponent;
    }


}
