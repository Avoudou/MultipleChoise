package bustedJars.MultipleChoice.ui.components;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Exam;
import bustedJars.MultipleChoice.core.Question;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamComponent extends VerticalLayout {
    private Exam exam;
    private Button submitAnwsersBtn;
    private List<QuestionComponent> questionComponentList;

    public ExamComponent(Exam exam) {
        questionComponentList = new ArrayList<>();
        this.exam = exam;
        setAlignItems(Alignment.CENTER);
        questionComponentList = exam.getQuestionList().stream()
                .map(q -> new QuestionComponent(q))
                .collect(Collectors.toList());
        questionComponentList.stream().forEach(qc -> add(qc));

        submitAnwsersBtn = new Button("submit anwsers");
        submitAnwsersBtn.addClickListener(e -> {
            exam.gradeExam(createSubmitedAnwsersList());
            add( new H1("Grade: "+ exam.getGrade()));
        });
        add(submitAnwsersBtn);
    }

    private List<Question> createSubmitedAnwsersList() {
        List<Question> returnList= new ArrayList<>();
        for (QuestionComponent comp : questionComponentList) {
            Set<Anwser> anwsers = new HashSet<>();
            comp.getAnwserEntries().stream().forEach(ent->{
                anwsers.add(new Anwser(ent.getDisplay(),ent.isSelected()));
            });
            Question question= new Question(comp.getQuestion().getText(),anwsers);
            returnList.add(question);
        }

        return returnList;
    }

}
