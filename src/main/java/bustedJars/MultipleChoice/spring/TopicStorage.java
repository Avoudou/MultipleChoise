package bustedJars.MultipleChoice.spring;

import bustedJars.MultipleChoice.core.Question;
import bustedJars.MultipleChoice.core.Topic;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TopicStorage {


    private final HashMap<Topic , Set<Question>> topicDictionary= new HashMap<>();

    public void  addTopic(Topic topic,Set<Question> topicQuestions){
        topicDictionary.put(topic,topicQuestions);
    }
    public Set<Question> getTopicQuestions(Topic topic){
        return topicDictionary.get(topic);
    }
    public HashMap<Topic, Set<Question>> getTopicDictionary() {
        return topicDictionary;
    }

}
