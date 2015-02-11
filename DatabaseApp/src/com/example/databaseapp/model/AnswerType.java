package com.example.databaseapp.model;

public class AnswerType {

	private int id;
	private String answer_type;

	public AnswerType()
	{

	}

	public AnswerType(int id, String answer_type) {
		super();
		this.id = id;
		this.answer_type = answer_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswer_type() {
		return answer_type;
	}
	public void setAnswer_type(String answer_type) {
		this.answer_type = answer_type;
	}


}
