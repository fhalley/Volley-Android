alert("test myService entro");     
var myService;

document.addEventListener('deviceready', function () {
    var serviceName = 'com.red_folder.phonegap.plugin.backgroundservice.sample.MyService';
    var factory = cordova.require('com.red_folder.phonegap.plugin.backgroundservice.BackgroundService')
    myService = factory.create(serviceName);

    getStatus();
}, true);

function restartThread() {
    var serviceName = 'com.red_folder.phonegap.plugin.backgroundservice.sample.MyService';
    var factory = cordova.require('com.red_folder.phonegap.plugin.backgroundservice.BackgroundService')
    myService = factory.create(serviceName);

    go();
}

function getStatus() {
    myService.getStatus(function (r) {
        displayResult(r)
    }, function (e) {
        displayError(e)
    });
}

function displayResult(data) {
    alert("Is service running: " + data.ServiceRunning);
    if(data.ServiceRunning = false){
      //  alert("restart therad");
        //restartThread();
    }
}

function displayError(data) {
    alert("We have an error");
}

function updateHandler(data) {
    if (data.LatestResult != null) {
        try {
            var resultMessage = document.getElementById("resultMessage");
            //var pesan = JSON.parse(data.LatestResult.Message);
            //console.log("You Got A New Message details : " + data.LatestResult.Message);
            //resultMessage.innerHTML = "You Got A New Message details : " + data.LatestResult.Message ;
            //crearDiv(data.LatestResult.sender, data.LatestResult.msg);
            //escribirMsj(data.LatestResult.msg);
            //crearA(data.LatestResult.sender);
            //crearSpam(data.LatestResult.msg);
            //alert("You Got A New Message details : " + data.LatestResult.Message);
            friendMessage(data.LatestResult.msg);
            //alert(data.LatestResult.msg);
            //alert("Mensaje: " +data.LatestResult.msg + " de: "+data.LatestResult.sender+ "a las"+ data.LatestResult.now);
            //escribirMsj(data.LatestResult.msg);
        } catch (err) {
            alert("Error en el handler");
        }
    }
}

function go() {
    myService.getStatus(function (r) {
        startService(r)
    }, function (e) {
        displayError(e)
    });
}
;

function startService(data) {
    if (data.ServiceRunning) {
        enableTimer(data);
    } else {
        myService.startService(function (r) {
            enableTimer(r)
        }, function (e) {
            displayError(e)
        });
    }
}

function enableTimer(data) {
    if (data.TimerEnabled) {
        registerForUpdates(data);
    } else {
        alert("mil milisegundos");
        myService.enableTimer(10000, function (r) { 
            registerForUpdates(r)
        }, function (e) {
            displayError(e)
        });
    }
}

function registerForUpdates(data) {
    if (!data.RegisteredForUpdates) {
        myService.registerForUpdates(function (r) {
            updateHandler(r)
        }, function (e) {
            handleError(e)
        });
    }
}

document.addEventListener('deviceready', function () {
    var serviceName = 'com.red_folder.phonegap.plugin.backgroundservice.sample.MyService';
    var factory = cordova.require('com.red_folder.phonegap.plugin.backgroundservice.BackgroundService');
    myService = factory.create(serviceName);

    go();
}, true);

function friendMessage(msj){
    var midiv = document.createElement("div");
        midiv.setAttribute("id","");
        midiv.setAttribute("class","left listDetailUser1");
        midiv.innerHTML = msj;
    document.getElementById("chat").appendChild(midiv); 
}

function myMessage(){
    var msj = $("#message").val();
    var midiv = document.createElement("div");
        midiv.setAttribute("id","");
        midiv.setAttribute("class","left listDetailUser2");
    //     midiv.setAttribute("otros atributos","otros");
        midiv.innerHTML = msj;
    document.getElementById("chat").appendChild(midiv); 
}

//PAra enviar los mensajes
var number_friend = "";
var message = "";
//var hour = "";
//var date = "";
alert("entro al message-test");
function clickSend() {
    number_friend = $("#a").val() + "@volley.com";
    message = $('#message').val();
    alert("el mensaje se enviara "+ message +" a: " + number_friend);
    callNativePlugin(callbackFunction);
    // alert("Va ejecutar el go.");
    // go();
    var serviceName = 'com.red_folder.phonegap.plugin.backgroundservice.sample.MyService';
    var factory = cordova.require('com.red_folder.phonegap.plugin.backgroundservice.BackgroundService');
    myService = factory.create(serviceName);
    alert("Va ejecutar el go.");
    go();
}
var el = document.getElementById("send");
el.addEventListener("click", clickSend, false);

function callNativePlugin(callback) {  
    console.log("Entro al calnative");
    cordova.exec(function(result) {
            callback(null, result);
    }, function(result) {
            callback("myerror");
    }, "Xmpp", "xmpp_send_message", [number_friend, message])
}
;

var callbackFunction = function(err, result) {
    console.log("Waiting ToCallback");
    if (err) {

    }
    else{
        alert(result);
    }
};

function mostrarhora(){ 
    var f = new Date();
    hour =  f.getHours()+":"+f.getMinutes(); 
    date = f.getDate() + "/" + (f.getMonth()) + "/" + f.getFullYear();
    setTimeout("mostrarhora()",1000); 
}