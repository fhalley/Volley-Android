
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
        }, "Xmpp", "xmpp_init", [phone])
    }
    ;

   var callbackFunction = function(err, result) {
        console.log("Waiting ToCallback");
        if (err){
          console.log("get in the if.");
        } else {
          console.log("entro al else. autologin.js");
            //console.log("success," + result);

          if (result == "true"){
            window.location.replace("profile.html");
          }else{
            //window.location.replace("account-setup2.html");
            window.location.replace("login-register.html");
          }
        }
    };