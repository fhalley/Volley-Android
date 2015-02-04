function init() {
    alert('Initializing......');
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
            alert('if');
            //window.location.replace("register.html");
            console.log("Not Authorized, Assuming need to register");
        } else {
            alert('else');
            console.log("success," + result);
        }
        ;
    }
callNativePlugin(callbackFunction);
}

function logOut() {
    alert('desconectando...');
    function callNativePlugin(callback) {
        cordova.exec(function(result) {
            callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "xmpp_logOut", [])
    }
    ;

    var callbackFunction = function(err, result) {
        if (err) {
            alert('if');
            //window.location.replace("register.html");
            console.log("Not Authorized, Assuming need to register");
        } else {
            alert('else');
            console.log("success," + result);
        }
        ;
    }
callNativePlugin(callbackFunction);
}

function singup() {
    alert('Registrandp..');
    function callNativePlugin(callback) {
        cordova.exec(function(result) {
            callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "xmpp_singup", [])
    }
    ;

    var callbackFunction = function(err, result) {
        if (err) {
            alert('Error al crear el usuario');
            //window.location.replace("register.html");
            console.log("Not Authorized, Assuming need to register");
        } else {
            alert('Se creo el usuario');
            console.log("success," + result);
        }
        ;
    }
callNativePlugin(callbackFunction);
}


document.addEventListener("deviceready", init, false);

function show(){
    alert("Hola");
}
