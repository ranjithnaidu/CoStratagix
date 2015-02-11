package com.example.databaseapp.appdata;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
	private static Singleton mInstance = null;

	private String name;
	private int genderId;
	List<Integer> checkId;
	private int countryId;
	private int adId;
	private String dateTime;

	private Singleton(){
		name = null;
		genderId = 0;
		checkId = new ArrayList<Integer>();
		countryId = 0;
		adId = 0;
		dateTime = null;
	}	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getGenderId() {
		return genderId;
	}



	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}



	public List<Integer> getCheckId() {
		return checkId;
	}



	public void setCheckId(List<Integer> checkId) {
		this.checkId = checkId;
	}



	public int getCountryId() {
		return countryId;
	}



	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}



	public int getAdId() {
		return adId;
	}



	public void setAdId(int adId) {
		this.adId = adId;
	}



	public String getDateTime() {
		return dateTime;
	}



	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}



	public void addToList(int id)
	{
		checkId.add(id);
	}
	
	public void removeFromList(Integer i)
	{
		checkId.remove(i);
	}


	public static Singleton getInstance(){
		if(mInstance == null)
		{
			mInstance = new Singleton();
		}
		return mInstance;
	}
	
	public void clear()
	{
		name = null;
		genderId = 0;
		checkId.clear();
		countryId = 0;
		adId = 0;
		dateTime = null;
	}
}