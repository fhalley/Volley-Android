//var number = $("#phone").val();
//alert("Tu numero es: "+number);
function init() {
    console.log('Ready To Works');
    //alert("listo para trabajar");
    function register() {
        callNativePlugin(callbackFunction);
        console.log("Final");
        //alert("Pico el bagre");
    }
    var el = document.getElementById("register");
    el.addEventListener("click", register, false);

    function callNativePlugin(callback) {
        //alert("entro native plugin");
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
        } else {
            console.log("Ok for Reg");
            console.log("success," + result);
            alert('your phone number success to registered ' + result);
            //window.location.replace("verification.html");
        }
    };
}
document.addEventListener("deviceready", init, false);