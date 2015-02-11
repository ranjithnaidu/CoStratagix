package com.example.databaseapp.model;

public class Answer {

	private int id;
	private int sort_order;
	private int question_id;
	private String answer;

	public Answer()
	{

	}

	public Answer(int id, int sort_order, int question_id, String answer) {
		super();
		this.id = id;
		this.sort_order = sort_order;
		this.question_id = question_id;
		this.answer = answer;
	}
	public int getSort_order() {
		return sort_order;
	}

	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
