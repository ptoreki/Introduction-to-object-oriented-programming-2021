package pgr112.exam;

import pgr112.exam.model.Dto.BinaryQuestionDto;
import pgr112.exam.model.Dto.MultiChoiceQuestionDto;
import pgr112.exam.model.Dto.ScoreDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JDBCOps {

    //made to Strings so it's easier to use and change in the future
    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/quizDb?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "mysql";

    public JDBCOps(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

    public boolean insertMultiChoiceQuestion(MultiChoiceQuestionDto multiChoiceQuestion){
        try(Connection con = DriverManager
            .getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
        Statement stmt = con.createStatement();

        String insertSql = "INSERT INTO multichoicequiz(question,answerA,answerB,answerC,answerD,correctAnswer) " +
                        "VALUES('" +
                            multiChoiceQuestion.getQuestion() +"', '" +
                            multiChoiceQuestion.getAnswerA() + "', '" +
                            multiChoiceQuestion.getAnswerB() + "', '" +
                            multiChoiceQuestion.getAnswerC() + "', '" +
                            multiChoiceQuestion.getAnswerD() + "', '" +
                            multiChoiceQuestion.getCorrectAnswer() +
                        "')";

        stmt.executeUpdate(insertSql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteMultiChoiceQuestions() {
        try(Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();

            String cleanUpSql = "DELETE FROM multichoicequiz";
            stmt.execute(cleanUpSql);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<MultiChoiceQuestionDto> getMultipleChoiceQuestions() {
        try(Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD))
        {
            Statement stmt = con.createStatement();

            String countStatement = "SELECT * FROM multichoicequiz";

            ResultSet result = stmt.executeQuery(countStatement);

            List<MultiChoiceQuestionDto> questions = new ArrayList<>();
            while(result.next()) {
                int id = result.getInt("id");
                MultiChoiceQuestionDto multiChoiceQuestion = new MultiChoiceQuestionDto(id);

                multiChoiceQuestion.setQuestion(result.getString("question"));
                multiChoiceQuestion.setAnswerA(result.getString("answerA"));
                multiChoiceQuestion.setAnswerB(result.getString("answerB"));
                multiChoiceQuestion.setAnswerC(result.getString("answerC"));
                multiChoiceQuestion.setAnswerD(result.getString("answerD"));
                multiChoiceQuestion.setCorrectAnswer(result.getString("correctAnswer"));

                questions.add(multiChoiceQuestion);
            }
            return questions;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public boolean insertBinaryQuestion(BinaryQuestionDto binaryQuestion){
        try(Connection con = DriverManager
                .getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO binaryquiz(question,correctAnswer)"
                    + "VALUES('" +
                    binaryQuestion.getQuestion() + "', '" +
                    binaryQuestion.getCorrectAnswer() + "')";

            stmt.executeUpdate(insertSql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteBinaryQuestions() {
        try(Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();

            String cleanUpSql = "DELETE FROM binaryquiz";
            stmt.execute(cleanUpSql);
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<BinaryQuestionDto> getBinaryQuestions() {
        try(Connection con = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD))
        {
            Statement stmt = con.createStatement();

            String countStatement = "SELECT * FROM binaryquiz";

            ResultSet result = stmt.executeQuery(countStatement);

            List<BinaryQuestionDto> questions = new ArrayList<>();
            while(result.next()) {
                int id = result.getInt("id");
                BinaryQuestionDto binaryQuestion = new BinaryQuestionDto(id);

                binaryQuestion.setQuestion(result.getString("question"));
                binaryQuestion.setCorrectAnswer(result.getString("correctAnswer"));

                questions.add(binaryQuestion);
            }
            return questions;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public boolean insertScore(ScoreDto score) {
        try (Connection con = DriverManager
                .getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();

            String insertSql = "INSERT INTO scorehistory(score, topic, user)"
                    + " VALUES('" +
                    score.getScore() + "', '" +
                    score.getTopic() + "', '" +
                    score.getUsername() + "')";

            stmt.executeUpdate(insertSql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
        }
        return true;

    }
    public List<ScoreDto> getAllScores(){

        List<ScoreDto> results = new ArrayList<>();
        try (Connection con = DriverManager
                .getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();

            String selectSql = "SELECT * FROM scorehistory";

            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                ScoreDto score = new ScoreDto(id);
                score.setScore(resultSet.getInt("score"));
                score.setTopic(resultSet.getString("topic"));
                score.setUsername(resultSet.getString("user"));

                results.add(score);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return results;
    }

    public List<ScoreDto> getHighscoresByUser(String username) {

        List<ScoreDto> results = new ArrayList<>();
        try (Connection con = DriverManager
                .getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {

            Statement stmt = con.createStatement();

            String selectScore = "SELECT * FROM scorehistory WHERE user = '" + username + "'";

            ResultSet resultSet = stmt.executeQuery(selectScore);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ScoreDto score = new ScoreDto(id);
                score.setScore(resultSet.getInt("score"));
                score.setTopic(resultSet.getString("topic"));
                score.setUsername(resultSet.getString("user"));

                results.add(score);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return results;
    }
}
