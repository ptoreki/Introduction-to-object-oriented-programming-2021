package pgr112.exam.model;

import pgr112.exam.JDBCOps;
import pgr112.exam.model.Dto.BinaryQuestionDto;
import pgr112.exam.model.Dto.ScoreDto;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BinaryQuiz extends Quiz {

    private String correctAnswer;
    private final ScoreDto score;
    private final Scanner input;

    public BinaryQuiz(ScoreDto score) {
        this.score = score;
        this.score.setTopic("BinaryQuestion");
        input = new Scanner(System.in);
    }

    @Override
    public void showQuestion() {

        JDBCOps jdbcOps = new JDBCOps();
        List<BinaryQuestionDto> questions = jdbcOps.getBinaryQuestions();
        Collections.shuffle(questions); //shuffles questions

        int questionNumber = 1;
        int highScore = score.getScore();

        for (BinaryQuestionDto question : questions) {
            System.out.println("Question " + questionNumber + ": " + question.getQuestion());
            System.out.println("Yes");
            System.out.println("No");

            String userAnswer;

            do { // if user input is invalid, it will keep asking user for a valid answer.
                userAnswer = input.nextLine().toLowerCase();
            } while (!isValid(userAnswer));

            correctAnswer = question.getCorrectAnswer().toLowerCase();

            if (isCorrectAnswer(userAnswer)) {
                highScore++;

                System.out.println("Your answer was correct! Your current score is: " + highScore);
            }
            else {
                System.out.println("Your answer was wrong! Your current score is: " + highScore);
            }

            score.setScore(highScore);
            questionNumber++;
        }
        System.out.println("Your final score is: " + highScore + " out of " +  questions.size());
        jdbcOps.insertScore(score);
        score.setScore(0);
    }

    @Override
    // checks if user answer matches correct answer regardless of case.
    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.toLowerCase().contains(answer);
    }

    // Makes sure user inputs are valid, if not they are asked to enter a valid answer.
    public boolean isValid(String answer) {
        switch (answer) {
            case "yes", "no" -> {
                return true;
            }
            default -> {
                System.out.println("Please enter 'Yes' or 'No'");
                return false;
            }
        }
    }
}
