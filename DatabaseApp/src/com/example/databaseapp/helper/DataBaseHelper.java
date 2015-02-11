package com.example.databaseapp.helper;

import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.util.ArrayList;
import java.util.List;
import com.example.databaseapp.model.Response;

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor;
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper; 
import android.util.Log; 

public class DataBaseHelper extends SQLiteOpenHelper 
{ 
	// Database name
	private static String DB_NAME ="Questionnaire.SQLite";

	private static final String TABLE_RESPONSE = "Response";

	// Common column names
	private static final String KEY_ID = "id";

	// Response Table - column names
	private static final String KEY_NAME = "name";
	private static final String KEY_GENDER_ID = "genderid";
	private static final String KEY_CHECK_ID = "checkid";
	private static final String KEY_COUNTRY_ID = "countryid";
	private static final String KEY_AD_ID = "adid";
	private static final String KEY_TIME = "datetime";

	private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window 
	//destination path (location) of our database on device 
	private static String DB_PATH = "";  

	private SQLiteDatabase mDataBase;  
	private final Context mContext; 

	public DataBaseHelper(Context context)  
	{ 
		super(context, DB_NAME, null, 1);// 1? its Database Version 
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"; 
		this.mContext = context; 
	}    

	public void createDataBase() throws IOException 
	{ 
		//If database not exists copy it from the assets 

		boolean mDataBaseExist = checkDataBase(); 
		if(!mDataBaseExist) 
		{ 
			this.getReadableDatabase(); 
			this.close(); 
			try  
			{ 
				//Copy the database from assests 
				copyDataBase(); 
				Log.e(TAG, "createDatabase database created"); 
			}  
			catch (IOException mIOException)  
			{ 
				throw new Error("ErrorCopyingDataBase"); 
			} 
		}
	} 
	//Check that the database exists here: /data/data/your package/databases/Da Name 
	private boolean checkDataBase() 
	{ 
		File dbFile = new File(DB_PATH + DB_NAME); 
		//Log.v("dbFile", dbFile + "   "+ dbFile.exists()); 
		return dbFile.exists(); 
	} 

	//Copy the database from assets 
	private void copyDataBase() throws IOException 
	{ 
		InputStream mInput = mContext.getAssets().open(DB_NAME); 
		String outFileName = DB_PATH + DB_NAME; 
		OutputStream mOutput = new FileOutputStream(outFileName); 
		byte[] mBuffer = new byte[1024]; 
		int mLength; 
		while ((mLength = mInput.read(mBuffer))>0) 
		{ 
			mOutput.write(mBuffer, 0, mLength); 
		} 
		mOutput.flush(); 
		mOutput.close(); 
		mInput.close(); 
	} 

	//Open the database, so we can query it 
	public boolean openDataBase() throws SQLException 
	{ 
		String mPath = DB_PATH + DB_NAME; 
		//Log.v("mPath", mPath); 
		mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
		 
		return mDataBase != null; 
	} 

	@Override 
	public synchronized void close()  
	{ 
		if(mDataBase != null) 
			mDataBase.close(); 
		super.close(); 
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
		String query = "create table "  + TABLE_RESPONSE  +
				"("+ KEY_ID + " integer primary key, " + KEY_NAME + " text, " +
				KEY_GENDER_ID + " integer, " + KEY_COUNTRY_ID + " integer, " +
				KEY_AD_ID + " integer, " + KEY_TIME + " text);";

		arg0.execSQL(query);
		Log.i("query", query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public boolean insertResponse  (String name, int genderid, int countryid, int adid, String date)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(KEY_NAME, name);
		
		contentValues.put(KEY_GENDER_ID, genderid);
		
		contentValues.put(KEY_COUNTRY_ID, countryid);
		
		contentValues.put(KEY_AD_ID, adid);
		
		contentValues.put(KEY_TIME, date);

		db.insert(TABLE_RESPONSE, null, contentValues);
		return true;
	}


	// Getting All responses
	public List<Response> getAllResponses() {
		List<Response> responsesList = new ArrayList<Response>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_RESPONSE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Response response = new Response();
				response.setId(Integer.parseInt(cursor.getString(0)));
				response.setName(cursor.getString(1));
				response.setGenderId(cursor.getInt(2));
				response.setCountryId(cursor.getInt(3));
				response.setAdId(cursor.getInt(4));
				response.setDateTime(cursor.getString(5));

				responsesList.add(response);
			} while (cursor.moveToNext());
		}
		return responsesList;
	}


} 


