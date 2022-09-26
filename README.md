# Introduction-to-object-oriented-programming-2021
The exam solution for the course "Introduction to object oriented programming" in Java.

Case QuizGame

I was given a task to make an interactive quiz game application. I had to make on multiplechoice quiz, and one yes/no quiz.
The questions are created and stored in a database, the application should read user input and give response. 
Changing quiz or quitting should be an option. It also includes a scoreboard and history for each user that signed up.

Functionalities I had to include:

- Database and tables
o You will create a database called quizDb.
o You will create two tables that stores Quiz questions. One table is
called multichoiceQuiz, the second table is called binaryQuiz. You shall
insert Quiz questions into tables using JDBC connection.
▪ The multichoiceQuiz table contains following columns for each
record: id; question; answerA; answerB; answerC; answerD;
correctAnswer.
▪ The binaryQuiz table contains following columns for each record:
id; question; correctAnswer.
o You will create a table that stores the score history. You shall insert a
new score record at each round using JDBC connection.
▪ The score table contains following columns for each record: id;
user; score; topic
o Other things you think should be included
- Interactive Java program
o You shall use Java.Util.Scanner to build the interactive QuizGame Java
program.
o You need to sign up as a user before start playing the Quiz game.
o You shall be able to play the QuizGame for unlimited times unless you
want to quit the game.
o You shall be able to choose Quiz topics before start. Once you start,
you are not allowed to switch topic.
o The Quiz game has a scoring system. If your answer is correct, add
one point to the score.
o You will get a final score each time you finish playing a round.
o You will also be displayed with a scoring board each time you finish
playing a round. The scoring board shows the score history where each
record presents “user, score, quiz topic” and listed in an descending
order sorted by score.
o Random questions at each play
