/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat;

import java.util.Scanner;

public class Quickchat {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter your firstname: ");
        String firstname = scan.next();
        
        System.out.print("Enter your lastname: ");
        String lastname = scan.next();
        
        System.out.print("Enter your username: ");
        String username = scan.next();
        
        System.out.print("Enter your password: ");
        String password = scan.next();
        
        System.out.print("Enter your cellphone: ");
        String cellphone = scan.next();
        
        String result = Login.registerUser(firstname, lastname, username, password, cellphone);
        System.out.println(result);
        
        if(result.contains("is not correctly")) {
            return;
        }
        
        boolean loggedIn = false;
        int attempts = 0;
        int maxAttempts = 3;
        
        System.out.println("\n=== LOGIN ===");
        
        while (!loggedIn && attempts < maxAttempts) {
            System.out.print("Enter your username: ");
            String loginUser = scan.next();
            
            System.out.print("Enter your password: ");
            String loginPass = scan.next();
            
            boolean loginResult = Login.loginUser(loginUser, loginPass);
            
            if (loginResult) {
                System.out.println(Login.returnLoginStatus(loginResult));
                loggedIn = true;
            } else {
                attempts++;
                int remaining = maxAttempts - attempts;
                System.out.println("Login failed! Incorrect username or password.");
                
                if (remaining > 0) {
                    System.out.println("You have " + remaining + " attempt(s) remaining.");
                    System.out.println("Please try again.\n");
                } else {
                    System.out.println("Too many failed attempts! Exiting program.");
                    return;
                }
            }
        }
        
        if (loggedIn) {
            showMessagingMenu(scan);
        }
    }
    
    public static void showMessagingMenu(Scanner scan) {
        System.out.println("\n=== Welcome to QuickChat! ===");
        
        System.out.print("How many messages do you want to send today? ");
        int numMessages = scan.nextInt();
        scan.nextLine();
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== QuickChat Menu ===");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            
            int choice = scan.nextInt();
            scan.nextLine();
            
            if (choice == 1) {
                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
                    
                    System.out.print("Enter recipient phone number (+27...): ");
                    String recipient = scan.nextLine();
                    
                    System.out.print("Enter your message (max 250 chars): ");
                    String messageText = scan.nextLine();
                    
                    Message msg = new Message(i + 1, recipient, messageText);
                    
                    if (!msg.checkMessageLength()) {
                        System.out.println("Message too long! Skipping this message...\n");
                        continue;
                    }
                    
                    String phoneCheck = msg.checkRecipientCell();
                    System.out.println(phoneCheck);
                    
                    if (!phoneCheck.equals("Cell phone number successfully captured.")) {
                        System.out.println("Message REJECTED: Invalid phone number! Not counted.\n");
                        continue;
                    }
                    
                    String sendResult = msg.sentMessage();
                    System.out.println(sendResult);
                    
                    if (sendResult.equals("Message successfully sent.")) {
                        System.out.println("\n=== Message Details ===");
                        System.out.println(msg.printMessage());
                        System.out.println("Message COUNTED as sent!\n");
                    } else if (sendResult.equals("Message successfully stored.")) {
                        System.out.println("Message saved to storage (not counted).\n");
                    } else if (sendResult.contains("deleted")) {
                        System.out.println("Message deleted (not counted).\n");
                    }
                }
                
                System.out.println("\n=== SUMMARY ===");
                System.out.println("Total messages successfully sent: " + Message.returnTotalMessages());
                
            } else if (choice == 2) {
                System.out.println("Coming Soon. This feature is still in development.");
                
            } else if (choice == 3) {
                System.out.println("Goodbye! Thanks for using QuickChat.");
                running = false;
                
            } else {
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }
}