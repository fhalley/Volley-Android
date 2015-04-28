function register() {
    callNativePlugin(callbackFunction);
    console.log("Final");
    //alert("Pico el bagre");
}
var el = document.getElementById("verificate");
el.addEventListener("click", register, false);

function callNativePlugin(callback) {
        var code = $("#code").val();
        cordova.exec(function(result) {
            callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "xmpp_verify", [code])
    }
;

var callbackFunction = function(err, result) {
    console.log("Waiting ToCallback");
    if (err) {
        window.location.replace("register.html");
        alert("Error");
    } else {
        console.log("Ok for verification.");
        console.log("success," + result);
        alert(result);

        if(result == "User verificated"){
            window.location.replace("profile.html");
        }
        //window.location.replace("profile.html");
    }
 };