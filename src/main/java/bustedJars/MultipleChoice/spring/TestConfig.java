package bustedJars.MultipleChoice.spring;

import bustedJars.MultipleChoice.core.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

//    @Autowired
    private TopicStorage topicStorage;



    public TestConfig(TopicStorage topicStorage){
        this.topicStorage= topicStorage;
        topicStorage.getTopicQuestions(Topic.MONG_TEST);
    }
}
