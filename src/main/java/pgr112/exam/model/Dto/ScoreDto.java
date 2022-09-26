package pgr112.exam.model.Dto;

public class ScoreDto {
    private int id;
    private int scoreNumber;
    private String topic;
    private String username;


    public ScoreDto(int id) {
        this.id = id;
    }

    public ScoreDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return scoreNumber;
    }

    public void setScore(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
