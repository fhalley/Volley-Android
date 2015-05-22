package com.red_folder.phonegap.plugin.backgroundservice.sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.postgresql.core.SetupQueryRunner;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.database.db_volley;
import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;
import com.red_folder.phonegap.plugin.backgroundservice.BackgroundServicePlugin;

public class MyService extends BackgroundService {
	public static final String SERVER_HOST = "66.208.118.221";
	public static final int SERVER_PORT = 5222;
	public static final String SERVICE = "volley.com ";
	//public static final String SERVER_HOST = "192.168.1.102";
	//public static final int SERVER_PORT = 5222;
	//public static final String SERVICE = "dell";
	private static final Context context = null;
	private static XMPPConnection xmppConnection;
	private static JSONObject mResult = new JSONObject();
	private static String SENDER = "";
	private static String MESSAGE = "";
	private static String USERNAME = "";
	private static String PASSWORD = "";
	private String hayMsj="";
	public List<String> listUser = new ArrayList<String>();
	db_volley DBServices = new db_volley(this);
	private static Handler mHandler = new Handler();
	
	
	
	protected static void connect(String server, int port, String s) throws Exception {
        xmppConnection = new XMPPConnection(new ConnectionConfiguration(server, port,s));
        xmppConnection.connect();
    }
	public void disconnect(){
        if(xmppConnection != null){
            xmppConnection.disconnect();
            interrupt();
        }
    }
	private void interrupt() {
		// TODO Auto-generated method stub
		
	}
	
	public static void login(String username, String password) throws Exception{
        connect(SERVER_HOST, SERVER_PORT, SERVICE);
        xmppConnection.login(username, password);
    }
	//Added4-17-15
   /* public void connect() {
    	Log.i("XMPPChatDemoActivity", "entro al connect");
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				Log.i("XMPPChatDemoActivity", "entro al run");
				Log.i("XMPPChatDemoActivity",
						"Connected to " + xmppConnection.getHost());
				setConnection(xmppConnection);
			}
		});
		t.start();
	}*/
    
	//NUEVO
	public void setConnection(XMPPConnection connection) {
		xmppConnection = connection;
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
						MESSAGE = message.getBody();
						SENDER = fromName;
				
					
					   // messages.add(fromName + ":");1
					   //	messages.add(message.getBody());
					   // Add the incoming message to the list view
						 mHandler.post(new Runnable() {
							public void run() {
								//setListAdapter();
							    //iniciar();
								doWork();
							}
						});
					}
				}
			}, filter);
		}
	}
	
	public static JSONObject listeningForMessages() throws JSONException{
		 JSONObject result = new JSONObject();
	        PacketFilter filter = new AndFilter(new PacketTypeFilter(Message.class));
	        PacketCollector collector = xmppConnection.createPacketCollector(filter);
	        while (true) {
	            Packet packet = collector.nextResult();
	            if (packet instanceof Message) {
	                Message message = (Message) packet;
	                if (message != null && message.getBody() != null)
	                    System.out.println("Received message from "
	                            + packet.getFrom() + " : "
	                            + (message != null ? message.getBody() : "NULL"));
	                result.put("Sender",packet.getFrom());
	                result.put("Message", message.getBody());
	                //result.put("Message",message.getBody());
	                Log.d("CoreXmppApi.Info","Got The Message From :"+ packet.getFrom());
	                Log.d("CoreXmppApi.Info","The Message Are :"+ message.getBody());
		            return result;
	            }
	            
	       }  
	}
	/*
	private static void setUpResult(String msg, String sender){
		try {
			mResult.put("Message", msg);
			mResult.put("Sender", sender);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}*/
	
	/*ORIGINAL
	public static JSONObject listeningForMessages() throws JSONException{
		 JSONObject result = new JSONObject();
	        PacketFilter filter = new AndFilter(new PacketTypeFilter(Message.class));
	        PacketCollector collector = xmppConnection.createPacketCollector(filter);
	        while (true) {
	            Packet packet = collector.nextResult();
	            if (packet instanceof Message) {
	                Message message = (Message) packet;
	                if (message != null && message.getBody() != null)
	                    System.out.println("Received message from "
	                            + packet.getFrom() + " : "
	                            + (message != null ? message.getBody() : "NULL"));
	                result.put("Sender",packet.getFrom());
	                result.put("Message", message.getBody());
	                //result.put("Message",message.getBody());
	                Log.d("CoreXmppApi.Info","Got The Message From :"+ packet.getFrom());
	                Log.d("CoreXmppApi.Info","The Message Are :"+ message.getBody());
		            return result;
	            }
	            
	       }  
	}*/
	//MIO
	/*public void setConnection(XMPPConnection connection) {
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
				 	myService.mHelloTo = message.getBody();
					// Add the incoming message to the list view
					 mHandler.post(new Runnable() {
						public void run() {
							
						}
					});
				}
			}
		}, filter);
	}
}*/


	private void getUserPass(){
		listUser = DBServices.getUserPass();
		USERNAME = listUser.get(0);
		PASSWORD = listUser.get(1);
	}
	private final static String TAG = MyService.class.getSimpleName();
	
	public String mHelloTo = "World-desde aca";

	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();

		  // Following three lines simply produce a text string with Hello World and the date & time (UK format)
		  getUserPass();
		  try {
		      login(USERNAME, PASSWORD);
		      Log.d("MyService", USERNAME + " Login pass: " + PASSWORD);
		      
		      //setConnection(xmppConnection);
		      JSONObject data = listeningForMessages();
		      // listeningForMessages();
		      Log.d("MyService", "obj: " + data.getString("Message") );
		      String sender = data.getString("Sender");
		      String message = data.getString("Message");
		      //String sender = SENDER;
		      //String message = MESSAGE;
		      //String bodymessage = data.getString("Message");
		      Log.d("CoreXmppApi.Info","Logger For incoming: "+sender+" ");
			  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
			  String now = df.format(new Date(System.currentTimeMillis())); 
			  String msg = "Hello World - its currently " + now + " Data ";
		      result.put("Message", "Sender  :" + sender + " Body :" + message);
			  result.put("sender", sender);
			  result.put("msg", message);
		      result.put("time", now);
		       
			  Log.d("MyService","Logger");
		   } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		        Log.d("MyService","valio verga el try?");
		   }
		  
		   Log.d("MyService","Paso el try");
		    // We output the message to the logcat
		

		   // We also provide the same message in our JSON Result
		  return result; 
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();
		try {
		      result.put("HelloTo", this.mHelloTo);
		   } catch (JSONException e) {
		   }
		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		try {
		      if (config.has("HelloTo"))
		         this.mHelloTo = config.getString("HelloTo");
		} catch (JSONException e) {
		}   
	}     

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub
		Log.d("MyService","Se detuvo el timer");
		
	}


}
