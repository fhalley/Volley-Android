var number;
var username;
var password;

var generarPassword = function(){
	text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    var rcv_numero="";
    for( var i=0; i < 7; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));
    return text;
}

var reciveUserInfo = function(){
	number = document.f1.txt_num.value;
	username = document.f1.txt_username.value;
	password = generarPassword();

	alert("USERNAME: " + username + ". PASSWORD: "+ password + ". NUMBER: "+ number);
}