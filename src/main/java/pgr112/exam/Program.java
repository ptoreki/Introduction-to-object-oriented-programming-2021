package pgr112.exam;

import pgr112.exam.model.BinaryQuiz;
import pgr112.exam.model.Dto.BinaryQuestionDto;
import pgr112.exam.model.Dto.MultiChoiceQuestionDto;
import pgr112.exam.model.Dto.ScoreDto;
import pgr112.exam.model.MultipleChoiceQuiz;

import java.util.*;

public class Program {

    private final Scanner input;
    private final ScoreDto scoreDto;

    public Program () {
        input = new Scanner(System.in);
        scoreDto = new ScoreDto();
    }

    public static void main( String[] args )
    {

        Program program = new Program();
        program.populateBinaryTable();
        program.populateMultipleChoiceTable();

        ScoreDto currentPlayer = program.createUser();

        program.handleInitialUserInteraction(currentPlayer);
    }

    public void handleInitialUserInteraction(ScoreDto currentPlayer) {

        BinaryQuiz binaryQuiz = new BinaryQuiz(currentPlayer);
        MultipleChoiceQuiz multipleChoiceQuiz = new MultipleChoiceQuiz(currentPlayer);

        String choice = "0";
        while (!choice.equals("4")) {
            printMainMenu();
            do {
                choice = input.nextLine();
            } while (!isValid(choice, true));
            switch (choice) {
                case "1" -> multipleChoiceQuiz.showQuestion();
                case "2" -> binaryQuiz.showQuestion();
                case "3" -> handleHighScoreInteraction();
                case "4" -> endUserInteraction();
            }
        }
    }

    public void handleHighScoreInteraction() {
        String choice = "0";
        while (!choice.equals("3")) {
            printHighScoreMenu();
            do {
                choice = input.nextLine();
            } while(!isValid(choice, false));
            switch (choice) {
                case "1" -> printAllScores();
                case "2" -> printScoresByUsername();
                case "3" -> handleInitialUserInteraction(scoreDto);
            }
        }
    }

    private void endUserInteraction() { // Deletes questions from tables on and exits program

        JDBCOps jdbcOps = new JDBCOps();
        jdbcOps.deleteMultiChoiceQuestions();
        jdbcOps.deleteBinaryQuestions();

        System.out.println("Exiting quiz.");
        System.exit(1); // to make sure program exits console
    }

    private ScoreDto createUser() {

        System.out.println("Sign up a new user:");
        String username = input.nextLine();
        scoreDto.setUsername(username);
        return scoreDto;
    }

    private void printMainMenu() {
        System.out.println("Select a topic to get started.");
        System.out.println("1 - UFC History - Multiple Choice Quiz");
        System.out.println("2 - UFC Rules - Binary Quiz");
        System.out.println("3 - Highscore");
        System.out.println("4 - Quit");
    }

    private void printHighScoreMenu() {
        System.out.println("Highscore");
        System.out.println("1 - View all highscores");
        System.out.println("2 - Get highscore by user");
        System.out.println("3 - Back to main menu");
    }

    private void printAllScores() {

        JDBCOps jdbcOps = new JDBCOps();
        List<ScoreDto> highscores = jdbcOps.getAllScores();
        sortHighScore(highscores);

        int place = 1; // Loops through the list obtained from the database until all the results have been printed
        for(ScoreDto highscore : highscores) {
            System.out.println(place + ". - User: " + highscore.getUsername() + " - Topic: " + highscore.getTopic()
                    + " - Score: " + highscore.getScore());
            place++;
        }
    }

    private void printScoresByUsername() {
        JDBCOps jdbcOps = new JDBCOps();

        System.out.println("Write in username:");
        String username = input.nextLine();
        System.out.println("Searching for username: " + username);

        List<ScoreDto> highscores = jdbcOps.getHighscoresByUser(username);
        if (highscores == null) {
            return;
        }
        sortHighScore(highscores);

        int place = 1;
        for (ScoreDto highscore : highscores) {
            System.out.println(place + ". - User: " + highscore.getUsername() + " - Topic: " + highscore.getTopic()
                    + " - Score: " + highscore.getScore());
            place++;
        }
    }

    public void sortHighScore(List<ScoreDto> highscores) {
        highscores.sort(Comparator.comparing(ScoreDto::getScore).reversed()); // Sorts the list by score, highest score comes first
    }

