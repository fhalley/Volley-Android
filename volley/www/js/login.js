
function init() {
  //var phone2 = $("#number").val();
  //alert(phone2);
  callNativePlugin(callbackFunction);
  callNativePlugin2(callbackFunction2);


}
var el = document.getElementById("login");
el.addEventListener("click", init, false);
 /*  
var pagina="www.yahoo.com"
function redireccionar() 
{
location.href=pagina
} 
*/
function callNativePlugin(callback) {
  alert("entro al");
  var phone = $("#number").val();
  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_verify_postgres", [phone])
};

var callbackFunction = function(err, result) {
  console.log("Waiting ToCallback");
  if (err){
    console.log("get in the if.");
  } else {
    //console.log("entro al else. autologin.js");
    //alert(result);
  }
};

function callNativePlugin2(callback) {
  alert("entro al plugin 2");
//  var phone = $("#number").val();
  cordova.exec(function(result) {
    callback(null, result);
  }, function(result) {
    callback("myerror");
  }, "Xmpp", "xmpp_verify_postgres_callback", [])
};

var callbackFunction2 = function(err, result) {
 console.log("Waiting ToCallback 2");
 alert("entro al plugin 2");
  if (err){
    alert("entro al if err");
    console.log("get in the if.");
  } else {
  //  console.log("entro al else. autologin.js");

    alert(result);
    if (result == "true"){
      alert("The user exist.");
      console.log("Va llamar al html");
      window.location.replace("enter-code.html");
      // window.location.replace("profile.html");
      // redireccionar();
      // console.log("llamo al nuevo html");
      // window.open ("pro.html");
      // window.focus();
    } else {
      alert("The user does not exist.");

    }

  }
};

//<script language="JavaScript" type="text/javascript">


//setTimeout ("redireccionar()", 20000);

//</script>