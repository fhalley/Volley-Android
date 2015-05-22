function init() {
	alert("entro al get-Userfriend.js ");
}
document.addEventListener("deviceready", init, false);


function getName(id_div){
  // var nombre = $("#"+id_div+"").val();
  var numero = document.getElementById(id_div).text;
  alert(numero);
  window.location.replace("chat-test.html");
}