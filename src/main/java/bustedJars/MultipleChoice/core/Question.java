package bustedJars.MultipleChoice.core;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public record Question(String question, Set<Anwser> anwsers) {

    public Question {
        Objects.requireNonNull(question);
        Objects.requireNonNull(anwsers);
    }

}
