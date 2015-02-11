package com.example.databaseapp.viewpager;

import com.example.databaseapp.viewpager.ViewFlow_Master.ViewSwitchListener;



public interface FlowIndicator extends ViewSwitchListener 
{
	public void setViewFlow(ViewFlow_Master view);
	public void onScrolled(int h, int v, int oldh, int oldv);
}
