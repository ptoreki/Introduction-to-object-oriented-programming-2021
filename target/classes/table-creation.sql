USE quizdb;

CREATE TABLE `binaryquiz` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `question` varchar(45) DEFAULT NULL,
                              `correctAnswer` varchar(45) DEFAULT NULL,
                              PRIMARY KEY (`id`)
);

CREATE TABLE `multichoicequiz` (
                                   `id` int NOT NULL AUTO_INCREMENT,
                                   `question` varchar(120) DEFAULT NULL,
                                   `answerA` varchar(120) DEFAULT NULL,
                                   `answerB` varchar(120) DEFAULT NULL,
                                   `answerC` varchar(120) DEFAULT NULL,
                                   `answerD` varchar(120) DEFAULT NULL,
                                   `correctAnswer` varchar(120) DEFAULT NULL,
                                   PRIMARY KEY (`id`)
);

CREATE TABLE `scorehistory` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `score` int DEFAULT NULL,
                                `topic` varchar(45) DEFAULT NULL,
                                `user` varchar(45) DEFAULT NULL,
                                PRIMARY KEY (`id`)
);


