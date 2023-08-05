package bustedJars.MultipleChoice.spring.mongo;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MongoQuestionDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;



    public List<Question> loadQuestions() {
        Query query = new Query();
        List<QuestionModel> questionModelList = mongoTemplate.find(query, QuestionModel.class);

        List<Question>  returnList= new ArrayList<>();
        for(QuestionModel modle:questionModelList){
            Set<Anwser> anwSet = new HashSet<>();
            String[] anwArr= modle.getAnwsers().split(";");
            for (String ans: anwArr) {
                if(ans.startsWith("*")&& ans.endsWith("*")){
                    anwSet.add(new Anwser(ans.substring(1,ans.length()-1),true));
                }else{
                    anwSet.add(new Anwser(ans,false));
                }
            }
            Question question= new Question(modle.getQuestion(),anwSet);
            returnList.add(question);
        }


        return returnList;
    }


}