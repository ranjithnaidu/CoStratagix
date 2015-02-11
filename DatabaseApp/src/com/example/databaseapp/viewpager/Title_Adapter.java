package com.example.databaseapp.viewpager;

import android.content.Context;
import android.view.LayoutInflater;

public class Title_Adapter implements TitleProvider
{
	
	private static final int VIEW5 = 4;
	private static final int VIEW_MAX_COUNT = VIEW5 + 1;
	private final String[] names = {"TitleFlow-1", "TitleFlow-2", "TitleFlow-3", "TitleFlow-4", "TitleFlow-5"};
	
	public static int SIDE_BUFFER = VIEW_MAX_COUNT - 1;
	public static int SIDE_BUFFER_GLOBAL = VIEW_MAX_COUNT - 2;
	
	private LayoutInflater mInflater;

	public Title_Adapter(Context context) 
	{
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public String getTitle(int position) 
	{
		return names[position];
	}
}
