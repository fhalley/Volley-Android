package com.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class VolleyOpenHelper extends SQLiteOpenHelper{

	public VolleyOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
		super(context, nombre, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table dt_users(username text primary key, password text, fullname text, verification_code text)");
		db.execSQL("create table dt_friends(id_friend int autonumeric, friend_username text, name text)");
		
		

		db.execSQL("create table dt_Chats (id_chat integer autonumeric, friend text)");
		db.execSQL("create table dt_Messages(id_chat integer, user text, message text, date_register text, hour text)");
	
	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		db.execSQL("drop table if exists dt_users");
		db.execSQL("drop table if exists dt_friends");
		db.execSQL("drop table if exists dt_Messages");
		db.execSQL("drop table if exists dt_Chats");
		

		db.execSQL("create table dt_users(username text primary key, password text, fullname text, verification_code text)");		
		db.execSQL("create table dt_friends(id_friend int autonumeric, friend_username text, name text)");
		


		db.execSQL("create table dt_Chats (id_chat integer autonumeric, friend text)");
		db.execSQL("create table dt_Messages(id_chat integer, user text, message text, date_register text, hour text)");
		
		
	}	
}
