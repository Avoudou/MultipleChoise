package bustedJars.MultipleChoice.core;

import java.util.List;
import java.util.Objects;

public record Question(String question, List<Anwser> anwsers) {

    public Question {
        Objects.requireNonNull(question);
        Objects.requireNonNull(anwsers);
    }

}
