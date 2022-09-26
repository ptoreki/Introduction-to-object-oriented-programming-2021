package pgr112.exam.model.Dto;

public class MultiChoiceQuestionDto {
    private int id;

    private String question;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String correctAnswer;

    public MultiChoiceQuestionDto (int id) {
        this.id = id;
    }

    public MultiChoiceQuestionDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
