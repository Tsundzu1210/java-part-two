/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.quickchat;

import org.junit.Test;
import org.junit.Assert;

public class MessageTest {
    
    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        boolean result = msg.checkMessageLength();
        Assert.assertEquals(true, result);
    }
    
    @Test
    public void testCheckMessageLengthFailure() {
        String longText = "This is a very long message. ";
        String veryLongText = longText.repeat(20);
        Message msg = new Message(1, "+27718693002", veryLongText);
        boolean result = msg.checkMessageLength();
        Assert.assertEquals(false, result);
    }
    
    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message(1, "+27718693002", "Hello");
        String result = msg.checkRecipientCell();
        Assert.assertEquals("Cell phone number successfully captured.", result);
    }
    
    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message(1, "08575975889", "Hello");
        String result = msg.checkRecipientCell();
        Assert.assertEquals(true, result.contains("incorrectly formatted"));
    }
    
    @Test
    public void testCreateMessageHash() {
        Message msg = new Message(1, "+27718693002", "Hi Mike dinner");
        String hash = msg.getMessageHash();
        Assert.assertNotNull(hash);
    }
    
    @Test
    public void testMessageIDIs10Digits() {
        Message msg = new Message(1, "+27718693002", "Hello");
        String id = msg.getMessageID();
        Assert.assertEquals(10, id.length());
    }
}