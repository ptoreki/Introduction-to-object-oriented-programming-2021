package pgr112.exam.model.Dto;

public class BinaryQuestionDto {
    private int id;
    private String question;
    private String correctAnswer;

    public BinaryQuestionDto(int id) {
        this.id = id;
    }

    public BinaryQuestionDto() {}

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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
