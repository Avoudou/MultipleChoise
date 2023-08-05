package bustedJars.MultipleChoice.spring.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionModel,String> {

}
