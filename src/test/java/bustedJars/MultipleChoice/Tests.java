package bustedJars.MultipleChoice;

import bustedJars.MultipleChoice.core.Anwser;
import bustedJars.MultipleChoice.core.Question;

import java.util.List;
import java.util.Set;

public class Tests {
    public static void main(String[] args){
        Question question1 = new Question("What is your name?", Set.of(new Anwser("John",false), new Anwser("Jane",true)));
        Question question2 = new Question("What is your name?", Set.of(new Anwser("John",false), new Anwser("Jane", true)));

        boolean areEqual = question1.equals(question2); // This will be true
        int hashCode1 = question1.hashCode();
        int hashCode2 = question2.hashCode();
        System.out.println(areEqual);
        System.out.println(hashCode1);
        System.out.println(hashCode2);
    }
}
