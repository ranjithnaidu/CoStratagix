package com.example.databaseapp.utils;

import java.util.Comparator;

import com.example.databaseapp.model.Answer;

public class ComparatorSortId implements Comparator<Answer>{

	@Override
	public int compare(Answer arg0, Answer arg1) {
		// TODO Auto-generated method stub
		int returnVal = 0;

		if(arg0.getSort_order() < arg1.getSort_order()){
			returnVal =  -1;
		}else if(arg0.getSort_order() > arg1.getSort_order()){
			returnVal =  1;
		}else if(arg0.getSort_order() == arg1.getSort_order()){
			returnVal =  0;
		}
		return returnVal;

	}
}