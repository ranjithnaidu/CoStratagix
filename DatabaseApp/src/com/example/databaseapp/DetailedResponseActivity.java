package com.example.databaseapp;

import java.util.List;

import com.example.databaseapp.helper.DatabaseAdapter;
import com.example.databaseapp.model.Question;
import com.example.databaseapp.model.Response;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailedResponseActivity extends Activity{
	
	TextView responses;
	DatabaseAdapter db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_response_details);
		
		db = new DatabaseAdapter(this);
		db.createDatabase();       
		db.open();
		
		final List<Question> questions = db.getAllQuestions();
		
		responses = (TextView) findViewById(R.id.responses);
		
		Bundle data = getIntent().getExtras();
		final Response response = (Response) data.getParcelable("RESPONSE");
		
		Log.i("Name", response.getName());
		Log.i("Gender", response.getGenderId() + "");
		Log.i("check", response.getCheckId() + "");
		Log.i("country", response.getCountryId() + "");
		Log.i("ad", response.getAdId() + "");
		
		new Thread() {
			public void run() {
				setAnswers();
			}

			private void setAnswers() {
				// TODO Auto-generated method stub
				responses.setText(questions.get(0).getQuestion() + "\n");
				responses.append(response.getName() + "\n");
				responses.append(questions.get(1).getQuestion() + "\n");
				responses.append(db.getAnswerWithAnswerId(response.getGenderId()) + "\n");
				responses.append(questions.get(2).getQuestion() + "\n");
				responses.append(db.getAnswerWithAnswerId(response.getCheckId()) + "\n");
				responses.append(questions.get(3).getQuestion() + "\n");
				responses.append(db.getAnswerWithAnswerId(response.getCountryId()) + "\n");
				responses.append(questions.get(4).getQuestion() + "\n");
				responses.append(db.getAnswerWithAnswerId(response.getAdId()) + "\n");
			}
		}.start();
		
		
	}

}
