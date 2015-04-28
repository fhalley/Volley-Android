package com.android.volley;

public class MessageObj {
	
	
	private Integer id_chat;
	private String user;
	private String friend;
	private String message;
	private String date_register;
	private String hour; 
	
	public MessageObj(Integer _id_chat ,String _user, String _message, String _date, String _hour){
		this.id_chat = _id_chat;
		this.user = _user;
		this.message = _message;
		this.date_register = _date;
		this.hour = _hour;
		
	}
	public void setId(Integer _id_chat){
		this.id_chat = _id_chat;
	}
	public void setUsername(String _user){
		this.user = _user;
	}
	public void setMessage(String _message){
		this.message = _message;
	}
	public void setDate_register(String _date){
		this.date_register = _date;
	}
	public void setHour(String _hour){
		this.hour = _hour;
	}
	
	public Integer getId(){return id_chat;}
	public String getUsername(){return user;}
	public String getMessage(){return message;}
	public String getDate_register(){return date_register;}
	public String getHour(){return hour;}
	
	

}
