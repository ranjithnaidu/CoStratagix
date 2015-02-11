package com.example.databaseapp.model;

public class Question {

	private int id;
	private int answer_type_id;
	private String question;

	// constructors
	public Question() {
	}

	public Question(String question, int answer_type_id) {
		this.question = question;
		this.answer_type_id = answer_type_id;
	}

	public Question(int id, String question, int answer_type_id) {
		this.id = id;
		this.question = question;
		this.answer_type_id = answer_type_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnswer_type_id() {
		return answer_type_id;
	}

	public void setAnswer_type_id(int answer_type_id) {
		this.answer_type_id = answer_type_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}



}