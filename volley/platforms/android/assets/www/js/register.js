
function init() {
    console.log('Ready To Works');
    function register() {
        callNativePlugin(callbackFunction);
    }
    var el = document.getElementById("register");
    el.addEventListener("click", register, false);

    function callNativePlugin(callback) {
        var fullname = $("#fullname").val();
        var phone = $("#phone").val();
        cordova.exec(function(result) {
            callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "xmpp_register", [fullname, phone])
    }
    ;

    var callbackFunction = function(err, result) {
        console.log("Waiting ToCallback");
        if (err) {
            window.location.replace("register.html");
        } else {
            console.log("Ok for Reg");
            console.log("success," + result);
            window.location.replace("enter-code.html");
        }
    };
}
document.addEventListener("deviceready", init, false);