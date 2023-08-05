package bustedJars.MultipleChoice.spring.mongo;

import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;

import java.util.HashMap;
import java.util.List;

public class TopicStorage {

    private HashMap<Topic , List<Question>> topicDictionary= new HashMap<>();

    public void  addTopic(Topic topic,List<Question> topicQuestions){
        topicDictionary.put(topic,topicQuestions);
    }
    public List<Question> getTopicQuestions(Topic topic){
        return topicDictionary.get(topic);
    }
}
