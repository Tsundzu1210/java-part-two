/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quickchat;


import org.junit.Test;
import org.junit.Assert;

public class Mytests {
    
   @Test
    public void testCheckUserNameCorrect() {
        Assert.assertEquals(true, Login.checkUserName("tan_1"));
    }
    
    @Test
    public void testCheckUserNameIncorrect() {
        Assert.assertEquals(false, Login.checkUserName("tania!!!!!"));
    }
    
    // ========== PASSWORD TESTS ==========
    @Test
    public void testCheckPasswordComplexityCorrect() {
        Assert.assertEquals(true, Login.checkPasswordComplexity("tsundzu#99!"));
    }
    
    @Test
    public void testCheckPasswordComplexityIncorrect() {
        Assert.assertEquals(false, Login.checkPasswordComplexity("password"));
    }
    
    // ========== CELLPHONE TESTS ==========
    @Test
    public void testCheckCellPhoneNumberCorrect() {
        Assert.assertEquals(true, Login.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testCheckCellPhoneNumberIncorrect() {
        Assert.assertEquals(false, Login.checkCellPhoneNumber("08966553"));
    }
    
    // ========== REGISTER TEST ==========

    @Test
    public void testRegisterUser() {
        String result = Login.registerUser("tania", "tan_1", "tsundzu#99!", "+27838968976", "tan");
        Assert.assertEquals(true, result.contains("Welcome"));
    }
    
    // ========== LOGIN TESTS ==========
    @Test
    public void testLoginUserCorrect() {
        Login.registerUser("tania", "tan_1", "tsundzu#99!", "+27838968976", "tan");
        Assert.assertEquals(true, Login.loginUser("tan_1", "tsundzu#99!"));
    }
    
    @Test
    public void testLoginUserIncorrect() {
        Login.registerUser("tania", "tan_1", "tsundzu#99!", "+27838968976", "tan");
        Assert.assertEquals(false, Login.loginUser("wrong", "wrong"));
    }
    
    // ========== LOGIN STATUS TESTS ==========
    @Test
    public void testReturnLoginStatusSuccess() {
        Login.registerUser("tania", "tan_1", "tsundzu#99!", "+27838968976", "tan");
        String result = Login.returnLoginStatus(true);
        Assert.assertEquals(true, result.contains("Welcome"));
    }
    
    @Test
    public void testReturnLoginStatusFailed() {
        String result = Login.returnLoginStatus(false);
        Assert.assertEquals(true, result.contains("failed"));
    }
}
