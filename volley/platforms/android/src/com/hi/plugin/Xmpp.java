package com.hi.plugin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.database.DatabaseManager;
import com.android.database.db_volley;
import com.android.volley.Codes;
import com.android.volley.Friend;
import com.android.volley.MessageObj;

import android.R.array;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentSender.SendIntentException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas.VertexMode;
import android.os.Handler;
import android.telephony.ServiceState;
import android.util.Log;
import android.view.Window.Callback;

/**
 * This class echoes a string called from JavaScript.
 */
public class Xmpp extends CordovaPlugin {
	
	public static final String SERVER_HOST = "66.208.118.221";
	public static final int SERVER_PORT = 5222;
	public static final String SERVICE = "volley.com ";
	public static final String ACTION_INIT = "xmpp_init";
	public static final String ACTION_REGISTER = "xmpp_register"; 
	public static final String ACTION_SEND_MSG = "xmpp_send_message";
	public static final String ACTION_ADD_CONTACT = "xmpp_add_contact";
	public static final String ACTION_VERIFY = "xmpp_verify";
	public static final String ACTION_GET_PROFILE = "xmpp_get_profile";
	public static final String ACTION_GET_FRIENDS = "xmpp_get_friends";
	public static final String ACTION_ADD_FRIEND = "xmpp_add_friend";
	public static final String ACTION_GET_ROSTER = "xmpp_get_roster";
	public static final String ACTION_USER_EXISTS = "xmpp_user_exists";
	public static final String ACTION_LOGIN = "xmpp_login";
	public String FULL_NAME = null;
	public String PHONE = null;
	public String VERIFY_CODE = null;
	public String CONS_PASSWORD = null;
	public static final ConnectionConfiguration connConfig = new ConnectionConfiguration(
			SERVER_HOST, SERVER_PORT, SERVICE);
//	public static final XMPPConnection connection = new XMPPConnection(
	//		connConfig);
	public static XMPPConnection connection = new XMPPConnection(
			connConfig);
	private Context context;
	//para probar el chat
	public String username = "edvard_cs1";
	public String password = "edvard123";
	public String TOt;
	public String FROMt;
	public String TEXTt;
	public String host = "66.208.118.221";	
	public int portInt = 5222;
	public String service = "volley.com";
	
