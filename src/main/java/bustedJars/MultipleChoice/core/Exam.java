package bustedJars.MultipleChoice.core;

import java.util.List;
import java.util.Set;

public class Exam {
    private int grade;
    private Topic topic;
    private Set<Question> questionSet;

    private long startTime;
    private  long endTime;


    public Exam(Topic topic, Set<Question> questionList) {
        this.topic = topic;
        this.questionSet = questionList;
        this.startTime=System.currentTimeMillis();
    }

    public void gradeExam(List<Question> submitedAnwsers){
        int questionValue=100/ questionSet.size();
        int finalGrade=0;

        for (Question q: questionSet) {
            if(submitedAnwsers.contains(q)){
                finalGrade= finalGrade+questionValue;
            }
        }
        this.grade= finalGrade;
        endTime= System.currentTimeMillis();
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getGrade() {
        return grade;
    }

    public Topic getTopic() {
        return topic;
    }
}
