alert("get in the js iniit");
function init() {
    callNativePlugin(callbackFunction);
}
document.addEventListener("deviceready", init, false);

  function callNativePlugin(callback) {
        alert("get in callNativePlugin");
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
          alert("get in the if" + err);// only to test
        } else {
          console.log("entro al else.");
            //console.log("success," + result);
          alert(result + " new");
          if (result == "true"){
            alert("result = true");
            window.location.replace("profile.html");
          }else{
            alert("result = false");
            window.location.replace("account-setup2.html");
          }
        }
    };