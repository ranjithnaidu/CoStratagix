package com.example.databaseapp;

import java.util.List;

import com.example.databaseapp.helper.DataBaseHelper;
import com.example.databaseapp.model.Response;
import com.example.databaseapp.viewpager.CustomListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ResponsesActivity extends Activity implements OnItemClickListener{

	List<Response> responses;
	ListView list;
	CustomListAdapter adapter;
	
	DataBaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_responses);
		
		db = new DataBaseHelper(getApplicationContext());
		
		list = (ListView) findViewById(R.id.listView);
		
		responses = db.getAllResponses();
		
		adapter = new CustomListAdapter(this, responses);
		list.setOnItemClickListener(this);
		list.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Response r = (Response) list.getItemAtPosition(arg2);
		
		Intent i = new Intent(this,DetailedResponseActivity.class);
		i.putExtra("RESPONSE", r);
		startActivity(i);
	}
}
