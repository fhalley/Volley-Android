alert("entro al js message");
var hour;
function init()  {
    mostrarhora();
    alert(hour);
    callNativePlugin(callbackFunction);
}
var el = document.getElementById("send");
el.addEventListener("click", init, false);

 function callNativePlugin(callback) {
        var message = $('#textchat').val();
        //var message = $('#textchat').val();
        cordova.exec(function(result) {
        callback(null, result);
        }, function(result) {
            callback("myerror");
        }, "Xmpp", "sendmsg", [message, date])
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

//cad=f.getHours()+":"+f.getMinutes()+":"+f.getSeconds(); 
//window.status =cad;
setTimeout("mostrarhora()",1000); 
}