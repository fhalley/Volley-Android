alert("entro al js frie3nda");
//var friendList;
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

  if (err) {
    console.log("entro al if.");

  } else {
    console.log("entro al else. lista amigos");

    for (i in result) { 
      alert("Amigo "+[i]+":"+result[i].name + ". Numero:" + result[i].num);


    }//for end

  }

};