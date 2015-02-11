package com.example.databaseapp.helper;

import java.io.IOException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.databaseapp.model.Answer;
import com.example.databaseapp.model.AnswerType;
import com.example.databaseapp.model.Question;

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 

public class DatabaseAdapter  
{ 

	//	// Database name
	//	private static String DB_NAME ="Questionnaire.SQLite";

	private static final String TABLE_QUESTION = "Question";
	private static final String TABLE_ANSWER_TYPE = "AnswerType";
	private static final String TABLE_ANSWER = "Answer";

	// Common column names
	private static final String KEY_ID = "id";

	// Question Table - column names
	private static final String KEY_QUESTION = "question";
	private static final String KEY_ANSWER_TYPE_ID = "answer_type_id";

	// Answer Table - column names
	private static final String KEY_SORT_ORDER = "sort_order";
	private static final String KEY_QUESTION_ID = "question_id";
	private static final String KEY_ANSWER = "answer";

	// AnswerType Table - column names
	private static final String KEY_ANSWER_TYPE = "answer_type";


	protected static final String TAG = "DataAdapter"; 

	private final Context mContext; 
	private SQLiteDatabase db; 
	private DataBaseHelper mDbHelper; 

	public DatabaseAdapter(Context context)  
	{ 
		this.mContext = context; 
		mDbHelper = new DataBaseHelper(mContext); 
	} 

	public DatabaseAdapter createDatabase() throws SQLException  
	{ 
		try  
		{ 
			mDbHelper.createDataBase(); 
		}  
		catch (IOException mIOException)  
		{ 
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
			throw new Error("UnableToCreateDatabase"); 
		} 
		return this; 
	}

	public DatabaseAdapter open() throws SQLException  
	{ 
		try  
		{ 
			mDbHelper.openDataBase(); 
			mDbHelper.close(); 
			db = mDbHelper.getReadableDatabase(); 
		}  
		catch (SQLException mSQLException)  
		{ 
			Log.e(TAG, "open >>"+ mSQLException.toString()); 
			throw mSQLException; 
		} 
		return this; 
	} 

	public void close()  
	{ 
		mDbHelper.close(); 
	} 

	public Cursor getTestData() 
	{ 
		try 
		{ 
			String sql ="SELECT * FROM Answer"; 

			Cursor mCur = db.rawQuery(sql, null); 
			if (mCur!=null) 
			{ 
				mCur.moveToNext(); 
			} 
			return mCur; 
		} 
		catch (SQLException mSQLException)  
		{ 
			Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
			throw mSQLException; 
		} 
	}

	public boolean SaveEmployee(String name, String email) 
	{
		try
		{
			ContentValues cv = new ContentValues();
			cv.put("Name", name);
			cv.put("Email", email);

			db.insert("Employees", null, cv);

			Log.d("SaveEmployee", "informationsaved");
			return true;
		}
		catch(Exception ex)
		{
			Log.d("SaveEmployee", ex.toString());
			return false;
		}
	}

	// ------------------------ "Question" table methods ----------------//

	/**
	 * get single question
	 */
	public Question getQuestion(long question_id) {

		String selectQuery = "SELECT  * FROM " + TABLE_QUESTION + " WHERE "
				+ KEY_ID + " = " + question_id;

		Log.e(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		Question qs = new Question();
		qs.setId(c.getInt(c.getColumnIndex(KEY_ID)));
		qs.setQuestion((c.getString(c.getColumnIndex(KEY_QUESTION))));
		qs.setAnswer_type_id(c.getInt(c.getColumnIndex(KEY_ANSWER_TYPE_ID)));

		return qs;
	}

	/**
	 * getting all questions
	 * */
	public List<Question> getAllQuestions() {
		List<Question> questions = new ArrayList<Question>();
		String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;

		Log.e(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Question qs = new Question();
				qs.setId(c.getInt(c.getColumnIndex(KEY_ID)));
				qs.setQuestion((c.getString(c.getColumnIndex(KEY_QUESTION))));
				qs.setAnswer_type_id(c.getInt(c.getColumnIndex(KEY_ANSWER_TYPE_ID)));

				// adding to todo list
				questions.add(qs);
			} while (c.moveToNext());
		}

		return questions;
	}

	/**
	 * getting questions count
	 */
	public int getQuestionsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_QUESTION;
		
		Cursor cursor = db.rawQuery(countQuery, null);

		int count = cursor.getCount();
		cursor.close();

		return count;
	}

	// ------------------------ "AnswerType" table methods ----------------//

	/**
	 * getting all AnswerType
	 * */
	public List<AnswerType> getAllAnswerType() {
		List<AnswerType> answerTypes = new ArrayList<AnswerType>();
		String selectQuery = "SELECT  * FROM " + TABLE_ANSWER_TYPE;

		Log.e(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				AnswerType at = new AnswerType();
				at.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				at.setAnswer_type(c.getString(c.getColumnIndex(KEY_ANSWER_TYPE)));

				// adding to tags list
				answerTypes.add(at);
			} while (c.moveToNext());
		}
		return answerTypes;
	}

	// ------------------------ "Answer" table methods ----------------//

	/**
	 * getting all Answer
	 * */
	public List<Answer> getAllAnswers() {
		List<Answer> answers = new ArrayList<Answer>();
		String selectQuery = "SELECT  * FROM " + TABLE_ANSWER;

		Log.e(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Answer ans = new Answer();
				ans.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				ans.setQuestion_id(c.getInt((c.getColumnIndex(KEY_QUESTION_ID))));
				ans.setSort_order(c.getInt((c.getColumnIndex(KEY_SORT_ORDER))));
				ans.setAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));

				// adding to tags list
				answers.add(ans);
			} while (c.moveToNext());
		}
		return answers;
	}

	/**
	 * getting Answers with particular question_id
	 * */
	public List<Answer> getAnswersWithQuestionId(int question_id) {
		List<Answer> answers = new ArrayList<Answer>();
		String selectQuery = "SELECT  * FROM " + TABLE_ANSWER + " WHERE " + KEY_QUESTION_ID + " = " + question_id;

		Log.e(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Answer ans = new Answer();
				ans.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				ans.setQuestion_id(c.getInt((c.getColumnIndex(KEY_QUESTION_ID))));
				ans.setSort_order(c.getInt((c.getColumnIndex(KEY_SORT_ORDER))));
				ans.setAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));

				// adding to tags list
				answers.add(ans);
			} while (c.moveToNext());
		}
		return answers;
	}
	
	/**
	 * getting Answer with particular answer_id
	 * */
	public String getAnswerWithAnswerId(int answer_id) {
		
		String selectQuery = "SELECT  * FROM " + TABLE_ANSWER + " WHERE " + KEY_ID + " = " + answer_id;

		Log.e(TAG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);
		
		String ans = null;
		
		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				
				
				ans = c.getString(c.getColumnIndex(KEY_ANSWER));

				
			} while (c.moveToNext());
		}
		return ans;
	}

	/**
	 * get datetime
	 * */
	public String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}


} 

