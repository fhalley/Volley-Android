//upload text to conversation
   // JavaScript Document
 $(document).ready(function () {
	 var name = $("#name").text();
     var number = $("#number").text();;
		
	 //addfriend  
	  while( name!='')
      {
		  $("#name").text("");
	  		$("#number").text("");
		  if(number!=''){
    	  $(".contentChatIn").append("<!-- list user : e -->" +
    	  		"<!-- list user : s -->" +
    	  		"<div class='listUserChat clearfix'>" +
    			" <div class='left sUserPhoto'>" +
    			"<a href='#'><img ></a></div>" +
    			"<div class='left listDetailUser1'>" +
    			"<a class='userNameDetail'><i class='sendMessage'></i>"+ name +"<br />" +
    			"</a><span>" + number + "</span>" +
    			" <div id='Clock'></div></div>");
    	  		startTime();
    	  		message_frend="";
    	  		
		  }
		  else
			  {
			  
			  }
		  
      }
    
	  
     /* ************************ */
     // input type file
     // SEARCH INPUT ======================	

     $('textarea').addClass("idleField");
     $('textarea').focus(function () {
         $(this).removeClass("idleField").addClass("focusField");
         if (this.value == this.defaultValue) {
             this.value = '';
         }
         if (this.value != this.defaultValue) {
             this.select();
         }
     });
     $('textarea').blur(function () {
         $(this).removeClass("focusField").addClass("idleField");
         if ($.trim(this.value) == '') {
             this.value = (this.defaultValue ? this.defaultValue : '');
         }
     });


     $('input[type="text"]').addClass("idleField");
     $('input[type="text"]').focus(function () {
         $(this).removeClass("idleField").addClass("focusField");
         if (this.value == this.defaultValue) {
             this.value = '';
         }
         if (this.value != this.defaultValue) {
             this.select();
         }
     });
     $('input[type="text"]').blur(function () {
         $(this).removeClass("focusField").addClass("idleField");
         if ($.trim(this.value) == '') {
             this.value = (this.defaultValue ? this.defaultValue : '');
         }
     });

     $('input[type="textarea"]').addClass("idleField");
     $('input[type="textarea"]').focus(function () {
         $(this).removeClass("idleField").addClass("focusField");
         if (this.value == this.defaultValue) {
             this.value = '';
         }
         if (this.value != this.defaultValue) {
             this.select();
         }
     });
     $('input[type="textarea"]').blur(function () {
         $(this).removeClass("focusField").addClass("idleField");
         if ($.trim(this.value) == '') {
             this.value = (this.defaultValue ? this.defaultValue : '');
         }
     });

     $(".defaultText").focus(function (srcc) {
         if ($(this).val() == $(this)[0].title) {
             $(this).removeClass("defaultTextActive");
             $(this).val("");
         }
     });

     $(".defaultText").blur(function () {
         if ($(this).val() == "") {
             $(this).addClass("defaultTextActive");
             $(this).val($(this)[0].title);
         }
     });

     $(".defaultText").blur();
     
   });
 