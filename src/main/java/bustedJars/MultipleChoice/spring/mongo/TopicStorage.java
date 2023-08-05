package bustedJars.MultipleChoice.spring.mongo;

import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;

import java.util.HashMap;
import java.util.List;

public class TopicStorage {

    private HashMap<String , List<Question>> topicDictionary= new HashMap<>();

    public void  addTopic(String topic,List<Question> topicQuestions){
        topicDictionary.put(topic,topicQuestions);
    }
    public List<Question> getTopicQuestions(String topic){
        return topicDictionary.get(topic);
    }
}
