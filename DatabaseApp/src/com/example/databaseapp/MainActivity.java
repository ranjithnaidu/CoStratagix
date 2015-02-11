package com.example.databaseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	Button surveyActivity,responsesActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		surveyActivity = (Button) findViewById(R.id.submitSurvey);
		responsesActivity = (Button) findViewById(R.id.viewResponses);
		
		surveyActivity.setOnClickListener(this);
		responsesActivity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.submitSurvey:
			Intent surveyActivityIntent = new Intent(this,SurveyActivity.class);
			startActivity(surveyActivityIntent);
			break;

		case R.id.viewResponses:
			Intent responsesActivityIntent = new Intent(this,ResponsesActivity.class);
			startActivity(responsesActivityIntent);
			break;
		}
	}
}
