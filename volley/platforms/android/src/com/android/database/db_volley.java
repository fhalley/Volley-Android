package com.android.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.Friend;
import com.android.volley.Profile;
//import com.squareup.okhttp.Connection;



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
	public ArrayList<Friend> getFriendsTest(){
		friend_insert_manual();
		//List<String> pro = new ArrayList<String>();
		ArrayList<Friend> arrayListFriends = new ArrayList<Friend>();
		open();
		Cursor c = db.rawQuery("Select * from dt_friends;", null) ;
		//Cursor c = db.rawQuery("Select username,friend_username,message,date_register,hour from dt_chatMessenger;", null) ;
	    if(c.moveToFirst())
	    {
	    	do
	    	{
	    		Friend friendObj = new Friend();
	    		
	    		friendObj.setNumber(c.getString(1));
	    		friendObj.setFull_Name(c.getString(2));
	    		
	    		arrayListFriends.add(friendObj);
	    	}
	    	while
	    	(c.moveToNext());
	    }
	    close();
		return arrayListFriends;
	}
	
	public ArrayList<Friend> getAmigos(){
		Friend friendObj1 = new Friend();
		Friend friendObj2 = new Friend();
		Friend friendObj3 = new Friend();
		Friend friendObj4 = new Friend();
		
		ArrayList<Friend> arrayListFriends = new ArrayList<Friend>();
		
		friendObj1.setNumber("1311087061");
		friendObj1.setFull_Name("Angel");

		friendObj2.setNumber("2311087061");
		friendObj2.setFull_Name("Pedro");
		
		friendObj3.setNumber("3311087061");
		friendObj3.setFull_Name("Daniel");
		
		friendObj4.setNumber("4311087061");
		friendObj4.setFull_Name("Juan");
		
		arrayListFriends.add(friendObj1);
		arrayListFriends.add(friendObj2);
		arrayListFriends.add(friendObj3);
		arrayListFriends.add(friendObj4);
	
		return arrayListFriends;
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

	
	public void friend_insert(String friend_username, String name)
	{
		consulta_usuario();
		open();
		ContentValues registro = new ContentValues();
		registro.put("friend_username", friend_username.toString());
		registro.put("name", name.toString());
		db.insert("dt_friends", null, registro);
		close();
	}
	
	public void friend_insert_manual()
	{
		friend_insert("123", "amigo 1");
		friend_insert("223", "amigo 2");
		friend_insert("3123", "amigo 3");
		friend_insert("4123", "amigo 4");
		friend_insert("5123", "amigo 5");

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

	public String consultFriend(String number){

		String name ="";
		//number = "alexis";
		open();
		Cursor fila = db.rawQuery("select name from dt_friends where friend_username like '"+
		  number.toString()+ "'", null);
		
		if (fila.moveToFirst()) {
			  
			name = fila.getString(0);
		}
		else
		{
			name = "error";
		}
		close();
		return name;
	}
	
  //PostgresSQL database-----------------------------------------------------------------------------------------------

  	public String friend_username;
  	public String friend_name;
  	
  	
  	public Thread sqlThread = new Thread() {
  		
  		
  		 public void run() {
  		 try {
  			 
  		 Class.forName("org.postgresql.Driver");
  		 // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
  		 // Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
  		 Connection conn = DriverManager.getConnection(
  		 "jdbc:postgresql://66.208.118.222:5432/volley_db", "omar", "123456");
  		 //En el stsql se puede agregar cualquier consulta SQL deseada.
  		 String stsql = "Select username,name from ofuser where username like '"+friend_username.toString()+"'";
  		 Statement st = conn.createStatement();
  		 
  		 ResultSet rs = st.executeQuery(stsql);
  		 
  		
  			 if(rs.next())
  			 {
  				 System.out.println(rs.getString(1));
  				friend_username =   rs.getString(1);
  				friend_name = rs.getString(2);
  				System.out.println(friend_username + friend_name);
  				 
  				 
  				
  			    friend_insert(friend_username,friend_name);
  				 
  				System.out.println( rs.getString(1) );
  			    conn.close();
  		   }
  		
  		 	sqlThread.interrupt();
  		 //rs.next();

  		 }
  		 catch (SQLException se) {
  		 System.out.println("oops! No se puede conectar. Error: " + se.toString());
  		 friend_name = "No existe 1";
  		
  		 
  		 }
  		 catch (ClassNotFoundException e) {
  		 System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
  		 friend_name = "No existe 2";
  		 
  		 } catch (java.sql.SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  			friend_name = "No existe 3";
  		}
  		 
  		 
  	   }
  	
  	
  		
  	};
  	
  	

    
    
    
    
    
    

 

}
