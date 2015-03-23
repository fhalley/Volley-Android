package com.hi.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.database.DatabaseManager;
import com.android.database.db_volley;
import com.android.volley.Codes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class Xmpp extends CordovaPlugin {

	public static final String SERVER_HOST = "66.208.118.221";
	public static final int SERVER_PORT = 5222;
	public static final String SERVICE = "volley.com ";
	public static final String ACTION_INIT = "xmpp_init";
	public static final String ACTION_REGISTER = "xmpp_register"; 
	public static final String ACTION_SEND_MSG = "sendmsg";
	public static final String ACTION_ADD_CONTACT = "xmpp_add_contact";
	public static final String ACTION_VERIFY = "xmpp_verify";
	public static final String ACTION_GET_PROFILE = "xmpp_get_profile";
	public static final String ACTION_GET_ROSTER = "xmpp_get_roster";
	public static final String ACTION_USER_EXISTS = "xmpp_user_exists";
	public static final String ACTION_LOGIN = "xmpp_login";
	public String FULL_NAME = null;
	public String PHONE = null;
	public String VERIFY_CODE = null;
	public String CONS_PASSWORD = null;
	public static final ConnectionConfiguration connConfig = new ConnectionConfiguration(
			SERVER_HOST, SERVER_PORT, SERVICE);
	public static final XMPPConnection connection = new XMPPConnection(
			connConfig);
	private Context context;
	
	public String CODE_USER;
	public String STATUS;
	public List<String> listProfile = new ArrayList<String>();
	

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
    	 final Context	context = cordova.getActivity();	
    	Log.i("XmppPlugin.Info","Open Excute");
    	if(ACTION_INIT.equals(action)||ACTION_REGISTER.equals(action)
    			||ACTION_ADD_CONTACT.equals(action)||ACTION_SEND_MSG.equals(action)
    			||ACTION_GET_PROFILE.equals(action)||ACTION_VERIFY.equals(action)||ACTION_GET_ROSTER.equals(action))
    	{
        	//Thread t = new Thread(new Runnable() {
    		final db_volley Users_DBServices = new db_volley(context);
    		cordova.getThreadPool().execute(new Runnable() {
    			//@Override
    			public void run() {
    				Log.i("XmppPlugin.Info","Open Thread");
    				// TODO Auto-generated method stub
    				if(ACTION_INIT.equals(action)){
    					Log.i("XmppPlugin.Info","Open Init");
    					
    					 String EXISTS = Users_DBServices.validacion_registacion();
    					 Log.i("XmppPlugin.Info","exists?");
    					 if(EXISTS.equals("true")){	  
    						  Login(PHONE, CONS_PASSWORD);
    						  callbackContext.success(EXISTS + "logged");   
    				     }else if(EXISTS.equals("false")){
    						   callbackContext.success(EXISTS);   
    				     }else{
    						   callbackContext.success("ni true ni false ::: " + EXISTS); 
    				     }
    				}
    				else if(ACTION_REGISTER.equals(action)){
    					try {
    						getPassAndCode();
    						FULL_NAME = args.getString(0);
    						PHONE =  args.getString(1);
							register(FULL_NAME, PHONE, CONS_PASSWORD);
							//register(args.getString(0), args.getString(1), CONS_PASSWORD);
							Boolean setProfile = XmppProfile.SetHiProfile(args.getString(0), args.getString(1),CONS_PASSWORD,"1", context);
							Log.i("XmppPlugin.Info","Registered");
							String hi="ASD_register";
							callbackContext.success("Registrado");
							
    					} catch (JSONException e) {
							// TODO Auto-generated catch block
							Log.i("XmppPlugin.Error",e.getMessage());
						}
    				}
    				else if(ACTION_VERIFY.equals(action)){
    					try {
    						Log.i("XmppPlugin.Info","Send code");
							CODE_USER = args.getString(0);
							String status = validateCode();
							
							if(status == "User verificated"){
								registerBD();
							}

							callbackContext.success(status);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}
    				
    				else if(ACTION_GET_PROFILE.equals(action)){
						Log.i("XmppPlugin.Info","Get and send profile info.");
					
						getProfile();
						callbackContext.success(listProfile.get(2)); 
		            }
    			}
    			
    			//Get the profile info and put it on a list
    			private void getProfile(){
    			    db_volley Users_DBServices = new db_volley(context); 
    				listProfile = Users_DBServices.getProfile();
    			}
    			
    			//Method to get random pass and code
    			//Pass and code = 12345 to test
    			private void getPassAndCode(){
    			    Codes CP = new Codes();
    			    CONS_PASSWORD =  CP.generatePassword();
    			   // VERIFY_CODE = CP.generateCode();
    			   // CONS_PASSWORD =  "123";
    			    VERIFY_CODE = "123";
    			}
    			
    			//Verify if the code is correct
    			private String validateCode(){
    		    	String status = "Invalid code";
    		    	
    		    	if(VERIFY_CODE.equals(CODE_USER))
    		    	   status = "User verificated";
    		    	
    		    	return status; 	
    		     }
    			
    			 public String verifyUserExits(){
    			    	Log.i("XmppPlugin.Info","Open veryfi");
    					// db_volley Users_DBServices = new db_volley(context);
    					 String exists = Users_DBServices.validacion_registacion();
    					   
    			          if(exists.equals("true")){	  
    				          listProfile = Users_DBServices.getProfile();
    						  PHONE = listProfile.get(0);
    						  CONS_PASSWORD = listProfile.get(1);
    					   }else if(exists.equals("false")){
    						 //  result =  exists;
    					   }else{
    						//   result = "ni true ni false ::: " + exists; 
    					   }
    					 return exists;
    				 }
    			    
    				 private void registerBD(){
    				  //Intert User in database
    			      Log.i("XMPPClient", "a punto de guardar");
    			      //db_volley Users_DBServices = new db_volley(context); 
    			      Log.i("XMPPClient", "se instancio la database");
    			      Users_DBServices.insertar(PHONE, CONS_PASSWORD, FULL_NAME, VERIFY_CODE);
    			      Log.i("XMPPClient", "guardo");
    			      // Users_DBServices.consultas(PHONE);
    			      // telefono = Ussers_DBServices._usuario;
    			      // pass = Users_DBServices._val;

    			      try { 
    			    	    //connection.connect();
    			    	    connection.login(PHONE, CONS_PASSWORD);
							//connection.login("001001", "001001");
						    Log.i("XMPPClient", "Logged in as " + connection.getUser());
						    // Set the status to available
						    Presence presence = new Presence(Presence.Type.available);
						    connection.sendPacket(presence);
						    Log.i("XMPPClient", "Trono ");
						} catch (XMPPException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.i("XMPPClient", "Trono ");
						}
    			      }

    					private void Login(String phone, String pass){
    						try {
    							connection.connect();
    							connection.login(phone, pass);
    						    Log.i("XMPPClient", "Logged in as " + connection.getUser());
    						    // Set the status to available
    						    Presence presence = new Presence(Presence.Type.available);
    						    connection.sendPacket(presence);
    						    
    						} catch (XMPPException e) {
    							// TODO Auto-generated catch block
    							e.printStackTrace();
    							   Log.i("XMPPClient", "Trono ");
    						}
    			    }
    			 
    			//all function underneath are here
    			private boolean register(String fullname,String username, String password) {
    				try {
						connection.connect();
						AccountManager accm = new AccountManager(connection);
		    			Map<String, String> attributes = new HashMap<String, String>();

		    			attributes.put("name", fullname);
		    			
		    			try {
		    				accm.createAccount(username, password,attributes);
		    				return true;
		    			} catch (XMPPException ex) {
		    				Log.i("XmppPluginMainClass.Exception >>",ex.getMessage());
		    			}
					} catch (XMPPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    			return true;
	    		}
    			
    			//end function
    		});
    				return true;
    	}
    	
        return false;
    }
    
    
   
}