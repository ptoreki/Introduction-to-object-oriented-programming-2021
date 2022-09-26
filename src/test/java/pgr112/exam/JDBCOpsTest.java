package pgr112.exam;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pgr112.exam.model.Dto.BinaryQuestionDto;

import java.sql.*;

public class JDBCOpsTest {

    private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/quizDb?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "mysql";

    JDBCOpsTest() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testCreateBinaryQuestion() {
        BinaryQuestionDto q1 = new BinaryQuestionDto();
        try(Connection con = DriverManager
                .getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            con.setAutoCommit(false);

            Statement stmt = con.createStatement();

            // Cleanup
            stmt.executeUpdate("DELETE FROM binaryquiz");

            String question = "Is the sky blue?";
            String correctAnswer = "Yes";

            q1.setQuestion(question);
            q1.setCorrectAnswer(correctAnswer);

            String insertSql = "INSERT INTO binaryquiz(question,correctAnswer)"
                    + "VALUES('" +
                    q1.getQuestion() + "', '" +
                    q1.getCorrectAnswer() + "')";

            stmt.executeUpdate(insertSql);

            try(ResultSet rs = stmt.executeQuery("SELECT * FROM binaryquiz")) {
                //Check for object
                assertTrue(rs.next());
                // Check that db object cotains the expected values
                assertEquals(question, rs.getString("question"));
                assertEquals(correctAnswer, rs.getString("correctAnswer"));
                // Should be false as it is the only object
                assertFalse(rs.next());
            }
            finally {
                con.rollback(); // so that if changes are made it undoes it
            }
        }
        catch (SQLException sqlException) {
            fail(sqlException);
        }
    }
}
