package com.mycompany.chatapplication;

import java.util.Random;
import java.util.Scanner;

public class Message {
    private String messageID;
    private String recipientPhone;
    private String messageText = "";

    
    public String getMessageText() {
        return messageText;
    }

    public boolean checkMessageID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        this.messageID = sb.toString();
        return this.messageID.length() == 10;
    }

    public boolean checkRecipientCell(String phonenumber) {
        if (phonenumber != null && phonenumber.startsWith("+27") && phonenumber.length() >= 11) {
            this.recipientPhone = phonenumber;
            return true;
        }
        return false;
    }

    public String checkMessageLength(String message) {
        if (message != null && message.length() <= 250) {
            return "Message ready to send.";
        }
        return "Message exceeds character limits.";
    }

    public String selectTransmissionOption(int choice) {
        return switch (choice) {
            case 1 -> "Message successfully sent.";
            case 2 -> "Press 0 to delete the message.";
            case 3 -> "Message successfully stored.";
            default -> "Invalid option.";
        };
    }

    public String createMessageHash() {
        if (this.messageText == null || this.messageText.trim().isEmpty()) {
            return "00:0:EMPTY";
        }
        
        
        String CleanText = this.messageText.trim().toUpperCase();
        String[] words = CleanText.split("\\s+");
        
        
        String first = words[0];
        String last = words[words.length - 1];
        
        
        String part1 = first.length() >= 2 ? first.substring(0, 2) : first;
        String part2 = last.length() >= 7 ? last.substring(last.length() - 7) : last;
        
        return "00:0:" + part1 + part2;
    }

    public String sentMessage(String message, Scanner input) {
        this.messageText = message;
        
        
        if (message.length() > 250) {
            System.out.println(" Message exceeds 250 characters.");
            System.out.println("Select transmission handling options:");
            System.out.println("1) Send anyway");
            System.out.println("2) Delete options menu");
            System.out.println("3) Store locally");
            System.out.print("Choose (1-3): ");
            
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine(); 
                return selectTransmissionOption(choice);
            } else {
                input.nextLine(); 
                return "Invalid option entry.";
            }
        }
        
        
        return "Message processed cleanly. Hash generated: " + createMessageHash();
    }
}