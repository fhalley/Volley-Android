alert("entro al js frie3nda");
//var friendList;
var suma = "";
var name1 = "";

function init() {
    callNativePlugin(callbackFunction);
}
document.addEventListener("deviceready", init, false);

function callNativePlugin(callback) {
  alert("CalNative entro callNativePlugin frienda");

  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_get_chats", [])
}
;

var callbackFunction = function(err, result) {
  if (err) {
    console.log("entro al if.");
  } else {
    console.log("entro al else. lista chats");
    for (i in result) { 
        alert("Numeo de chat: "+result[i].id + ". AMIGO: " + result[i].userFriend);
    }//for end
  }
};
