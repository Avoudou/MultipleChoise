package bustedJars.MultipleChoice.spring.mongo;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MongoQuestionDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;



    public Set<Question> loadQuestions() {
        Query query = new Query();
        List<QuestionModel> questionModelList = mongoTemplate.find(query, QuestionModel.class);

        Set<Question> returnSet = new HashSet<>();
        for (QuestionModel model : questionModelList) {
            Set<Anwser> anwSet = new HashSet<>();
            String[] anwArr = model.getAnwsers().split(";");
            for (String ans : anwArr) {
                if (ans.startsWith("*") && ans.endsWith("*")) {
                    anwSet.add(new Anwser(ans.substring(1, ans.length() - 1), true));
                } else {
                    anwSet.add(new Anwser(ans, false));
                }
            }
            Question question = new Question(model.getQuestion(), anwSet);
            returnSet.add(question);
        }

        return returnSet;
    }


}