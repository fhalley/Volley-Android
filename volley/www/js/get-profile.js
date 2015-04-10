
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
    }
    ;

    var callbackFunction = function(err, result) {
        console.log("Waiting ToCallback");

        if (err) {
              console.log("entro al if.");

        } else {
            console.log("entro al else.");
           //console.log("success," + result);
           // alert(result);
           var nombre=document.getElementById("name");
           nombre.value = result;
           nombre.innerHTML = result;    
           console.log("final");
        }

    };