	public String MESSAGE;
	public String RECIVED_MESSAGE;
	public String FROM;
	public String TO;
	public String CODE_USER;
	public String STATUS;
	public List<String> listFriends = new ArrayList<String>();
	public List<String> listProfile = new ArrayList<String>();
    private ArrayList<String> messagest = new ArrayList<String>();
	private Handler mHandler = new Handler();
	ArrayList<Friend> amiguitos = new ArrayList<Friend>();
	ArrayList<MessageObj> messages = new ArrayList<MessageObj>();

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
	        super.initialize(cordova, webView);

	}
	 
    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
    	final Context	context = cordova.getActivity();	
    	Log.i("XmppPlugin.Info","Open Excute");
    	if(ACTION_INIT.equals(action)||ACTION_REGISTER.equals(action)
    			||ACTION_ADD_CONTACT.equals(action)||ACTION_SEND_MSG.equals(action)
    			||ACTION_GET_PROFILE.equals(action)||ACTION_VERIFY.equals(action)||ACTION_GET_ROSTER.equals(action)
    			||ACTION_GET_FRIENDS.equals(action)||ACTION_ADD_FRIEND.equals(action))
    	{
        	//Thread t = new Thread(new Runnable() {
    		final db_volley Users_DBServices = new db_volley(context);
    		cordova.getThreadPool().execute(new Runnable() {
    			
    			//@Override
    			public void run() {
    				/* to tests, recive messages xmpp
    				if (connection != null) {
    					// Add a packet listener to get messages sent to us
    					PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
    					connection.addPacketListener(new PacketListener() {
    						@Override
    						public void processPacket(Packet packet) {
    							Message message = (Message) packet;
    							if (message.getBody() != null) {
    								String fromName = StringUtils.parseBareAddress(message
    										.getFrom());
    								Log.i("XMPPChatDemoActivity", "Text Recieved " + message.getBody()
    										+ " from " + fromName );
    							    messagest.add(fromName + ":");
    							 	messagest.add(message.getBody());
    							 	RECIVED_MESSAGE = message.getBody();
    								// Add the incoming message to the list view
    								 mHandler.post(new Runnable() {
    									public void run() {
    										//setListAdapter();
    										//sendJavascript("display();");
    										//Log.i("XMPPChatDemoActivity", "Se va llamar el javascript. " );
    										//webView.loadUrl("javascript:recive-message()");
    										//Log.i("XMPPChatDemoActivity", "se le llamo al ijueputa " );							
    										//	ScriptEngine engine = manager.getEngineByName("JavaScript");
    									//	callbackContext.success(RECIVED_MESSAGE);
    										callbackContext.sendPluginResult(pluginResult);

    									}
    								});
    							}
    						}
    					}, filter);
    				}
    				*/
    				
    				Log.i("XmppPlugin.Info","Open Thread");
    				// TODO Auto-generated method stub
    				if(ACTION_INIT.equals(action)){
    					Log.i("XmppPlugin.Info","Open Init");
    					
    					 String EXISTS = Users_DBServices.validacion_registacion();
    					 Log.i("XmppPlugin.Info","exists?");
    					 if(EXISTS.equals("true")){	  
    						  getProfile();
    						  Login(PHONE, CONS_PASSWORD);
    						  callbackContext.success(EXISTS);   
    				     }else if(EXISTS.equals("false")){
    						   callbackContext.success(EXISTS);   
    				     }else{
    						   callbackContext.success("ni true ni false ::: " + EXISTS); 
    				     }
    				}
    				else if(ACTION_REGISTER.equals(action)){
    					try {
    						Log.i("XmppPlugin.Info"," Entro al action register");
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
								Login(PHONE, CONS_PASSWORD);
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

						callbackContext.success(FULL_NAME); 
		            }
    				
    				else if(ACTION_GET_FRIENDS.equals(action)){
						Log.i("XmppPlugin.Info","Get and friends info.");

						getFriends();
						//listFriends.add("Amigo 1");
						Log.i("XmppPlugin.Info","Obtuvo los amigos");
						
						
					    for (Friend i: amiguitos) {
						   Log.i("XmppPlugin.Info", "Amigos: " + i.getFull_Name() + " Numero:" + i.getNumber() ); 
				        //   System.out.println (i); //Muestra cada uno de los nombres dentro de listaDeNombres
						}
					
					    JSONObject jsonObject = new JSONObject();
							
					    JSONArray array = new JSONArray();
								  for(Friend f : amiguitos ){
									  JSONObject json = new JSONObject();
									  
		    							try {
											json.put("name", f.getFull_Name());
											json.put("num", f.getNumber());
											array.put(json);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
	    		          }
						  callbackContext.success(array); 
		            }    
    				
    				else if(ACTION_SEND_MSG.equals(action)){
    					Log.i("XmppPlugin.Info","Action send message");
    					try {
    						TOt = args.getString(0);
    						TEXTt = args.getString(1);
    					} catch (JSONException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					//Log.i("XMPPChatDemoActivity", "Sending text " + text + " to " + to);
    					Log.i("XMPPChatDemoActivity", "Sending text " + TEXTt + "De " + connection.getUser() +" to " + TOt);
    					Message msg = new Message(TOt, Message.Type.chat);
    					msg.setBody(TEXTt);				
    					if (connection != null) {
    						Log.i("XMPPChatDemoActivity", "Connection es diferente de null");
    						connection.sendPacket(msg);
    						messagest.add(connection.getUser() + ":");
    						messagest.add(TEXTt);
    						Log.i("XMPPChatDemoActivity", "Se envio el mensaje.");	
    					}
    					
    			    	callbackContext.success("mensaje: "+ TEXTt + " Usuario: " + TOt);	

    					}
    				
    				else if(ACTION_ADD_FRIEND.equals(action)){
						Log.i("XmppPlugin.Info","Vamosaver si agrega.");
						String num = "";
						try {
							num = args.getString(0);
							Users_DBServices.friend_username = num;
							//Users_DBServices.sqlThread.start();
							Users_DBServices.friend_verification();	
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String nameFriend = Users_DBServices.consultFriend(num);

						callbackContext.success(nameFriend); 
		            }
    				
    			  
    			}
    			
    			private void SendMessages(){
    				String to = "edvard_cs";
    		        String text = "Hola guei";          
    		        Log.i("XMPPChatDemoActivity ", "Sending text " + text + " to " + to);
    		        Message msg = new Message(to, Message.Type.chat);  
    		        msg.setBody(text);
    		        if (connection != null) {
    		          connection.sendPacket(msg);
                // Cambie la lista a tipo message
    		        // messages.add(connection.getUser() + ":");
    		        //messages.add(text);
    		        }
    			}
    			//mHandler.post(new Runnable() {
				//	public void run() {
				//  	setListAdapter();
				//	}
				//});
    			
    			//Get the profile info and put it on a list
    			private void getProfile(){
    				listProfile = Users_DBServices.getProfile();
    				PHONE = listProfile.get(0);
					CONS_PASSWORD = listProfile.get(1);
					FULL_NAME = listProfile.get(2);
					VERIFY_CODE = listProfile.get(3);
    			}
    			
    			private void getFriends(){
    				Log.i("XMPPChatDemoActivity ", "Entro al getfriends");
    				 amiguitos = Users_DBServices.getFriendsTest();
    			  // esta es la buena   amiguitos = Users_DBServices.getAmigos();
    				//listFriends = Users_DBServices.getFriends();
    				//listFriends.add("Amigo 1");
    				//listFriends.add("Amigo 2");
    				//listFriends.add("Amigo 3");
    				Log.i("XMPPChatDemoActivity ", "agrego al getfriends");
    			}
    			//Method to get random pass and code
    			//Pass and code = 12345 to test
    			private void getPassAndCode(){
    			    Codes CP = new Codes();
    			    CONS_PASSWORD =  CP.generatePassword();
    			    // VERIFY_CODE = CP.gfile:///C:/Users/Edvard_CS/workspace/volley/www/chatlist.html#enerateCode();
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
    			      }

    					private void Login(String phone, String pass){
    						try {
    							//Log.i("XMPPClient", "Va a conectar");
    							//connection.connect();
    							Log.i("XMPPClient", "Va a logearse");
    							connection.login(phone, pass);
    						    Log.i("XMPPClient", "Logged in as " + connection.getUser());
    						    // Set the status to available
    						    Presence presence = new Presence(Presence.Type.available);
    						    connection.sendPacket(presence);
    						   
    						    //CODIGO AGREGADO RECIENTEMENTE PARA PRUEBAS
    						    
    						    Roster roster = connection.getRoster();
    							Collection<RosterEntry> entries = roster.getEntries();
    							for (RosterEntry entry : entries) {
    								Log.d("XMPPChatDemoActivity",
    										"--------------------------------------");
    								Log.d("XMPPChatDemoActivity", "RosterEntry " + entry);
    								Log.d("XMPPChatDemoActivity",
    										"User: " + entry.getUser());
    								Log.d("XMPPChatDemoActivity",
    										"Name: " + entry.getName());
    								Log.d("XMPPChatDemoActivity",
    										"Status: " + entry.getStatus());
    								Log.d("XMPPChatDemoActivity",
    										"Type: " + entry.getType());
    								Presence entryPresence = roster.getPresence(entry
    										.getUser());

    								Log.d("XMPPChatDemoActivity", "Presence Status: "
    										+ entryPresence.getStatus());
    								Log.d("XMPPChatDemoActivity", "Presence Type: "
    										+ entryPresence.getType());
    								Presence.Type type = entryPresence.getType();
    								if (type == Presence.Type.available)
    									Log.d("XMPPChatDemoActivity", "Presence AVIALABLE");
    								Log.d("XMPPChatDemoActivity", "Presence : "
    										+ entryPresence);
    							}
    						   	Log.i("XMPPChatDemoActivity", "Se va ejecutar el connect");
    					       	connect();
    					        Log.i("XMPPChatDemoActivity", "Se ejecuto el connect");
    							
    						} catch (XMPPException ex) {
    							Log.e("XMPPChatDemoActivity", "Failed to log in as "
    									+ phone);
    							Log.e("XMPPChatDemoActivity", ex.toString());
    							setConnection(null);
    						}
    					
    						//END
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
    		//Log.i("XMPPChatDemoActivity", "Se va ejecutar el connect");
        	//connect();
           // Log.i("XMPPChatDemoActivity", "Se ejecuto el connect");
    	    return true;
    	}
    	//Log.i("XMPPChatDemoActivity", "Se va ejecutar el connect");
    	//connect();
        //Log.i("XMPPChatDemoActivity", "Se ejecuto el connect");
    	//callbackContext.success();
        return false;
    }
    
	public void setConnection(XMPPConnection connection) {
		this.connection = connection;
		if (connection != null) {
			// Add a packet listener to get messages sent to us
			PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
			connection.addPacketListener(new PacketListener() {
				@Override
				public void processPacket(Packet packet) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						Log.i("XMPPChatDemoActivity", "Text Recieved " + message.getBody()
								+ " from " + fromName );
					    messagest.add(fromName + ":");
					 	messagest.add(message.getBody());
					 	
						// Add the incoming message to the list view
						 mHandler.post(new Runnable() {
							public void run() {
								//setListAdapter();
								//sendJavascript("display();");
								Log.i("XMPPChatDemoActivity", "Se va llamar el javascript. " );
								webView.loadUrl("javascript:recive-message()");
								Log.i("XMPPChatDemoActivity", "se le llamo al ijueputa " );							
								//	ScriptEngine engine = manager.getEngineByName("JavaScript");
								

							}
						});
					}
				}
			}, filter);
		}
	}


	
    //Added4-17-15
    public void connect() {
    	Log.i("XMPPChatDemoActivity", "entro al connect");
		//final ProgressDialog dialog = ProgressDialog.show(this,
		//		"Connecting...", "Please wait...", false);

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Log.i("XMPPChatDemoActivity", "entro al run");
				//setConnection(connection);
				// Create a connection
			    //ConnectionConfiguration connConfig = new ConnectionConfiguration(
						//host, portInt, service);
				//XMPPConnection connection = new XMPPConnection(connConfig);
				Log.i("XMPPChatDemoActivity",
						"Connected to " + connection.getHost());
				setConnection(connection);
/*
				try {
					//connection.connect();
					Log.i("XMPPChatDemoActivity", "Conecto");
					Log.i("XMPPChatDemoActivity",
							"Connected to " + connection.getHost());
					setConnection(connection);
				} catch (XMPPException ex) {
					Log.e("XMPPChatDemoActivity", "Failed to connect to "
							+ connection.getHost());
					Log.e("XMPPChatDemoActivity", ex.toString());
					setConnection(null);
				}
				/*	try {
					
					//SASLAuthentication.supportSASLMechanism("PLAIN", 0);
					//connection.login(username, password);
					connection.login(PHONE, CONS_PASSWORD);
					Log.i("XMPPChatDemoActivity",
							"Logged in as " + connection.getUser());

					// Set the status to available
					Presence presence = new Presence(Presence.Type.available);
					connection.sendPacket(presence);
					setConnection(connection);

					Roster roster = connection.getRoster();
					Collection<RosterEntry> entries = roster.getEntries();
					for (RosterEntry entry : entries) {
						Log.d("XMPPChatDemoActivity",
								"--------------------------------------");
						Log.d("XMPPChatDemoActivity", "RosterEntry " + entry);
						Log.d("XMPPChatDemoActivity",
								"User: " + entry.getUser());
						Log.d("XMPPChatDemoActivity",
								"Name: " + entry.getName());
						Log.d("XMPPChatDemoActivity",
								"Status: " + entry.getStatus());
						Log.d("XMPPChatDemoActivity",
								"Type: " + entry.getType());
						Presence entryPresence = roster.getPresence(entry
								.getUser());

						Log.d("XMPPChatDemoActivity", "Presence Status: "
								+ entryPresence.getStatus());
						Log.d("XMPPChatDemoActivity", "Presence Type: "
								+ entryPresence.getType());
						Presence.Type type = entryPresence.getType();
						if (type == Presence.Type.available)
							Log.d("XMPPChatDemoActivity", "Presence AVIALABLE");
						Log.d("XMPPChatDemoActivity", "Presence : "
								+ entryPresence);

					}
				} catch (XMPPException ex) {
					Log.e("XMPPChatDemoActivity", "Failed to log in as "
							+ username);
					Log.e("XMPPChatDemoActivity", ex.toString());
					setConnection(null);
			    }*/

				//dialog.dismiss();
			}
		});
		t.start();
		//dialog.show();
	}

}