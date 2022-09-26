package pgr112.exam.model;
// Us abstract so that the binary and multiple choice classes can use the same methods with different logic
public abstract class Quiz {

    public abstract void showQuestion();

    public abstract boolean isCorrectAnswer(String answer);

}
