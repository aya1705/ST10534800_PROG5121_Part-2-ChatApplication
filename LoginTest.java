/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package chatappplication.testpackages;


import com.mycompany.chatapplication.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    Login login = new Login();
    
    @Test 
    public void testvalidUsername(){
        assertTrue(login.checkUserName("kyl_1"));
        
    }
   @Test 
public void testInvalidUsername_NoUnderscore(){
    assertFalse(login.checkUserName("kyle!!!!!!!"));
}
    
    @Test
    public void testInvalidUsername_TooLong(){
        assertFalse (login.checkUserName("kyle!!!!!!!"));
       
    }
@Test 
public void testvaildPassword(){
    assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99"));
  
}

@Test
public void testInvalidPassword_TooShort(){
   
    assertFalse(login.checkPasswordComplexity("Password"));
}

@Test
        public void testInvaidPassword_DoesNotHaveSpecialCharacters(){
            assertFalse(login.checkPasswordComplexity("password"));
        }
@Test
public void testvaildPhoneNumber(){
    //The phone number has to starrt with +27 
    assertTrue(login.checkCellPhoneNumber("+2783896877"));
}
@Test 
public void testInvaildPhoneNumber_DoesNotHaveSouthAfricanCode(){
    
    assertFalse(login.checkCellPhoneNumber("08966553"));
}
@Test
public void testInvalidPhoneNumber_TooShort(){
    
    //This will check how long the phone number is 
    assertFalse(login.checkCellPhoneNumber("08966553"));
}
// The test login 
    @Test
   public void testLoginSuccess(){
       // The user must first register27838968976");
       
        login.registerUser("kyl_1", "Ch&&sec@ke99","+27838968976");
       
       // Ater the user must attempt to login with the correct details 
       boolean result = login.loginUser("kyl_1","Ch&&sec@ke99");
      
       assertTrue(result);
   }
  @Test
  public void testLoginFail (){
      // The users must register 
      login.registerUser("kyl_1", "Ch&&sec@ke99", "+27838968976");
      
      //what will happen if user attemps login with an inncorrec t password
      boolean result = login.loginUser("kyl_1", "Wrong Pass1!");
      assertFalse (result);
  }


}
