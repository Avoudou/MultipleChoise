package bustedJars.MultipleChoice.ui.components;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Question;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class QuestionComponent extends VerticalLayout {

    private H1 question;
    private List<SelectEntryComponent> anwserEntries;

    public QuestionComponent(Question question) {
        this.question= new H1(question.question());
        add(this.question);
        setAnwserComponents(question.anwsers());

    }
    private void setAnwserComponents(List<Anwser> anwserList){
        List<SelectEntryComponent> list= new ArrayList<>();
        anwserList.stream().forEach(anwser -> list.add(new SelectEntryComponent(anwser.anwser())));
        this.anwserEntries =list;
        this.anwserEntries.stream().forEach(entry-> add(entry));
    }
}
