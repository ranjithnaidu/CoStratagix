package com.example.databaseapp.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable{

	private int id;
	private String name;
	private int genderId;
	private int checkId;
	private int countryId;
	private int adId;
	private String dateTime;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	public Response(int id, String name, int genderId,
			int countryId, int adId, String dateTime) {
		super();
		this.id = id;
		this.name = name;
		this.genderId = genderId;
		this.countryId = countryId;
		this.adId = adId;
		this.dateTime = dateTime;
		
	}
	
	public Response(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        name = data[0];
        dateTime = data[1];
        int[] ids = new int[4];
        in.readIntArray(ids);
        genderId = ids[0];
        checkId = ids[1];
		countryId = ids[2];
		adId = ids[3];
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[] {
                name,
                dateTime
        });
		
		dest.writeIntArray(new int[]{
				genderId,
				checkId,
				countryId,
				adId
		});
	}
	
	public static final Parcelable.Creator<Response>CREATOR = new Parcelable.Creator<Response>() {
		 
        @Override
        public Response createFromParcel(Parcel source) {
            return new Response(source);
        }
 
        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
 
    };
}
