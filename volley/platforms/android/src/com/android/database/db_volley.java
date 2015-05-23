package com.android.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.ChatObj;
import com.android.volley.Friend;
import com.android.volley.MessageObj;
import com.android.volley.Profile;
//import com.squareup.okhttp.Connection;








import android.R.fraction;
import android.R.string;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.StrictMode;
import android.util.Log;

public class db_volley {
	
	private SQLiteDatabase db;
	private VolleyOpenHelper dbHelper;
	private db_volley_server DB_Server = new db_volley_server();
	
	public String _usuario,_pass,_val;
	public int cant,cant2;
	
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
	
	public List<String> getUserPass(){
		List<String> pro = new ArrayList<String>();
		open();
		Cursor fila = db.rawQuery("select * from dt_users", null);
		
		if (fila.moveToFirst()) {
			pro.add(fila.getString(0));
			pro.add(fila.getString(1));
		}
		else{
			pro.add("Error");
		}
		
		close();
		return pro;
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
	
	public void fillFriend(ArrayList<Friend> listFriends){
		for(Friend f : listFriends){
			friend_insert(f.getNumber(),f.getFull_Name());
		}
	}
	
	public ArrayList<Friend> getFriendsTest(){
	    //friend_insert_manual();
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
	
	public ArrayList<MessageObj> getMessages(){
		message_insert_manual();
	
		Log.i("XMPPChatDemoActivity", "se guardo mensaje en data base.");	
		ArrayList<MessageObj> arrayListMessage = new ArrayList<MessageObj>();
		open();
		Cursor c = db.rawQuery("Select * from dt_Messages where id_chat ='" + 1 + "';", null);
		
	    if(c.moveToFirst()) {
	    	do {
	    		String val1 = c.getString(0);
	    		String val2 = c.getString(1);
	    		String val3 = c.getString(2);
	    		String val4 = c.getString(3);
	    		
	    		MessageObj messageObj = new MessageObj(1,val1,val2,val3,val4);
	    		arrayListMessage.add(messageObj);
	    		Log.i("XMPPChatDemoActivity", "Se saco mensaje de la base de datos.");	
	    	}
	    	while
	    	(c.moveToNext());
	    }
	    close();
		return arrayListMessage;
	}
	
	public String getFriedName(String numero){
		String nombre = null;
		open();
		Cursor fila = db.rawQuery("select name from dt_friends where friend_username like '"+
		  numero.toString()+ "'", null);
		
		if (fila.moveToFirst()) 
			nombre = fila.getString(2);
		//Log.i("XMPPChatDemoActivity", "se encontro: " + nombre);	
		else
			nombre = "No existe";
		
		
		close();
		return nombre;
	}
	
	public void message_insert_manual()
	{
		message_add(1, "12345", "Hola", "10/1/47", "05:37");
		message_add(1, "12345", "Hola wey", "10/1/47", "05:40");
		message_add(1, "12345", "que hay que hacer", "10/1/47", "05:40");
	}
	
    public ArrayList<ChatObj> getChats(){
    	chat_insert_manual();
		ArrayList<ChatObj> arrayListChat = new ArrayList<ChatObj>();
		open();
		Cursor c = db.rawQuery("Select * from dt_Chats;", null) ;
		
	   if(c.moveToFirst())
	    {
	    	do
	    	{
	    		int val1 = c.getInt(0);
	    		String val2 = c.getString(1);
	    		
	    		ChatObj ChatObj = new ChatObj(val1,val2);
	    		arrayListChat.add(ChatObj);
	    	}
	    	while
	    	(c.moveToNext());
	    }
	    close();
		return arrayListChat;
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
	
	public void chat_insert_manual()
	{
		chat_add("123987");
		chat_add("12345");
		chat_add("11229988");
		chat_add("123459876");
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
	
	
	
	//Chat messages ................................................................................
	    public int _id_chat = 0;
	
	    public void chat_add(String friendUser)
		{
	
			open();
			ContentValues registro = new ContentValues();
		    registro.put("friend",friendUser.toString());
		    db.insert("dt_Chats", null, registro);
		    close();
			
		}
		
		
		public void chat_delete(Integer id_chat)
		{
			
			open();
			cant = db.delete("dt_Chats", "id_chat ='"+id_chat +"'", null);
			cant2 = db.delete("dt_Messages","id_chat ='" +id_chat+"'", null);
			close();
			
			
		}
		
		public String consutChatId(String friendUser){
			String result="";
			open();
			Cursor fila = db.rawQuery("select id_chat from dt_Chats where friend like '"+friendUser+"' ;", null);

			if(fila.moveToFirst()){ 
			    result = fila.getString(0);
			}
			else{
				chat_add(friendUser); 
				result="false";
			}		
			close();
			return result;
		}
		/*Original
		public void chat_add(String user, String friend)
		{
	
			open();
			ContentValues registro = new ContentValues();
			registro.put("user", user.toString());
		    registro.put("friend",friend.toString());
		    db.insert("dt_Chats", null, registro);
		    close();
			
		}
		
		public void consult_chat(String friend){
			
		open();
		Cursor fila = db.rawQuery("select id_chat from dt_Chats where friend like '"+friend+"' ;", null);
			
			
		if(fila.moveToFirst()) 
		{ 
		     _id_chat = fila.getInt(0);
		}
			else
			{
				_id_chat = 0;
				
			}
			close();
		}
		*/	
		public void message_add(Integer id_chat,String user ,String message, String date_register, String hour)
		{
			open();
			ContentValues registro = new ContentValues();
			registro.put("id_chat", id_chat.intValue());
			registro.put("user", user.toString());
		    registro.put("message",message.toString());
		    registro.put("date_register", date_register.toString());
		    registro.put("hour", hour.toString());
		    db.insert("dt_Messages", null, registro);
		    close();
		    
		}
		

		
		public ArrayList<MessageObj> get_messages(Integer id_chat)
		{   
			
			ArrayList<MessageObj> listMessages = new ArrayList<MessageObj>();
			open();
			Cursor c = db.rawQuery("Select user,message,date_register,hour from dt_Messages where id_chat ='" + id_chat + "' ;", null) ;
			//Cursor c = db.rawQuery("Select user,message,date_register,hour from dt_Messages;", null) ;
			if(c.moveToFirst())
		    {
		    	do
		    	{
		    		
		    		String get1 = c.getString(0);
		    		String get2 = c.getString(1);
		    		String get3 = c.getString(2);
		    		String get4 = c.getString(3);
		    		
		    		MessageObj msg = new MessageObj(id_chat,get1,get2,get3,get4);
		    		listMessages.add(msg);
		    	    
		    	}
		    	while
		    	(c.moveToNext());
		    	
		    }
		    close();
		    
		    return listMessages;
			  
		}
	
	
	
	
	
  //PostgresSQL database-----------------------------------------------------------------------------------------------

  	public String friend_username;
  	public String friend_name;
  	
  	
  	
  	public void friend_verification() {
 		 try {
 			 
 			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			 StrictMode.setThreadPolicy(policy);
 			 
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
 			    DB_Server.friend_insert(_usuario, friend_username, friend_name); 
 				 
 				System.out.println( rs.getString(1) );
 			    conn.close();
 		   }
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
  	

    
    
    
    
    
    

 

}