    private void populateMultipleChoiceTable() {

        JDBCOps jdbcOps = new JDBCOps();
        MultiChoiceQuestionDto question1 = new MultiChoiceQuestionDto();
        MultiChoiceQuestionDto question2 = new MultiChoiceQuestionDto();
        MultiChoiceQuestionDto question3 = new MultiChoiceQuestionDto();
        MultiChoiceQuestionDto question4 = new MultiChoiceQuestionDto();
        MultiChoiceQuestionDto question5 = new MultiChoiceQuestionDto();


        question1.setQuestion("Who has not been the UFC bantamweight champion?");
        question1.setAnswerA("A. Dominick Cruz");
        question1.setAnswerB("B. T.J. Dillashaw");
        question1.setAnswerC("C. Urijah Faber");
        question1.setAnswerD("D. Aljamain Sterling");
        question1.setCorrectAnswer("C");

        question2.setQuestion("Who is a UFC hall of famer?");
        question2.setAnswerA("A. George St. Pierre");
        question2.setAnswerB("B. Conor McGregor");
        question2.setAnswerC("C. Demetrious Johnson");
        question2.setAnswerD("D. Jon Jones");
        question2.setCorrectAnswer("A");

        question3.setQuestion("What weight is middleweight at?");
        question3.setAnswerA("A. 170 lbs");
        question3.setAnswerB("B. 145 lbs");
        question3.setAnswerC("C.205 lbs");
        question3.setAnswerD("D. 185 lbs");
        question3.setCorrectAnswer("D");

        question4.setQuestion("What is not a division in the UFC?");
        question4.setAnswerA("A. 135 lbs");
        question4.setAnswerB("B. 145 lbs");
        question4.setAnswerC("C. 155 lbs");
        question4.setAnswerD("D. 165 lbs");
        question4.setCorrectAnswer("D");

        question5.setQuestion("How long did it take Conor McGregor to defeat Jose Aldo at UFC 194?");
        question5.setAnswerA("A. 58 seconds");
        question5.setAnswerB("B. 16 minutes and 10 seconds");
        question5.setAnswerC("C. 1 minute and 28 seconds");
        question5.setAnswerD("D. 13 seconds");
        question5.setCorrectAnswer("D");


        boolean q1 = jdbcOps.insertMultiChoiceQuestion(question1);
        boolean q2 = jdbcOps.insertMultiChoiceQuestion(question2);
        boolean q3 = jdbcOps.insertMultiChoiceQuestion(question3);
        boolean q4 = jdbcOps.insertMultiChoiceQuestion(question4);
        boolean q5 = jdbcOps.insertMultiChoiceQuestion(question5);


        if (!q1) {
            System.out.println("ERROR: could not insert question1");
        }
        if (!q2) {
            System.out.println("ERROR: could not insert question2");
        }
        if (!q3) {
            System.out.println("ERROR: could not insert question3");
        }
        if (!q4) {
            System.out.println("ERROR: could not insert question4");
        }
        if (!q5) {
            System.out.println("ERROR: could not insert question4");
        }
    }

    private void populateBinaryTable() {

        JDBCOps jdbcOps = new JDBCOps();
        BinaryQuestionDto question1 = new BinaryQuestionDto();
        BinaryQuestionDto question2 = new BinaryQuestionDto();
        BinaryQuestionDto question3 = new BinaryQuestionDto();
        BinaryQuestionDto question4 = new BinaryQuestionDto();
        BinaryQuestionDto question5 = new BinaryQuestionDto();


        question1.setQuestion("Are soccer kicks allowed in the UFC?");
        question1.setCorrectAnswer("No");

        question2.setQuestion("Are elbows to the head allowed in the UFC?");
        question2.setCorrectAnswer("Yes");

        question3.setQuestion("Can you knee a downed opponent in the UFC?");
        question3.setCorrectAnswer("No");

        question4.setQuestion("Can you headbutt your opponent in the UFC?");
        question4.setCorrectAnswer("No");

        question5.setQuestion("Can you kick someone in the groin in the UFC?");
        question5.setCorrectAnswer("No");


        boolean q1 = jdbcOps.insertBinaryQuestion(question1);
        boolean q2 = jdbcOps.insertBinaryQuestion(question2);
        boolean q3 = jdbcOps.insertBinaryQuestion(question3);
        boolean q4 = jdbcOps.insertBinaryQuestion(question4);
        boolean q5 = jdbcOps.insertBinaryQuestion(question5);


        if (!q1) {
            System.out.println("ERROR: Could not insert question 1");
        }
        if (!q2) {
            System.out.println("ERROR: Could not insert question 2");
        }
        if (!q3) {
            System.out.println("ERROR: Could not insert question 3");
        }
        if (!q4) {
            System.out.println("ERROR: Could not insert question 4");
        }
        if (!q5) {
            System.out.println("ERROR: Could not insert question 5");
        }
    }
    // Makes sure user enters a valid option, if not they are asked to try again.
    private boolean isValid(String answer, boolean isMainMenu) {

        if (isMainMenu) {
            switch (answer) {
                case "1", "2", "3", "4" -> {
                    return true;
                }
                default -> {
                    System.out.println("Please enter a valid number.");
                    printMainMenu();
                    return false;
                }
            }
        }
        else {
            switch (answer) {
                case "1", "2", "3" -> {
                    return true;
                }
                default -> {
                    System.out.println("Please enter a valid number.");
                    printHighScoreMenu();
                    return false;
                }
            }
        }
    }
}

