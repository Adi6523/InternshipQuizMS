package com.app.model;

public class Quiz_attempts {
	
	private int attempt_id;
	private int quiz_id;
	private int student_id;
	private int final_score;
	private int total_questions;
	
	public Quiz_attempts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quiz_attempts(int attempt_id, int quiz_id, int student_id, int final_score, int total_questions) {
		this.attempt_id = attempt_id;
		this.quiz_id = quiz_id;
		this.student_id = student_id;
		this.final_score = final_score;
		this.total_questions = total_questions;
	}

	public int getAttempt_id() {
		return attempt_id;
	}

	public void setAttempt_id(int attempt_id) {
		this.attempt_id = attempt_id;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getFinal_score() {
		return final_score;
	}

	public void setFinal_score(int final_score) {
		this.final_score = final_score;
	}

	public int getTotal_questions() {
		return total_questions;
	}

	public void setTotal_questions(int total_questions) {
		this.total_questions = total_questions;
	}
	

}
