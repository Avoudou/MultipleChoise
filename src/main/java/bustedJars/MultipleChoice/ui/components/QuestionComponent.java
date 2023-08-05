package bustedJars.MultipleChoice.ui.components;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Question;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class QuestionComponent extends VerticalLayout {

    private H1 questionH1;
    private List<SelectEntryComponent> answerEntries;

    private Question question;


    public QuestionComponent(Question question) {
        this.question= question;
        this.questionH1 = new H1(question.question());
        add(this.questionH1);
        setAnwserComponents(question.anwsers());
        setAlignItems(Alignment.CENTER);
        getStyle().set("border", "2px solid black");
    }

    private void setAnwserComponents(Set<Anwser> anwserList) {
        List<SelectEntryComponent> list = new ArrayList<>();
        AtomicInteger ordinal = new AtomicInteger(1);
        anwserList.stream().forEach(anwser -> {
            list.add(new SelectEntryComponent(anwser));
        });
        Collections.shuffle(list);
        this.answerEntries = list;
        this.answerEntries.stream().forEach(entry -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(new H2(ordinal + ") "));
            horizontalLayout.add(entry);
            add(horizontalLayout);
            ordinal.getAndIncrement();
        });
    }

    public H1 getQuestionH1() {
        return questionH1;
    }

    public List<SelectEntryComponent> getAnswerEntries() {
        return answerEntries;
    }

    public Question getQuestion() {
        return question;
    }
    public void resolveAnwsers(){
        answerEntries.stream().forEach(ent->{
           Boolean isCorrect= ent.resolveAnwserIsCorrect();
//           if
        });
    }
}
