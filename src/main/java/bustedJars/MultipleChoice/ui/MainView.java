package bustedJars.MultipleChoice.ui;

import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.spring.mongo.TopicStorage;
import bustedJars.MultipleChoice.ui.components.QuestionComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {


    private Button createExamButton;
    private Button submitAnwsers;

    private List<QuestionComponent> questionComponentList;
    private TopicStorage topicStorage;


    public MainView(TopicStorage topicStorage) {
        this.topicStorage = topicStorage;
        createExamButton = new Button("getQuestions");
        createExamButton.addClickListener(e -> { initQuestionComponentList();
        });
        add(createExamButton);
    }

    private void initQuestionComponentList() {
    List<Question> questionList= topicStorage.getTopicQuestions("karfoto");
        questionComponentList= new ArrayList<>();
        questionComponentList = questionList.stream()
                .map(q -> new QuestionComponent(q))
                .collect(Collectors.toList());
        questionComponentList.stream().forEach(qc->this.add(qc));
    }
}
