
function init() {
  callNativePlugin(callbackFunction);
}
document.addEventListener("deviceready", init, false);

function callNativePlugin(callback) {
  var phone = "333";
  cordova.exec(function(result) {
    callback(null, result);
    }, function(result) {
      callback("myerror");
    }, "Xmpp", "xmpp_get_profile", [phone])
};

var callbackFunction = function(err, result) {
  console.log("Waiting ToCallback");
  if (err) {
    console.log("Error al obtener perfil.");
  } else {
    var nombre = document.getElementById("name");
    nombre.value = result;
    nombre.innerHTML = result;    
    console.log("final");
  }
};