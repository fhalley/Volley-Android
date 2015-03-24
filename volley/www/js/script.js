//upload text to conversation
   // JavaScript Document
 $(document).ready(function () {
	 var time;	 
	 var message_frend="e";
	 
	 function startTime()
	    {	
	    		today=new Date(); 
		    	h=today.getHours(); 
		    	m=today.getMinutes(); 
		    	s=today.getSeconds(); 
		    	m=checkTime(m); 
		    	s=checkTime(s); 		    	
		    	time=h+":"+m+":"+s;		    	
		    	//document.getElementById('reloj').innerHTML=h+":"+m+":"+s; 
		    	t=setTimeout('startTime()',500);
	   	} 
	    function checkTime(i) 
	    {
	    	if (i<10) 
	    	{
	    		i="0" + i;
	      	}
	       	return i;
	    }
	    window.onload=function()
	    {
	    	 startTime();
	    }
	 time="17:28:07";
	 
	   
	 //send msg   
	 $("#send").click(function(){
	        var Add;
	          Add = $('#textchat').val(
	              );
	              if(Add!='')
	              {
	            	  $(".contentChatIn").append("<!-- list user : e -->" +
	            	  		"<!-- list user : s -->" +
	            	  		"<div class='listUserChat clearfix'> " +
		  	          	 		"<div class='right sUserPhoto'> " +
		  	         	 		"<a href='#'><img></a></div>" +
		  	         	 		"<div class='right listDetailUser2'>" +
		  	         	 		"<a href='#' class='userNameDetail'><i class='sendMessage'></i>You <br/></a>"+
		  	         	 		"<span id='span'>"+ Add +"</span><div id='Clock'>"+ time +"</div></div></div>");      
		  	             		$('#textchat').val("");
		  	             		startTime();
	              }
	             else
	             {
	            	
	             }
	  		});
	  if( message_frend!='')
      {
    	  $(".contentChatIn").append("<!-- list user : e -->" +
    	  		"<!-- list user : s -->" +
    	  		"<div class='listUserChat clearfix'>" +
    			" <div class='left sUserPhoto'>" +
    			"<a href='chat.html'><img ></a></div>" +
    			"<div class='left listDetailUser1'>" +
    			"<a class='userNameDetail'><i class='sendMessage'></i>" +
    			" Acmoda Golmondo <br />" +
    			"</a><span>" + message_frend + "</span>" +
    			" <div id='Clock'>" + time + "</div></div>");
    	  		startTime();
    	  		message_frend="";          
      }
     else
     {
    	
     }
	  //change photos
	  $(function () {
		    $(":file").change(function () {
		        if (this.files && this.files[0]) {
		            var reader = new FileReader();
		            reader.onload = imageIsLoaded;
		            reader.readAsDataURL(this.files[0]);
		        }
		    });
		});

		function imageIsLoaded(e) {
		    $('#myImg').attr('src', e.target.result);
		};
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