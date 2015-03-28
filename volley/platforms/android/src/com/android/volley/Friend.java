package com.android.volley;

public class Friend {

	String Number;
	String Full_Name;
	
	public void Friend(String _number, String _full_name){
		Number = _number;
		Full_Name = _full_name;
	}
	
	public void setNumber(String _number){
		Number = _number;
	}
	
	public void setFull_Name(String _full_name){
		Full_Name = _full_name;
	}
	
	public String getUsername(){return Number;}
	public String getFriend(){return Full_Name;}


}
