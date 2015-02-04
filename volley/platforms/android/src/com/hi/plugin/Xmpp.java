package com.hi.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	public static final String ACTION_DAFTAR = "xmpp_daftar";
	public static final String ACTION_GET_PROFILE = "xmpp_get_profile";
	public static final String ACTION_GET_ROSTER = "xmpp_get_roster";
	public final String VERIFY_CODE = "15367";
	public static final String CONS_PASSWORD = "asdf";
	
	
	// String username = "edvard_cs"; //Without domain name
	 //   String password = "edvard123";
	    String username = "omar"; //Without domain name
	    String password = "omalfl123";
	    
	    //Variables para register
	    String usuario = "TeamPrograming";
	    String contra = "team123";
	    String name = "Team Programming Voley"; 
	    String email = "team.ptrograming@gmail.com";
	    		
	public static final String ACTION_LOGOUT= "xmpp_logOut";
    public static final String ACTION_SINGUP= "xmpp_singup";
    private XMPPConnection connection;
	private ArrayList<String> messages = new ArrayList<String>();
	private Handler mHandler = new Handler();
	
	
    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) {
    	Log.i("XmppPlugin.Info","Open Excute");
    	if(ACTION_INIT.equals(action)||ACTION_REGISTER.equals(action)
    			||ACTION_ADD_CONTACT.equals(action)||ACTION_SEND_MSG.equals(action)||ACTION_DAFTAR.equals(action)
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
    					String hi="ASD";
						callbackContext.success(hi);
						connect();
    				}
    			}
    		});
    				return true;
    	}
    	
     	if(ACTION_LOGOUT.equals(action))
    	{
        	//Thread t = new Thread(new Runnable() {
    		cordova.getThreadPool().execute(new Runnable() {
    			//@Override
    			public void run() {
    				Log.i("XmppPlugin.Info","Open Thread");
    				// TODO Auto-generated method stub
    				if(ACTION_LOGOUT.equals(action)){
    					Log.i("XmppPlugin.Info","Open Init");
    					String hi="ASD";
						callbackContext.success(hi);
						
						try {
							if (connection != null)
								connection.disconnect();
						} catch (Exception e) {

						}
    				}
    			}
    		});
    				return true;
    	}
     	
     	if(ACTION_SINGUP.equals(action))
    	{
        	//Thread t = new Thread(new Runnable() {
    		cordova.getThreadPool().execute(new Runnable() {
    			//@Override
    			public void run() {
    				Log.i("XmppPlugin.Info","Open Thread");
    				// TODO Auto-generated method stub
    				if(ACTION_SINGUP.equals(action)){
    					Log.i("XmppPlugin.Info","Open Init");
    					String hi="ASD";
						callbackContext.success(hi);

						register();
    				}
    			}
    		});
    				return true;
    	}
    	
    	
        return false;
    }
    
 //   private void setListAdapter() {
	//	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		//		R.layout.listitem, messages);
		//listview.setAdapter(adapter);
//	}
    
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
    
    public void register() {
    	
    	AccountManager am = new AccountManager(connection);
    	Map<String, String> attributes = new HashMap<String, String>();
    	attributes.put("usuario", usuario);
    	attributes.put("contra", contra);
    	attributes.put("email", "foo@foo.com");
    	attributes.put("name", "my_full_name");
    	am.createAccount(usuario, contra, attributes);


    	Registration reg = new Registration();
    	reg.setType(IQ.Type.SET);
    	reg.setTo(connection.getServiceName());
//    	      attributes.put("username", username);
//    	      attributes.put("password", password);
//    	      reg.setAttributes(attributes);
    	reg.addAttribute("usuario", usuario);
    	reg.addAttribute("contra", contra);
    	reg.addAttribute("email", email);
    	reg.addAttribute("name", name);
    	PacketFilter filter = new AndFilter(new PacketIDFilter(
    	    reg.getPacketID()), new PacketTypeFilter(IQ.class));
    	PacketCollector collector = connection.createPacketCollector(filter);
    	connection.sendPacket(reg);
    	
      	//attributes.put("username", usuario);
    	//attributes.put("password", contra);
    	//attributes.put("email", correo);
    	//attributes.put("name", nombre_completo);
    	//am.createAccount(usuario, contra, attributes);
    }
    
    public void connect() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// Create a connection
				ConnectionConfiguration connConfig = new ConnectionConfiguration(
						SERVER_HOST, SERVER_PORT, SERVICE);
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

/*int portInt = 5222;
    String host = "66.208.118.221";
    String service = "volley.com"; //Domain name
    String username = "edvard_cs"; //Without domain name
    String password = "edvard123";;

	private XMPPConnection connection;
	private ArrayList<String> messages = new ArrayList<String>();
	private Handler mHandler = new Handler();

	private EditText recipient;
	private EditText textMessage;
	private ListView listview;

 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		recipient = (EditText) this.findViewById(R.id.toET);
		textMessage = (EditText) this.findViewById(R.id.chatET);
		listview = (ListView) this.findViewById(R.id.listMessages);
		setListAdapter();

		// Set a listener to send a chat text message
		Button send = (Button) this.findViewById(R.id.sendBtn);
		send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String to = recipient.getText().toString();
				String text = textMessage.getText().toString();

				Log.i("XMPPChatDemoActivity", "Sending text " + text + " to " + to);
				Message msg = new Message(to, Message.Type.chat);
				msg.setBody(text);				
				if (connection != null) {
					connection.sendPacket(msg);
					messages.add(connection.getUser() + ":");
					messages.add(text);
					setListAdapter();
				}
			}
		});

		connect();
	}

	/**
	 * Called by Settings dialog when a connection is establised with the XMPP
	 * server
	 * 
	 * @param connection

	

	

	

	*/