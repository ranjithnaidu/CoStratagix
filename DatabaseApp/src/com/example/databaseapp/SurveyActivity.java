package com.example.databaseapp;

import java.util.List;

import com.example.databaseapp.helper.DatabaseAdapter;
import com.example.databaseapp.viewpager.CircleFlowIndicator;
import com.example.databaseapp.viewpager.Circle_Adapter;
import com.example.databaseapp.viewpager.ViewFlow_Master;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class SurveyActivity extends Activity{

	private ViewFlow_Master viewFlow;

	DatabaseAdapter db;
	int tabCount;
	
	protected String mString;
	protected int genderId;
	List<Integer> checkId;
	protected int spinnerId;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflow_circle_layout);
		
//		InitialiseView ini = new InitialiseView();
//		ini.execute();

		db = new DatabaseAdapter(getApplicationContext());
		db.createDatabase();       
		db.open(); 

		getCount();

		viewFlow = (ViewFlow_Master) findViewById(R.id.circle_viewflow);
		Circle_Adapter adapter = new Circle_Adapter(this,tabCount);
		viewFlow.setAdapter(adapter);
		CircleFlowIndicator indicator = (CircleFlowIndicator) findViewById(R.id.circle_viewflowindicator);		
		viewFlow.setFlowIndicator(indicator);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{
		super.onConfigurationChanged(newConfig);
		viewFlow.onConfigurationChanged(newConfig);
	}

	private void getCount() {
		// TODO Auto-generated method stub
		
		tabCount = db.getQuestionsCount();
		Log.i("tabs", tabCount+"");

	}
	
	class InitialiseView extends AsyncTask<Void, Void, Void>
	{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			db = new DatabaseAdapter(getApplicationContext());
			db.createDatabase();       
			db.open(); 
			
			getCount();			
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub

			viewFlow = (ViewFlow_Master) findViewById(R.id.circle_viewflow);
			Circle_Adapter adapter = new Circle_Adapter(getApplicationContext(),tabCount);
			viewFlow.setAdapter(adapter);
			CircleFlowIndicator indicator = (CircleFlowIndicator) findViewById(R.id.circle_viewflowindicator);		
			viewFlow.setFlowIndicator(indicator);
			
			return null;
		}
		
	}



	//	DataBaseHelper db;
	//	int tabCount;
	//
	//	@Override
	//	protected void onCreate(Bundle savedInstanceState) {
	//		super.onCreate(savedInstanceState);
	//		setContentView(R.layout.activity_survey);
	//
	//		getCount();
	//
	//		getQuestions();
	//
	//		initialise();
	//
	//	}
	//
	//	private void getCount() {
	//		// TODO Auto-generated method stub
	//		db = new DataBaseHelper(getApplicationContext());
	//		tabCount = db.getQuestionsCount();
	//
	//	}
	//
	//	private void initialise() {
	//		// TODO Auto-generated method stub
	//
	//	}
	//
	//	private void getQuestions() {
	//		// TODO Auto-generated method stub
	//		db = new DataBaseHelper(getApplicationContext());
	//
	//		List<Question> questions = db.getAllQuestions();
	//
	//		for(Question qs:questions)
	//		{
	//			Log.i("answer_typr_id", qs.getAnswer_type_id() +"");
	//			Log.i("id", qs.getId() +"");
	//			Log.i("question", qs.getQuestion() +"");
	//		}
	//	}
}
