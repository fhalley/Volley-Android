alert("entro al js frie3nda");
//var friendList;
var suma = 0;
var name1 = "";
var numero;

function init() {
    callNativePlugin(callbackFunction);
}
document.addEventListener("deviceready", init, false);

function callNativePlugin(callback) {
  alert("CalNative entro callNativePlugin frienda");

  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_get_friends", [])
}
;

var callbackFunction = function(err, result) {

  if (err) {
    console.log("entro al if.");

  } else {
    console.log("entro al else. lista amigos");

    for (i in result) { 
      //alert("Amigo "+[i]+":"+result[i].name + ". Numero:" + result[i].num);
      //name1.innerHTML += result[i].name + "<br/>"; //le da valor a cada <p>
      //number.innerHTML += result[i].num + "<br/>"; //le da valor a cada <p>
      //alert("Amigo "+[i]+":"+result[i].name + ". Numero:" + result[i].num);
      //name= result[i].name;
      //number=result[i].num;

      creardivuno(result[i].name, result[i].num);
      //creardivtres(result[i].name, result[i].num);
      suma= suma+1;
    }//for end

  }

};

/********************************************************
**************** SEND NUMBER OF FRIEND ******************
********************************************************/
function callNativePlugin2(callback) {
  alert("CalNative entro callNativePlugin frienda");
  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_recive_user_friend", [numero])
}
;

var callbackFunction2 = function(err, result) {
  if (err) {
    console.log("entro al if.");
  } else {
    console.log("entro al else. lista amigos");
  
  }
};
/********************************************************
************** END SEND NUMBER OF FRIEND ****************
********************************************************/ 

function getName(id_div){
  numero = document.getElementById(id_div).text;
  alert(numero);
  //Para envier el nombrea android
  callNativePlugin2(callbackFunction2);
  window.location.replace("chat-test.html");
}

function a(){   
  //
  divclearfix = document.createElement('a');
  divclearfix.setAttribute("id","contenedor");
  divclearfix.setAttribute("class","left");
  valorText2 = document.createTextNode("name1");
  divclearfix.appendChild(valorText2);
  document.getElementById('chatname').appendChild(divclearfix);

  creardivdos(nam, num);
  }
function i(){   
  //
  divclearfix = document.createElement('a');
  divclearfix.setAttribute("id","i" + suma);
  divclearfix.setAttribute("class","newMessage");

  document.getElementById('contenedor').appendChild(divclearfix);
  creardivdos(nam, num);
  }

//diseño freinds fondo
function creardivuno(nam, num){ 
//  
  divclearfix = document.createElement('div');
  divclearfix.setAttribute("id","listUno" + suma);
  divclearfix.setAttribute("class","listUserChat clearfix");

  valorText2 = document.createTextNode(nam);
  divclearfix.appendChild(valorText2);
  document.getElementById('friendlist').appendChild(divclearfix);

  creardivdos(nam, num);
}

function creardivdos(nam, num){ 
  //circulo de fondo
  divclearfix = document.createElement('a');
  divclearfix.setAttribute("id","listDos" + suma);
  divclearfix.setAttribute("class","left sUserPhoto");
   divclearfix.setAttribute("background-color","#000213");
  //divclearfix.setAttribute("href","chat.html");
  
  document.getElementById('listUno'+ suma).appendChild(divclearfix);
  crearlink(nam, num);  
}

function crearlink(nam, num){ 
  divclearfix = document.createElement('a');
  divclearfix.setAttribute("id","link" + suma);
  //divclearfix.setAttribute("href","chat.html");
  //divclearfix.setAttribute("onClick","getName(this.id)");
  document.getElementById('listDos' + suma).appendChild(divclearfix);
  crearimg(nam, num); 
}

function crearimg(nam, num){ 
  divclearfix = document.createElement('img');
  divclearfix.setAttribute("id","img" + suma);
  divclearfix.setAttribute("src","http://lorempixel.com/32/32/people/");
  document.getElementById('link' + suma).appendChild(divclearfix);
  creardivtres(nam, num);
}
//
function creardivtres(nam, num){ 
  divclearfix = document.createElement('div');
  divclearfix.setAttribute("id","listres" + suma);
  divclearfix.setAttribute("id","listres" + suma);
  divclearfix.setAttribute("class","left listDetailUser");
  //divclearfix.setAttribute("onClick","getName(this.id)");
  document.getElementById("listUno" + suma).appendChild(divclearfix);
  crearlinkname(nam, num);
}

function crearlinkname(nam, num){ 
  divclearfix = document.createElement('a');
  divclearfix.setAttribute("id","linkdos" + suma);
  divclearfix.setAttribute("class","userNameDetail");
  //divclearfix.setAttribute("href","chat.html");
  divclearfix.setAttribute("onClick","getName(this.id)");
  
  document.getElementById("listres" + suma).appendChild(divclearfix);
  creari(nam, num);  
}

function creari(nam, num){ 
  divclearfix = document.createElement('a');
  divclearfix.setAttribute("id","link1" + suma);
  divclearfix.setAttribute("class","sendMessage");
  //divclearfix.setAttribute("href","chat.html");
  document.getElementById('linkdos' + suma).appendChild(divclearfix);
  crearspan(nam, num);  
}

function crearspan(nam, num){   
  divclearfix = document.createElement('span');
  divclearfix.setAttribute("id","listdivcuatro" + suma);
  valorText1 = document.createTextNode(num);
  divclearfix.appendChild(valorText1);
  document.getElementById('link1' + suma).appendChild(divclearfix);
 // divclearfix.setAttribute("onClick","getName(listdivcuatro" + suma +")");
  creardivcuatro(nam, num);
  //divclearfix.setAttribute("onClick","getName(this.id)");
}
/*
function creardivcuatro(nam, num){ 
  divclearfix = document.createElement('div');
  divclearfix.setAttribute("id","clock" + suma);
  document.getElementById('listdivcuatro' + suma).appendChild(divclearfix);
  //alert(nam + num);
}

function nuevo() {  
   name1 = document.getElementById("link").text;
   alert("entro a nuevo(); "+ name1);
}
var el = document.getElementById("link");
el.addEventListener("click", nuevo, false);*/