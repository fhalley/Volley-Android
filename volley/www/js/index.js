function init() {
<<<<<<< HEAD
    console.log('Initializing......');
    function callNativePlugin(callback) {
        cordova.exec(function(result) {
            callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "xmpp_init", [])
    }
    ;

    var callbackFunction = function(err, result) {
        if (err) {
            //window.location.replace("register.html");
            console.log("Nt Authorized, Assuming need to register");
        } else {
            console.log("success," + result);
        }
        ;
    }
=======
console.log('Initializing......');
function callNativePlugin(callback) {
cordova.exec(function(result) {
callback(null, result);
}, function(result) {
callback("myerror");
}, "Xmpp", "xmpp_init", [])
}
;
var callbackFunction = function(err, result) {
if (err) {
//window.location.replace("register.html");
console.log("Nt Authorized, Assuming need to register");
} else {
console.log("success," + result);
}
;
}
>>>>>>> origin/master
callNativePlugin(callbackFunction);
}
document.addEventListener("deviceready", init, false);