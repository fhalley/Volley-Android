package com.android.database;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Friend;
import com.android.volley.Profile;

import android.R.fraction;
import android.R.string;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class db_volley {
	
	private SQLiteDatabase db;
	private VolleyOpenHelper dbHelper;
	
	public String _usuario,_pass,_val;
	public int cant;
	
	public ArrayList<String> list_friends = new ArrayList<String>();


	public db_volley (Context context){
	
	dbHelper = new VolleyOpenHelper(context,"volley",null,1);	
		
	}
	
	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		//SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}

	public void insertar(String username, String password, String fullname, String verificacion){
		//SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
		open();
		ContentValues registro = new ContentValues();
		
		registro.put("username", username.toString());
	    registro.put("password", password.toString());
	    registro.put("fullname", fullname.toString());
	    registro.put("verification_code",verificacion.toString() );
	    db.insert("dt_users", null, registro);
	    close();
	    //DatabaseManager.getInstance().closeDatabase();
	}
	public void actualizar(String fullname, String username, String password, String verificacion){
		
		open();
	    ContentValues actualizar = new ContentValues();
		
		actualizar.put("fullname", fullname.toString());
		actualizar.put("password", password.toString());
		actualizar.put("verification_code", verificacion.toString());
	    cant = db.update("dt_users", actualizar,"username='"+ username.toString() +"'", null);
		close();
	}
	
	public void eliminar(String nombre){
		
		open();
		cant = db.delete("dt_users", "fullname ='"+nombre.toString()+"'", null);
		close();	
	}
	
	public void consultas(String nombre){
		Profile pro = null;

		
		open();
		Cursor fila = db.rawQuery("select * from dt_users where username like '"+
		  nombre.toString()+ "'", null);
		
		if (fila.moveToFirst()) {
			  
			 _usuario = fila.getString(0);
			 _pass = fila.getString(1);
			 _val =  fila.getString(3);
			 pro.Set(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3));
		}
		else
		{
			_usuario = "es";
			_pass = "un";
			_val ="error";
		}
		close();
	}
	
	public List<String> getProfile(){
		List<String> pro = new ArrayList<String>();
		open();
		Cursor fila = db.rawQuery("select * from dt_users", null);
		
		if (fila.moveToFirst()) {
			pro.add(fila.getString(0));
			pro.add(fila.getString(1));
			pro.add(fila.getString(2));
			pro.add(fila.getString(3));
		}
		else{
			pro.add("Error");
		}
		
		close();
		return pro;
	}
	
	public List<String> getFriends() {
		List<String> amigos = new ArrayList<String>();
		
		amigos.add("amigo1");
		amigos.add("amigo2");
		amigos.add("amigo3");
		
		return amigos;
	}
	
	public ArrayList<Friend> getFriendList() {
	    Friend amigo = new Friend();
	    amigo.setNumber("6311087061");
	    amigo.setFull_Name("Edvard Sapiens");
	    
	    Friend amigo2 = new Friend();
	    amigo2.setNumber("44323423423");
	    amigo2.setFull_Name("Pepe Pekas");
		
		ArrayList<Friend> friendList = new ArrayList<Friend>();
		
		friendList.add(amigo);
		friendList.add(amigo2);

		return friendList;
	}


	public String validacion_registacion()
	{
		String _verificacion; 
		open();
		
		//SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
		
		Cursor fila = db.rawQuery("select * from dt_users", null);
		
		if (fila.moveToFirst()) 
		 _verificacion = "true";
		else
		  _verificacion = "false";
		close();
		
		//DatabaseManager.getInstance().closeDatabase();
		return _verificacion;
	}

	public void getFriendsnotReady()
	{
		open();
		Cursor c = db.rawQuery("Select friend_username from dt_friend_list;", null) ;
		//Cursor c = db.rawQuery("Select username,friend_username,message,date_register,hour from dt_chatMessenger;", null) ;
	    if(c.moveToFirst())
	    {
	    	do
	    	{
	    		String get = c.getString(0);
	    		list_friends.add(get.toString());
	    	}
	    	while
	    	(c.moveToNext());
	    }
	    close();
	}
	

	
	public void friend_insert(String friend_username)
	{
		consulta_usuario();
		open();
		ContentValues registro = new ContentValues();
		registro.put("friend_username", friend_username.toString());
		registro.put("username", _usuario.toString());
		db.insert("dt_friend_list", null, registro);
		close();
	}
	
    public void consulta_usuario()
    {
		
		open();
		Cursor fila = db.rawQuery("select username from dt_users;", null);
		
		if (fila.moveToFirst()) 
		{ 
			 _usuario = fila.getString(0);
		}
		else
		{
			_usuario = "es";
			
		}
		close();
	}

 

}
