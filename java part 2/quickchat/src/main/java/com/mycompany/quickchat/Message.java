/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.Random;
import java.util.Scanner;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessagesSent = 0;
    
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    
    public boolean checkMessageLength() {
        if (messageText.length() <= 250) {
            System.out.println("Message ready to send.");
            return true;
        } else {
            int excess = messageText.length() - 250;
            System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
            return false;
        }
    }
    
    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() == 12) {
            String numbers = recipient.substring(3);
            if (numbers.matches("\\d+")) {
                return "Cell phone number successfully captured.";
            }
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String msgNum = String.valueOf(messageNumber);
        String[] words = messageText.split(" ");
        
        if (words.length == 0) {
            return firstTwo + ":" + msgNum + ":EMPTY";
        }
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = firstTwo + ":" + msgNum + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
    
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }
    
    public String sentMessage() {
        Scanner scanner = new Scanner(System.in);
        boolean isValidPhone = recipient.startsWith("+27") && recipient.length() == 12;
        
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1. Send Message");
        System.out.println("0. Disregard Message (Delete)");
        System.out.println("3. Store Message to send later");
        System.out.print("Choose option (1/0/3): ");
        
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            if (isValidPhone) {
                totalMessagesSent++;
                return "Message successfully sent.";
            } else {
                return "Cannot send message: Invalid phone number.";
            }
        } else if (choice == 0) {
            return "Press 0 to delete the message. Message deleted.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }
    
    public void storeMessage() {
        try {
            String jsonContent = "{\n";
            jsonContent += "  \"messageID\": \"" + messageID + "\",\n";
            jsonContent += "  \"messageNumber\": " + messageNumber + ",\n";
            jsonContent += "  \"recipient\": \"" + recipient + "\",\n";
            jsonContent += "  \"messageText\": \"" + messageText + "\",\n";
            jsonContent += "  \"messageHash\": \"" + messageHash + "\"\n";
            jsonContent += "},\n";
            
            java.io.FileWriter writer = new java.io.FileWriter("messages.json", true);
            writer.write(jsonContent);
            writer.close();
            
            System.out.println("Message stored to messages.json");
        } catch (Exception e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
    
    public String printMessage() {
        return "Message ID: " + messageID + 
               ", Message Hash: " + messageHash + 
               ", Recipient: " + recipient + 
               ", Message: " + messageText;
    }
    
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
}