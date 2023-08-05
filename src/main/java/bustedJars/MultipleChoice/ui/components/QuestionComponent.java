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

    private H1 question;
    private List<SelectEntryComponent> anwserEntries;


    public QuestionComponent(Question question) {
        this.question = new H1(question.question());
        add(this.question);
        setAnwserComponents(question.anwsers());
        setAlignItems(Alignment.CENTER);
        getStyle().set("border", "2px solid black");
    }

    private void setAnwserComponents(Set<Anwser> anwserList) {
        List<SelectEntryComponent> list = new ArrayList<>();
        AtomicInteger ordinal = new AtomicInteger(1);
        anwserList.stream().forEach(anwser -> {
            String value = anwser.anwser();
            list.add(new SelectEntryComponent(value));
        });
        Collections.shuffle(list);
        this.anwserEntries = list;
        this.anwserEntries.stream().forEach(entry -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(new H2(ordinal + ") "));
            horizontalLayout.add(entry);
            add(horizontalLayout);
            ordinal.getAndIncrement();
        });
    }

    public H1 getQuestion() {
        return question;
    }

    public List<SelectEntryComponent> getAnwserEntries() {
        return anwserEntries;
    }

}
