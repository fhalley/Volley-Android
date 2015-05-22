alert("entro al js getmessage");
//var friendList;
var number;
var name;
function init() {
  callNativePluginGetUserName(callbackFunctionGetUserName);
  callNativePlugin(callbackFunction);
}
document.addEventListener("deviceready", init, false);

/********************************************************
*************** RECIV NUMBER/NAME FRIEND ****************
********************************************************/ 
function callNativePluginGetUserName(callback) {
  alert("CalNative entro callNativePlugin frienda");
  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_send_friend", [])
}
;

var callbackFunctionGetUserName = function(err, result) {
  if (err) {
    console.log("entro al if.");
  } else {
    console.log("entro al else. lista amigos");
    number = result.number;
    name = result.username;
    //message.innerHTML = name;
    alert(number + " " + name)
  }
};
/********************************************************
************** END SEND NUMBER OF FRIEND ****************
********************************************************/ 

function callNativePlugin(callback) {
  alert("CalNative entro callNativePlugin message");

  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_get_messages", [])
}
;

var callbackFunction = function(err, result) {
  if (err) {
    console.log("entro al if.");
  } else {
    console.log("entro al else. lista amigos");
    for (i in result) { 
      // alert("Amigo "+[i]+":"+result[i].name + ". Numero:" + result[i].num);
      //name1.innerHTML += result[i].name + "<br/>"; //le da valor a cada <p>
        //number.innerHTML += result[i].num + "<br/>"; //le da valor a cada <p>
        //alert("Amigo "+[i]+":"+result[i].name + ". Numero:" + result[i].num);
        //name= result[i].name;
        //number=result[i].num;
        friendMessage(result[i].message);
        myMessage(result[i].message);
        // alert(result[i].user, result[i].message);
        //creardivuno(result[i].name, result[i].num);
        //creardivtres(result[i].name, result[i].num);
        //suma= suma+1;
    }//for end
  }
};

function friendMessage(msj){
    var midiv = document.createElement("div");
        midiv.setAttribute("id","");
        midiv.setAttribute("class","left listDetailUser1");
        midiv.innerHTML = msj;
    document.getElementById("chat").appendChild(midiv); 
}

function myMessage(msj){
    var midiv = document.createElement("div");
        midiv.setAttribute("id","");
        midiv.setAttribute("class","left listDetailUser2");
    //     midiv.setAttribute("otros atributos","otros");
        midiv.innerHTML = msj;
    document.getElementById("chat").appendChild(midiv); 
}