var number_friend = "";
var message = "";
//var hour = "";
//var date = "";
alert("entro al message-test");
function clickSend() {
    number_friend = $("#a").val() + "volley.com";
    message = $('#message').val();
    alert("el mensaje se enviara "+ message +" a: " + number_friend);
    callNativePlugin(callbackFunction);
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