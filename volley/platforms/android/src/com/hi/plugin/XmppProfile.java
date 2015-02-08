package com.hi.plugin;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class XmppProfile {
	private static final String MyPREFERENCES = "com.hi.plugin";
	final String profile = "nulled";
	public static Boolean SetHiProfile(String fullname,String username,String password,String i ,Context context){
		Boolean result = false;
		SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);	
		Editor editor = sharedpreferences.edit();

		editor.putString("fullname", fullname);
		editor.putString("username", username);
		editor.putString("password", password);
		editor.putString("verifiy", i);
		try{
		editor.commit();
		result = true;
		}catch(Exception ex){
			Log.i("XmppPluginMainClass.Exception",ex.getMessage());
		}
		return result;
		
	}
	public String GetHiProfile(Context context) throws JSONException{
		//String profile = "nulled";
		SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);	
		String Fullname = sharedpreferences.getString("fullname", "");
		String Username = sharedpreferences.getString("username", "");
		String Password = sharedpreferences.getString("password", "");
		String Verify   = sharedpreferences.getString("verify", "");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("fullname", Fullname);
		jsonObj.put("username", Username);
		jsonObj.put("password", Password);
		jsonObj.put("verify", Verify);
		String profile =jsonObj.toString();
		return profile;
	}
}
