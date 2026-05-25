/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapplication;


/**
 *
 * @author One eyed King
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Login {
        
    // Variable declarations used to store the user's details.
    //This is where the user input shall be saved.
    //--------------
    String Username;
    String password;
    String Phonenumber;
    
    
    
    //=========================
    //Teachers instructions below 
    //1.Checking username
    //You are required to ensure :
    //IF the username has an underscore 
    //IF the username is within the range check or has 5 characters including the_ 
    
    public boolean checkUserName (String username){
        
        //username.contains("_") checks for underscores
        // username.length() <=5 ensures or checks if characters fall under range check
        
        return username.contains("_") && username.length() <=5;
        
    }
    
    public boolean checkPasswordComplexity(String password){
    //Checking if password validation has :
    //The password should at least have 8 characters
    //Must contain one special character
    //
    boolean hasSpecial = false;
    boolean hasNumber = false;
    boolean hasCapital = false;
    
    //Looping through each character in the loop
    
    for (int i = 0; i < password.length(); i++){
       char c = password.charAt(i);//gets the current character
    
     if (Character.isUpperCase(c)){
        hasCapital = true;
        }else if (Character.isDigit(c)){
            hasNumber = true;
        }else if(!Character.isLetterOrDigit(c)){
          hasSpecial = true;  
        }
      }
     return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber(String phone){
     return phone.startsWith("+27") && phone.length() <= 12;
    }
    
   public String registerUser(String username, String password, String phoneNumber){
     if(!checkUserName(username)){
      return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters inlength";   
     }
     if(!checkPasswordComplexity(password)){
         return"Password is not correctly formatted; please ensure that the password contains at least eight characters,a capital letter, a number, and a special character";
     }
     if (!checkCellPhoneNumber(phoneNumber)){
       return"Cell phone number incorrectly formatted or does not contain international code";  
     }
   this.Username = username;
    this.password = password;
    this.Phonenumber = phoneNumber;
    
    // Save user data into file (overwrites existing data)
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"))) {
        writer.write(username + "," + password + "," + phoneNumber);
    } catch (IOException e) {
        return "Error saving user :";
    }
    
    return "User registered successfully";
}

public boolean loginUser(String username, String password) {
    // Check for null first, then compare using the passed-in arguments.
    // This safely returns false instead of crashing if the class variables are null.
    return username != null && username.equals(this.Username) && 
           password != null && password.equals(this.password);
}

public String returnLoginStatus(boolean success) {
    if (success) {
        // Fallback to a clean greeting if Username happens to be null
        String displayName = (this.Username != null) ? this.Username : "User";
        return "Welcome " + displayName + ", it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }
}
}
