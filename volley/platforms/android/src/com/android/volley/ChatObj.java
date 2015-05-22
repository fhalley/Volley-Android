package com.android.volley;

public class ChatObj {
	
	private Integer id;
	private String friendUser;

	public ChatObj(Integer _id, String _friendUser){
		id = _id;
		friendUser = _friendUser;
	}
	
	public void setId(Integer _id_chat){
		id = _id_chat;
	}
	
	public void setFriendUser(String _friendUser){
		friendUser = _friendUser;
	}

	public Integer getId(){return id;}
	public String getFriendUser(){return friendUser;}
	
}
