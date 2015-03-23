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
		//db.execSQL("create table dt_friend_list(id_friendList int autonumeric, friend_username text, username text)");
		//db.execSQL("create table dt_chatMessenger(id_message integer autonumeric, message text, username text,friend_username text, date_register text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		db.execSQL("drop table if exists dt_users");
		//db.execSQL("drop table if exists dt_friend_list");
		//db.execSQL("drop table if exists dt_chatMessenger");
		db.execSQL("create table dt_users(username text primary key, password text, fullname text, verification_code text)");		
		//db.execSQL("create table dt_friend_list(id_friendList int autonumeric, friend_username text , username text)");
		//db.execSQL("create table dt_chatMessenger(id_message integer autonumeric, message text, username tesxt,friend_username text, date_register text)");s
	}	
}
