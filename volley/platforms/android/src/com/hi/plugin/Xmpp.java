package com.hi.plugin;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Codes;

import android.content.Context;
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
	public String VERIFY_CODE = "15367";
	public String CONS_PASSWORD = "12345";
	public static final ConnectionConfiguration connConfig = new ConnectionConfiguration(
			SERVER_HOST, SERVER_PORT, SERVICE);
	public static final XMPPConnection connection = new XMPPConnection(
			connConfig);
	private Context context;
	
	public String CODE_USER;
	public String STATUS;
	
    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
    	 final Context	context = cordova.getActivity();	
    	Log.i("XmppPlugin.Info","Open Excute");
    	if(ACTION_INIT.equals(action)||ACTION_REGISTER.equals(action)
    			||ACTION_ADD_CONTACT.equals(action)||ACTION_SEND_MSG.equals(action)
    			||ACTION_GET_PROFILE.equals(action)||ACTION_VERIFY.equals(action)||ACTION_GET_ROSTER.equals(action))
    	{
        	//Thread t = new Thread(new Runnable() {
    		cordova.getThreadPool().execute(new Runnable() {
    			//@Override
    			public void run() {
    				Log.i("XmppPlugin.Info","Open Thread");
    				// TODO Auto-generated method stub
    				if(ACTION_INIT.equals(action)){
    					Log.i("XmppPlugin.Info","Open Init");
    					String hi="ASD init";
						callbackContext.success(hi);
    				}
    				
    				else if(ACTION_REGISTER.equals(action)){
    					try {
    						getPassAndCode();
							register(args.getString(0), args.getString(1), CONS_PASSWORD);
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
						
							callbackContext.success(status);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    					
    				}
    			}
    			//Method to get random pass and code
    			//Pass and code = 12345 to test
    			private void getPassAndCode(){
    			    //Codes CP = new Codes();
    			    //CONS_PASSWORD =  CP.generatePassword();
    			    //VERIFY_CODE = CP.generateCode();
    			    CONS_PASSWORD =  "123";
    			    VERIFY_CODE = "123";
    			}
    			
    			//Compare Random code with user code
    			private String validateCode(){
    		    	String status = "Invalid code";
    		    	
    		    	if(VERIFY_CODE.equals(CODE_USER))
    		    	   status = "User verificated";
    		      		    	
    		    	return status; 	
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