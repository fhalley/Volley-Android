package com.android.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import java.util.ArrayList;

import com.android.volley.Friend;

import android.database.Cursor;
import android.database.SQLException;
import android.os.StrictMode;
public class db_volley_server{
	
private Connection conn;
	public void conexion() throws ClassNotFoundException, java.sql.SQLException { 
		Class.forName("org.postgresql.Driver");
		 
	     //"jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
		 //Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
		 conn = DriverManager.getConnection("jdbc:postgresql://66.208.118.222:5432/volley_server", "omar", "123456");	 
	}
	
  	public void  user_insert(String username, String password , String fullname, String verificacion){
  		
		 try {
			 
	 		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
	 			 
	 	
	 		 //Cadena de conexion a postgres
	 		 conexion();
	 		 
	 		 
	 		 //En el stsql se puede agregar cualquier consulta SQL deseada.
	 		System.out.println("antes de entrar al insert");
	 		 
	 		 PreparedStatement pstm = conn.prepareStatement("INSERT into " +
                " dt_users(username, password, fullname, verification_code) " + " VALUES(?,?,?,?)");
	 	  			pstm.setString(1, username);
	 	  			pstm.setString(2, password);
	 	  			pstm.setString(3,fullname);
	 	  			pstm.setString(4,verificacion);
	 	  			pstm.execute();
	 	  			pstm.close();
	 	  			
	 	  		System.out.println("si entro al registro !!!!!!!!!!--------");

	 		 }
	 		 catch (SQLException se) {
	 		 System.out.println("oops! No se puede conectar. Error: " + se.toString());
	 		 
	 		
	 		 
	 		 }
	 		 catch (ClassNotFoundException e) {
	 		 System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
	 		
	 		 
	 		 }catch (java.sql.SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		
	 		}
	}
  	
  	public String username;
  	public String password;
  	public String fullname;
	public String re;
	public String res;
  	
	public void user_verification_test(String username) {
		
		
		res = user_verification(username).toString(); 
		
		
		re = username + " si jala" + " " + res;
	}
  	
  	public String user_verification(String username) {
  		
  		String re1 = "";
		try {
			
		//Metodo para no utilizar un Thread
			
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
			
		//Cadena de conexion a postgres
		conexion();
		
		 //En el stsql se puede agregar cualquier consulta SQL deseada.
		 String stsql = "Select password,fullname from dt_users where username like '"+username.toString()+"'";
		 Statement st = conn.createStatement();
		 
		 ResultSet rs = st.executeQuery(stsql);
		 
		 	
			 if(rs.next())
			 {
				System.out.println(rs.getString(1));
				password =   rs.getString(1);
				fullname = rs.getString(2);
				System.out.println(password + " " + fullname);
				 
				System.out.println( rs.getString(1) );
				
				re1 = "true";
			    conn.close();
		   }
		   else
		   {
			   re1 = "false";
		   }
		
		 }
		 catch (SQLException se) {
		 System.out.println("oops! No se puede conectar. Error: " + se.toString());
		 password = "SQL exception Error 1";
		 
		 System.err.println(se.getMessage());
	
		
		 
		 }
		 catch (ClassNotFoundException e) {
		 System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
		 //friend_name = "No existe 2";
		 
		 password = "SQL exception Error 1";
			
		 
		 } catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//friend_name = "No existe 3";
			password = "SQL exception Error 1";
			
		}
		 //this.re =  re;
		return re1;
	   }

	public void  friend_insert(String username, String frienduser, String friendname){
  		
		 try {
			 
	 		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
	 			 
	 	
	 		 //Cadena de conexion a postgres
	 		 conexion();
	 		 
	 		 
	 		 //En el stsql se puede agregar cualquier consulta SQL deseada.
	 		System.out.println("antes de entrar al insert");
	 		 
	 		 PreparedStatement pstm = conn.prepareStatement("INSERT into " +
              " dt_friends(username,frienduser ,friendname) " + " VALUES(?,?,?)");
	 	  			pstm.setString(1, username);
	 	  			pstm.setString(2, frienduser);
	 	  			pstm.setString(3, friendname);
	 	  			
	 	  			pstm.execute();
	 	  			pstm.close();
	 	  			
	 	  		System.out.println("si entro al registro !!!!!!!!!!--------");

	 		 }
	 		 catch (SQLException se) {
	 		 System.out.println("oops! No se puede conectar. Error: " + se.toString());
	 		 
	 		
	 		 
	 		 }
	 		 catch (ClassNotFoundException e) {
	 		 System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
	 		
	 		 
	 		 }catch (java.sql.SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		
	 		}

	}
///////////////////////////////////////////////////////////////////////////////////////////////// 	
	
	/*
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
	}*/
	
	public ArrayList<Friend> getFriends(String username) {
		
		ArrayList<Friend> arrayListFriends = new ArrayList<Friend>();
		
		try {	
			//Metodo para no utilizar un Thread
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
			
	    	//Cadena de conexion a postgres
		    conexion();
		
	        //En el stsql se puede agregar cualquier consulta SQL deseada.
		    String stsql = "Select * from dt_friends where username like '"+username.toString()+"'";
		    Statement st = conn.createStatement();
		
	    	ResultSet rs = st.executeQuery(stsql);
		    String friend = "";
		 	
	        while(rs.next())
			{
				Friend amigo = new Friend();
				amigo.setNumber(rs.getString(2));
				amigo.setFull_Name(rs.getString(3));
				 
				arrayListFriends.add(amigo);
				 	
			    System.out.println( rs.getString(2) + " registrado" );
				conn.close();				    
		   }
		 }
		 catch (SQLException se) {
			 
		     System.out.println("oops! No se puede conectar. Error: " + se.toString());
		     password = "SQL exception Error 1";
		 
		     System.err.println(se.getMessage());
		 }
		 catch (ClassNotFoundException e) {
		 System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
		 //friend_name = "No existe 2";
		 
		 password = "SQL exception Error 1";

		 
		 } catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//friend_name = "No existe 3";
			password = "SQL exception Error 1";
			
		}
		return arrayListFriends;
		
	   }
  	
  	
	
	

}


