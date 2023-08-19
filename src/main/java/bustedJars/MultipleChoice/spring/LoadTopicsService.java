package bustedJars.MultipleChoice.spring;

import bustedJars.MultipleChoice.core.Topic;
import bustedJars.MultipleChoice.spring.mongo.MongoQuestionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class LoadTopicsService {

    @Autowired
    private  MongoQuestionDetailsService mongoQuestionDetailsService;
    @Bean
    public TopicStorage createTopicStorage() {
        TopicStorage topicStorage = new TopicStorage();
        topicStorage.addTopic(Topic.MONG_TEST, mongoQuestionDetailsService.loadQuestions());
        return topicStorage;
    }
}
