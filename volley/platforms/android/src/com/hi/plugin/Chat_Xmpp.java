package com.hi.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SASLAuthentication;
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

import com.android.database.db_volley;

import android.content.Context;

import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

public class  Chat_Xmpp extends CordovaPlugin  {

	public static final String ACTION_SEND_MESSAGE = "send_message";

	public static final String ACTION_GET_PROFILE = "xmpp_get_profile";

	int portInt = 5222;
    String host = "66.208.118.221";
    String service = "volley.com"; //Domain name
    String username = "edvard_cs"; //Without domain name
    String password = "edvard123";

	private XMPPConnection connection;
	private ArrayList<String> messages = new ArrayList<String>();
	private Handler mHandler = new Handler();

	String TO = "";
	String TEXT = "";
	private ListView listview;
	
	@Override
	public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
     	final Context	context = cordova.getActivity();	
    	Log.i("XmppPlugin.Info","Open Excute");
    	if(ACTION_SEND_MESSAGE.equals(action)||ACTION_GET_PROFILE.equals(action))
   	    {
        	//Thread t = new Thread(new Runnable() {
   	    	final db_volley Users_DBServices = new db_volley(context);
   		    cordova.getThreadPool().execute(new Runnable() {
   			//@Override
   			public void run() {
   				Log.i("XmppPlugin.Info","Open Thread");
   				// TODO Auto-generated method stub
   				if(ACTION_SEND_MESSAGE.equals(action)){
   					Log.i("XmppPlugin.Info","Action send message");
					try {
						TO = args.getString(0);
						TEXT = args.getString(1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.i("XMPPChatDemoActivity", "Sending text " + TEXT + " to " + TO);
					Message msg = new Message(TO, Message.Type.chat);
					msg.setBody(TEXT);				
					if (connection != null) {
						connection.sendPacket(msg);
						messages.add(connection.getUser() + ":");
						messages.add(TEXT);
						setListAdapter();
					}
					
					callbackContext.success("Se envio" + TEXT + " a " + TO);
   				}
   				
   			}
   			
   		});
   	
   				return true;
    	}
   	
        return false;
   }
 
	private void setListAdapter() {
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				//R.layout.listitem, messages);
		//listview.setAdapter(adapter);
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
					    messages.add(fromName + ":");
					 	messages.add(message.getBody());
						// Add the incoming message to the list view
						mHandler.post(new Runnable() {
							public void run() {
								//setListAdapter();
							}
						});
					}
				}
			}, filter);
		}
	}

	
	public void connect() {

		//final ProgressDialog dialog = ProgressDialog.show(this,
				//"Connecting...", "Please wait...", false);

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// Create a connection
				ConnectionConfiguration connConfig = new ConnectionConfiguration(
						host, portInt, service);
				XMPPConnection connection = new XMPPConnection(connConfig);

				try {
					connection.connect();
					Log.i("XMPPChatDemoActivity",
							"Connected to " + connection.getHost());
				} catch (XMPPException ex) {
					Log.e("XMPPChatDemoActivity", "Failed to connect to "
							+ connection.getHost());
					Log.e("XMPPChatDemoActivity", ex.toString());
					setConnection(null);
				}
				try {
					// SASLAuthentication.supportSASLMechanism("PLAIN", 0);
					connection.login(username, password);
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
				}

				//dialog.dismiss();
			}
		});
		t.start();
		//dialog.show();
	}
}