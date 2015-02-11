package com.example.databaseapp.questionview;

import com.example.databaseapp.R;
import com.example.databaseapp.helper.DatabaseAdapter;
import com.example.databaseapp.model.Question;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreateView {

	DatabaseAdapter db;
	
	private LayoutInflater inflater;
	Context context;
	private long question_id;
	boolean isLast = false;
//	View view;

	public CreateView(Context context, long question_id) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.question_id = question_id;
//		view = convertView;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		db = new DatabaseAdapter(context);
		db.createDatabase();       
		db.open(); 
	}
	
	public CreateView(Context context, long question_id, boolean isLast) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.question_id = question_id;
		this.isLast = isLast;  
//		view = convertView;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		db = new DatabaseAdapter(context);
		db.createDatabase();       
		db.open(); 
	}

	public View createView() {
		// TODO Auto-generated method stub
		Question question = db.getQuestion(question_id);
		View v = inflater.inflate(R.layout.question, null);
		
		int answer_type_id = question.getAnswer_type_id();
		String strQuestion = question.getQuestion();
		int question_id = question.getId();
		
		GenerateView gen = new GenerateView(context,v,isLast);
		LinearLayout ans = gen.generateView(question_id,answer_type_id);
		
		v = inflater.inflate(R.layout.question, ans); 
		
		Log.i("answer_type_id", answer_type_id+"");
		Log.i("strQuestion", strQuestion+"");
		Log.i("question_id", question_id+"");
		
		
		TextView qs = (TextView) v.findViewById(R.id.question);
		qs.setText(strQuestion);
		
		db.close();
		return v;
	}

}
