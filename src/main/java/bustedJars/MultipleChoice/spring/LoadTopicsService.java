package bustedJars.MultipleChoice.spring;

import bustedJars.MultipleChoice.core.Topic;
import bustedJars.MultipleChoice.spring.mongo.MongoQuestionDetailsService;
import bustedJars.MultipleChoice.spring.mongo.TopicStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class LoadTopicsService {

    @Autowired
    MongoQuestionDetailsService mongoQuestionDetailsService;
    @Bean
    public TopicStorage createTopicStorage() {
        TopicStorage topicStorage = new TopicStorage();
        topicStorage.addTopic(Topic.MONG_TEST, mongoQuestionDetailsService.loadQuestions());
        return topicStorage;
    }
}
