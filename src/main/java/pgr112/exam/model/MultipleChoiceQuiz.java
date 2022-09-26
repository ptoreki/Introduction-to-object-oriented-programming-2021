package pgr112.exam.model;

import pgr112.exam.JDBCOps;
import pgr112.exam.model.Dto.MultiChoiceQuestionDto;
import pgr112.exam.model.Dto.ScoreDto;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MultipleChoiceQuiz extends Quiz {

    private String correctAnswer;
    private final ScoreDto score;
    private final Scanner input;

    public MultipleChoiceQuiz(ScoreDto score) {
        this.score = score;
        this.score.setTopic("MultipleChoice");
        input = new Scanner(System.in);
    }

    @Override
    public void showQuestion() {
        JDBCOps jdbcOps = new JDBCOps();
        List<MultiChoiceQuestionDto> questions = jdbcOps.getMultipleChoiceQuestions();
        Collections.shuffle(questions);

        int questionNumber = 1;
        int highScore = score.getScore();

        for (MultiChoiceQuestionDto question : questions) {

            System.out.println("Question " + questionNumber + ": " + question.getQuestion());
            System.out.println(question.getAnswerA());
            System.out.println(question.getAnswerB());
            System.out.println(question.getAnswerC());
            System.out.println(question.getAnswerD());

            String userAnswer;

            do {
                userAnswer = input.nextLine().toLowerCase();
            } while (!isValid(userAnswer));

            correctAnswer = question.getCorrectAnswer();

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
    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.toLowerCase().contains(answer);
    }

    private boolean isValid(String answer) {
        switch (answer) {
            case "a", "b", "c", "d" -> {
                return true;
            }
            default -> {
                System.out.println("Please enter one of the alternative letters.");
                return false;
            }
        }
    }
}
