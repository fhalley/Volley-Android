function addFriend() {
    callNativePlugin(callbackFunction);
    console.log("Final");
}
var el = document.getElementById("add");
el.addEventListener("click", addFriend, false);

function callNativePlugin(callback) {
    var code = $("#phone").val();
    cordova.exec(function(result) {
        callback(null, result);
    }, function(result) {
        callback("myerror");
    }, "Xmpp", "xmpp_add_friend", [code])
}
;

var callbackFunction = function(err, result) {
    console.log("Waiting ToCallback");
    alert("entro al callback");
    if (err) {
       // window.location.replace("register.html");
       alert("Error");
    } else {
        // console.log("Ok for verification.");
        // console.log("success," + result);
        alert("se agrego "+result+ " a la lista de amigos.");
        window.location.replace("friend-list.html");
        //if(result == "User verificated"){
        //    window.location.replace("profile.html");
        // }
        //window.location.replace("profile.html");
    }
 };