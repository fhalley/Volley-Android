package com.android.volley;

public class Profile {
	String Phone, Full_Name, Password, Code;
	
	public Profile (){
		Phone = "";
		Full_Name = "";
		Password = "";
		Code = "";
	}
	
	public void Set (String _phone, String _full_name, String _password, String _code){
		Phone = _phone;
		Full_Name = _full_name;
		Password = _password;
		Code = _code;
	}

}
