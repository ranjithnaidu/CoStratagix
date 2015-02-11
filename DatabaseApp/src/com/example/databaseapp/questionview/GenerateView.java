package com.example.databaseapp.questionview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.databaseapp.R;
import com.example.databaseapp.appdata.Singleton;
import com.example.databaseapp.helper.DataBaseHelper;
import com.example.databaseapp.helper.DatabaseAdapter;
import com.example.databaseapp.model.Answer;
import com.example.databaseapp.utils.ComparatorSortId;
import com.example.databaseapp.viewpager.SpinAdapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class GenerateView {

	DatabaseAdapter db;
	DataBaseHelper rdb;
	Context context;

	LayoutInflater inflater;
	LinearLayout mainLayout;
	boolean isLast;

	public GenerateView(Context context, View v, boolean isLast) {
		// TODO Auto-generated constructor stub
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.isLast = isLast;
		mainLayout = (LinearLayout) v.findViewById(R.id.answer_layout);
		rdb = new DataBaseHelper(context);
		db = new DatabaseAdapter(context);
		db.createDatabase();       
		db.open();
	}


	LinearLayout generateView(int question_id, int answer_type_id)
	{
		View v = inflater.inflate(R.layout.answers_layout, null);

		LinearLayout mainLayout = (LinearLayout) v.findViewById(R.id.answer_layout);
		mainLayout.setGravity(Gravity.CENTER);

		final LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		List<Answer> answers = db.getAnswersWithQuestionId(question_id);
		Collections.sort(answers,new ComparatorSortId());

		switch (answer_type_id) {
		case 1: 
			final EditText textView = new EditText(context);

			textView.setId(1000);
			textView.setLayoutParams(viewParams);
			textView.addTextChangedListener(new TextWatcher() {

				public void afterTextChanged(Editable s) {
				}

				public void beforeTextChanged(CharSequence s, int start, 
						int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, 
						int before, int count) {
					Singleton.getInstance().setName(s.toString());
				}
			});

			mainLayout.addView(textView);

			break;
		case 2:
			mainLayout.addView(createRadioButton(answers.size(), answers));
			break;
		case 3:
			mainLayout.addView(createCheckBox(answers.size(), answers));
			break;
		case 4:
			mainLayout.addView(createSpinner(answers.size(), answers));
			break;
		}

		if(!isLast){
			return mainLayout;
		}else{
			mainLayout.addView(createSubmitButton());
			return mainLayout;
		}
	}

	private Button createSubmitButton() {
		// TODO Auto-generated method stub
		Button submit = new Button(context);
		submit.setText("Submit");
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				boolean bo = rdb.insertResponse(Singleton.getInstance().getName(),
						Singleton.getInstance().getGenderId(),
						Singleton.getInstance().getCountryId(),
						Singleton.getInstance().getAdId(),
						db.getDateTime());
				if(bo)
				{
					Singleton.getInstance().clear();
				}
			}
		});
		return submit;
	}

	private Spinner createSpinner(int size, List<Answer> answers) {
		// TODO Auto-generated method stub
		Spinner spinner = new Spinner(context);
		ArrayList<String> spinnerArray = new ArrayList<String>();
		for(int i=0; i<size; i++){
			Answer ans = answers.get(i);
			spinnerArray.add(ans.getAnswer());
		}

		final SpinAdapter adapter = new SpinAdapter(context,
				android.R.layout.simple_spinner_item,
				answers);

		spinner.setAdapter(adapter); // Set the custom adapter to the spinner
		// You can create an anonymous listener to handle the event when is selected an spinner item
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {

				Answer user = adapter.getItem(position);

				Singleton.getInstance().setCountryId(user.getId());

			}
			@Override
			public void onNothingSelected(AdapterView<?> adapter) {  }
		});
		return spinner;
	}


	private View createRadioButton(int no,List<Answer> answers) {
		final RadioButton[] rb = new RadioButton[no];
		RadioGroup rg = new RadioGroup(context); 
		rg.setOrientation(RadioGroup.VERTICAL);
		for(int i=0; i<no; i++){
			Answer ans = answers.get(i);
			rb[i]  = new RadioButton(context);
			rg.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout
			rb[i].setText(ans.getAnswer());
			rb[i].setId(ans.getId());
		}

		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if(checkedId<5)
				{
					Singleton.getInstance().setGenderId(checkedId);
				}else{
					Singleton.getInstance().setAdId(checkedId);
				}
			}
		});
		return rg;
	}

	private LinearLayout createCheckBox(int no,List<Answer> answers) {

		View v = inflater.inflate(R.layout.answers_layout, null);

		LinearLayout subLayout = (LinearLayout) v.findViewById(R.id.answer_layout);

		for(int i=0; i<no; i++){
			Answer ans = answers.get(i);
			final CheckBox check  = new CheckBox(context);
			subLayout.addView(check); 
			check.setText(ans.getAnswer());
			check.setId(ans.getId());
			check.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(check.isChecked())
					{
						Singleton.getInstance().addToList(check.getId());
					}else {
						Singleton.getInstance().removeFromList(new Integer(check.getId()));
					}
				}
			});
		}
		return subLayout;
	}
}
