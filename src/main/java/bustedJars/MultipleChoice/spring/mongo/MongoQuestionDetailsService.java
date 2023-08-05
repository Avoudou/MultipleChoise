package bustedJars.MultipleChoice.spring.mongo;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MongoQuestionDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public TopicStorage createTopicStorage() {
        TopicStorage topicStorage = new TopicStorage();
        topicStorage.addTopic("karfoto", loadQuestions());
        return topicStorage;
    }

    private List<Question> loadQuestions() {
        Query query = new Query();
        List<QuestionModel> questionModelList = mongoTemplate.find(query, QuestionModel.class);

        List<Question>  returnList= new ArrayList<>();
        for(QuestionModel modle:questionModelList){
            ArrayList<Anwser> anwList = new ArrayList<>();
            String[] anwArr= modle.getAnwsers().split(";");
            for (String ans: anwArr) {
                if(ans.startsWith("*")&& ans.endsWith("*")){
                    anwList.add(new Anwser(ans.substring(1,ans.length()-1),true));
                }else{
                    anwList.add(new Anwser(ans,false));
                }
            }
            Question question= new Question(modle.getQuestion(),anwList);
            returnList.add(question);
        }


        return returnList;
    }


}