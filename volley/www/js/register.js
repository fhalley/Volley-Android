function init() {
    console.log('Ready To Works');
    function register() {
        callNativePlugin(callbackFunction);
        console.log("Final");
    }
    var el = document.getElementById("register");
    el.addEventListener("click", register, false);


    function callNativePlugin(callback) {
        var fullname = $("#fullname").val();
        var phone = $("#phone").val();
        //var str = "";
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
            console.log("Nt Authorized, Assuming need to register");
        } else {
            console.log("Ok for Reg");
            console.log("success," + result);
            alert('your phone number success to registered');
            //window.location.replace("verification.html");
        }
    };
    
}

document.addEventListener("deviceready", init, false);