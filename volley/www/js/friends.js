alert("entro al js frie3nda");
var friendList= [];
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
  }, "Xmpp", "xmpp_get_friends", [])
}
;

var callbackFunction = function(err, result) {
  console.log("Waiting ToCallback");
  alert("Entro al callbackFunctionoooo");
  if (err) {
    console.log("entro al if.");
    alert("Entrtro al iff");
  } else {
    console.log("entro al else. lista amigos");
   // alert(result);
    friendList = result;
  }
};