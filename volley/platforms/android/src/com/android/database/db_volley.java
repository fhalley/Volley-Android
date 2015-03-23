package com.android.database;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Profile;

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

 

}
