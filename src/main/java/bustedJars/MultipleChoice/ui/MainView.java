package bustedJars.MultipleChoice.ui;

import bustedJars.MultipleChoice.core.Exam;
import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;
import bustedJars.MultipleChoice.spring.mongo.TopicStorage;
import bustedJars.MultipleChoice.ui.components.ExamComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route
public class MainView extends VerticalLayout {


    private Button createExamButton;


//    private List<QuestionComponent> questionComponentList;
    private TopicStorage topicStorage;

    private ExamComponent examComponent;


    public MainView(TopicStorage topicStorage) {
        this.topicStorage = topicStorage;
        createExamButton = new Button("Get new Exam");
        createExamButton.addClickListener(e -> { initQuestionContainer();
        });
        add(createExamButton);
        setAlignItems(Alignment.CENTER);
    }

    private void initQuestionContainer() {
        List<Question> questionList= topicStorage.getTopicQuestions(Topic.MONG_TEST);
        Exam exam= new Exam(Topic.MONG_TEST,questionList);
        examComponent = new ExamComponent(exam);
        add(examComponent);
    }
}
