CREATE DATABASE quiz_system;
USE quiz_system;


CREATE TABLE users (
    user_id INT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('student', 'teacher', 'admin') DEFAULT 'student',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE quizzes (
    quiz_id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    creator_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creator_id) REFERENCES users(user_id)
);


CREATE TABLE questions (
    question_id INT PRIMARY KEY,
    quiz_id INT NOT NULL,
    question_text TEXT NOT NULL,
    option_a VARCHAR(500) NOT NULL,
    option_b VARCHAR(500) NOT NULL,
    option_c VARCHAR(500) NOT NULL,
    option_d VARCHAR(500) NOT NULL,
    correct_option CHAR(1)
        CHECK (correct_option IN ('A','B','C','D')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id)
);


CREATE TABLE quiz_attempts (
    attempt_id INT PRIMARY KEY,
    quiz_id INT NOT NULL,
    student_id INT NOT NULL,
    final_score INT,
    total_questions INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id),
    FOREIGN KEY (student_id) REFERENCES users(user_id)
);


SHOW TABLES;


DESC users;
DESC quizzes;
DESC questions;
DESC quiz_attempts;
