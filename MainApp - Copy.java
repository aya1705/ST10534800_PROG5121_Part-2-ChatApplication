package com.mycompany.chatapplication;

import java.util.Scanner;
import java.util.ArrayList; 

public class MainApp {

    public static void main(String[] args) {
        Login login = new Login();
       
        ArrayList<String> sentMessages = new ArrayList<>(); 
        int totalMessagesSent = 0;
        
        try (Scanner input = new Scanner(System.in)) {
            
            // === USER REGISTRATION ===
            System.out.println("=== USER REGISTRATION ===");
            System.out.print("Enter a username: ");
            String username = input.nextLine();
            
            System.out.print("Enter a Password: ");
            String password = input.nextLine();
            
            System.out.print("Enter your South African phone number (+27....): ");
            String phone = input.nextLine();
            
            String response = login.registerUser(username, password, phone);
            System.out.println(response); 
            
            if (!login.checkUserName(username) || !login.checkPasswordComplexity(password)) {
                System.out.println("Registration failed formatting requirements. Exiting system context.");
                return;
            }

            
            boolean loggedIn = false;
            int loginAttempts = 0;
            
            System.out.println("===== USER LOGIN =====");
            
            while (!loggedIn && loginAttempts < 3) {
                System.out.println("System initialized. Attempts remaining: " + (3 - loginAttempts));
                System.out.print("Enter your username: ");
                String loginUsername = input.nextLine();
                
                System.out.print("Enter your password: ");
                String loginPassword = input.nextLine();
                
                loggedIn = login.loginUser(loginUsername, loginPassword);
                
                if (!loggedIn) {
                    loginAttempts++;
                    System.out.println("Invalid credentials.");
                }
            }
            
            if (!loggedIn) {
                System.out.println("Access Denied. Too many failed login attempts. Exiting application.");
                return;
            }
            
            System.out.println(login.returnLoginStatus(loggedIn));
            System.out.println("Welcome to ChatApp.");
            
          
            Message messageApp = new Message();
            boolean isValidCell = false;
            int phoneAttempts = 0;
            String recipientPhone = "";
            
            while (!isValidCell && phoneAttempts < 3) {
                System.out.print("Please Enter recipient phone number: ");
                recipientPhone = input.nextLine();
                
                isValidCell = messageApp.checkRecipientCell(recipientPhone);
                
                if (!isValidCell) {
                    phoneAttempts++;
                    System.out.println("Incorrect format. Must start with +27 and be a valid SA number.");
                    if (phoneAttempts < 3) {
                        System.out.println("Try again. Attempts remaining: " + (3 - phoneAttempts));
                    }
                }
            }
            
            if (!isValidCell) {
                System.out.println("System Error: Invalid phone number. Exiting.");
                return;
            }
            
            System.out.println("Cell phone number successfully captured.");
            
            
            boolean running = true;
            while (running) {
                System.out.println("--- ChatApp Menu ---");
                System.out.println("1) Send message");
                System.out.println("2) Show messages");
                System.out.println("3) Quit");
                System.out.print("Choose: ");
                
                
                if (!input.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
                    input.nextLine(); 
                    continue;
                }
                
                int choice = input.nextInt();
                input.nextLine(); 
                
                switch (choice) {
                    case 1 -> {
                        System.out.print("How many messages would you like to send? ");
                        if (input.hasNextInt()) {
                            int count = input.nextInt();
                            input.nextLine(); 
                            
                            for (int i = 0; i < count; i++) {
                                System.out.print("Enter message " + (i + 1) + ": ");
                                String msgText = input.nextLine();
                                
                                sentMessages.add(msgText);
                                totalMessagesSent++;
                            }
                            System.out.println(">>> Success: " + count + " message(s) sent to " + recipientPhone + "!");
                            System.out.println(">>> Total messages sent this session: " + totalMessagesSent);
                        } else {
                            System.out.println("Invalid number count.");
                            input.nextLine(); 
                        }
                    }
                    case 2 -> {
                        System.out.println("--- Sent Messages Log ---");
                        System.out.println("Coming soon...");
                        
                        
                        String latestMessage = sentMessages.isEmpty() ? "No message text found." : sentMessages.get(sentMessages.size() - 1);
                        
                        
                        System.out.println("=========================================");
                        System.out.println("Message ID: MSG-" + (sentMessages.size()));
                        System.out.println("Message Hash: #HSH" + latestMessage.hashCode());
                        System.out.println("Recipient: " + recipientPhone);
                        System.out.println("Message: " + latestMessage);
                        System.out.println("=========================================");
                        
                        
                        System.out.println("What would you like to do with the entered message?");
                        System.out.println("1. Send message2. Discard message. Store message");
                        System.out.print("Enter choice (1-3): ");
                        String actionChoice = input.nextLine();
                        System.out.println("Action processed successfully.");
                        
                        
                        System.out.print("Please enter a second message: ");
                        String secondMessage = input.nextLine();
                        sentMessages.add(secondMessage);
                        totalMessagesSent++;
                        System.out.println("Second message captured");
                    }
                    case 3 -> {
                        System.out.println("Thank you for using ChatApp. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            }
        }
    }
} 