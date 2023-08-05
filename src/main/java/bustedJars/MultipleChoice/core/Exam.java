package bustedJars.MultipleChoice.core;

import java.util.List;
import java.util.Map;

public class Exam {
    private int grade;
    private Topic topic;
    private List<Question> questionList;

    private long startTime;
    private  long endTime;


    public Exam(Topic topic, List<Question> questionList) {
        this.topic = topic;
        this.questionList = questionList;
        this.startTime=System.currentTimeMillis();
    }

    public void gradeExam(List<Question> submitedAnwsers){
        int questionValue=100/questionList.size();
        int finalGrade=0;

        for (Question q: questionList) {
            if(submitedAnwsers.contains(q)){
                finalGrade= finalGrade+questionValue;
            }
        }
        this.grade= finalGrade;
        endTime= System.currentTimeMillis();
    }

    public List<Question> getQuestionList() {
        return questionList;
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
