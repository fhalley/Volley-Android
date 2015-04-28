alert("entro al js message");

var id_chat = "1";
var hour = "";
var date = "";
//var myPhone = "6311087061";
//var friendPhone = "987654321";
//var friendPhone = "887554221";
var friendPhone = "988655322";
//var phone = "6311087061";

//var message = "";
function init()  {
  console.log("entro al init");
    mostrarhora();
    callNativePlugin(callbackFunction);
alert(hour);
}
var el = document.getElementById("send");
el.addEventListener("click", init, false);

    function callNativePlugin(callback) {
       
        var message = $('#textchat').val();
        cordova.exec(function(result) {
            callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "xmpp_send_message", [id_chat, message, hour, date,friendPhone])
    }
    ;

var callbackFunction = function(err, result) {
    console.log("Waiting ToCallback");

    if (err) {

        }
        else{
           alert(result);
            // alert(result);
        }
 };

function mostrarhora(){ 
var f = new Date();
hour =  f.getHours()+":"+f.getMinutes(); 
date = f.getDate() + "/" + (f.getMonth()) + "/" + f.getFullYear();

//cad=f.getHours()+":"+f.getMinutes()+":"+f.getSeconds(); 
//window.status =cad;
setTimeout("mostrarhora()",1000); 
}