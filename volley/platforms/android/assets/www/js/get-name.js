//js to get name
alert("entro al js getname");
//Var name
var name="";
//var el;
//While to get name
//el = document.getElementById("link");
//el.addEventListener("click", nuevo, false);
function nuevo(id) {	
	 name = document.getElementById(id).text;
	 alert("entro a nuevo(); "+ name);
}


