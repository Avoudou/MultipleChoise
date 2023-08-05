package bustedJars.MultipleChoice.spring.mongo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("questions")
public class QuestionModel {
    @Id
    private String id;
    private String question;
    private String anwsers;


    public QuestionModel() {

    }

    public QuestionModel(String question, String anwsers) {
        this.question = question;
        this.anwsers = anwsers;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnwsers() {
        return anwsers;
    }
}
