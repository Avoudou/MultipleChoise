package bustedJars.MultipleChoice.ui.components;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Exam;
import bustedJars.MultipleChoice.core.Question;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


public class ExamComponent extends VerticalLayout {
    private Exam exam;
    private Button submitAnwsersBtn;
    private List<QuestionComponent> questionComponentList;

    private H1 timer;

    public ExamComponent(Exam exam) {
        questionComponentList = new ArrayList<>();
        this.exam = exam;
        setAlignItems(Alignment.CENTER);
//        getStyle().set("border", "4px solid black");
//        getStyle().set("background-color", "gray");
        getElement().setAttribute("theme", Lumo.DARK);



        HorizontalLayout horizontalLayout= new HorizontalLayout();
        horizontalLayout.add((new H1("Exam for "+exam.getTopic()+": ")));
        timer= new H1("Time passed:");
        horizontalLayout.add(timer);

        add(horizontalLayout);
        UI ui = UI.getCurrent();

        ui.setPollInterval(1000);
        ui.addPollListener(event -> {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Double timeInMins= Double.parseDouble(decimalFormat.format(((System.currentTimeMillis()-exam.getStartTime())/ 60000.0)));
           timer.setText("Time passed:"+ timeInMins+ "mins");
        });



        questionComponentList = exam.getQuestionSet().stream()
                .map(q -> new QuestionComponent(q))
                .collect(Collectors.toList());
        Collections.shuffle(questionComponentList);
        questionComponentList.stream().forEach(qc -> add(qc));

        submitAnwsersBtn = new Button("submit anwsers");
        submitAnwsersBtn.addClickListener(e -> {
            exam.gradeExam(createSubmitedAnwsersList());
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Double timeInMins= Double.parseDouble(decimalFormat.format(((exam.getEndTime()-exam.getStartTime())/ 60000.0)));
            add( new H1("Grade: "+ exam.getGrade()+ "out of 100 in " +timeInMins+" min "));
            questionComponentList.stream().forEach(comp->{
                comp.setEnabled(false);
                comp.resolveAnwsers();
            });
            submitAnwsersBtn.setEnabled(false);
        });
        add(submitAnwsersBtn);
    }

    private List<Question> createSubmitedAnwsersList() {
        List<Question> returnList= new ArrayList<>();
        for (QuestionComponent comp : questionComponentList) {
            Set<Anwser> anwsers = new HashSet<>();
            comp.getAnswerEntries().stream().forEach(ent->{
                anwsers.add(new Anwser(ent.getAnwser().anwser(),ent.isSelected()));
            });
            Question question= new Question(comp.getQuestionH1().getText(),anwsers);
            returnList.add(question);
        }

        return returnList;
    }

}
