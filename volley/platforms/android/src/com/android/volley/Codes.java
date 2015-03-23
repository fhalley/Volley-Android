package com.android.volley;

public class Codes {
	
    public String char_pass = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@!#$";
    public String char_code = "0123456789";
   // public String contrasena ="";
   // public String code ="";
    //public String password;
    //public String verification_code;

    public String generatePassword(){
        int LargoContrasena= 12;
        int longitud = char_pass.length();
        String pass = "";
        for(int i=0; i<LargoContrasena;i++){
           int numero = (int)(Math.random()*(longitud));
           String caracter = char_pass.substring(numero, numero+1);
           pass = pass+caracter;
           }
        return pass;
    }

   public String generateCode(){
       int LargoCode= 6;
       int longitud = char_code.length();
       String code = "";
       for(int i=0; i<LargoCode;i++){
           int numero = (int)(Math.random()*(longitud));
           String caracter = char_code.substring(numero, numero+1);
           code = code+caracter;
       }
       return code;
   }
}
