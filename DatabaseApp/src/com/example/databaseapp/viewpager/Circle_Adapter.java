package com.example.databaseapp.viewpager;

import com.example.databaseapp.R;
import com.example.databaseapp.questionview.CreateView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Circle_Adapter extends BaseAdapter
{
	//	private static final int VIEW1 = 0;
	//	private static final int VIEW2 = 1;
	//	private static final int VIEW3 = 2;
	//	private static final int VIEW4 = 3;
	//	private static final int VIEW5 = 4;
	private static int VIEW_MAX_COUNT ;
	private LayoutInflater mInflater;
	Context context;


	public Circle_Adapter(Context context,int tabCount) 
	{
		this.context = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		VIEW_MAX_COUNT = tabCount;
		Log.i("tabCount", VIEW_MAX_COUNT + "");
	}

	@Override
	public int getItemViewType(int position) 
	{
		Log.i("getItemViewType", "Here");
		return position;
	}

	@Override
	public int getViewTypeCount() 
	{
		Log.i("getItemViewType", "Here");
		return VIEW_MAX_COUNT;
	}

	@Override
	public int getCount() 
	{
		Log.i("getCount", "Here");
		return VIEW_MAX_COUNT;
	}

	@Override
	public Object getItem(int position) 
	{
		Log.i("getItem", "Here");
		return position;
	}

	@Override
	public long getItemId(int position) 
	{
		Log.i("getItemId", "Here");
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		int view = getItemViewType(position);
		if (convertView == null) 
		{
			Log.i("getView", "if convertView == null here");

			//			convertView = mInflater.inflate(R.layout.demo_2, null);
			CreateView createView;
			if((view+1) == VIEW_MAX_COUNT)
			{
				createView = new CreateView(context, view+1,true);
				Log.i("View", (view+1) + "");
			}else{
				createView = new CreateView(context, view+1);
				Log.i("View", (view+1) + "");
			}
			convertView = createView.createView();

		}
		Log.i("getItemViewType", "convertView != null");
		return convertView;
	}
}
