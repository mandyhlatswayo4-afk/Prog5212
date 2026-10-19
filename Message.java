package com.mycompany.prog5121poe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static int totalMessages = 0;

    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long number = 100000000L + random.nextInt(900000000);
        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null
                && (recipient.matches("^\\+[1-9]\\d{7,14}$")
                || recipient.matches("^0\\d{9,10}$"))) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return "00:0:";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return "00:0:" + (firstWord + lastWord).toUpperCase();
    }

    public String SentMessage(String choice) {
        if (choice == null) {
            return "Invalid choice.";
        }

        switch (choice.trim().toLowerCase()) {

            case "send":
                totalMessages++;
                return "Message successfully sent.";

            case "disregard":
            case "discard":
                return "Press 0 to delete the message.";

            case "store":
                return storeMessage();

            default:
                return "Invalid choice.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    public String storeMessage() {
        try (FileWriter writer = new FileWriter(
        "C:/Users/Student/Documents/NetBeansProjects/PROG5212/messages.json", true)) {
            String safeRecipient = recipient
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"");

            String safeMessage = messageText
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r");

            String json = "{"
                    + "\"messageID\":\"" + messageID + "\","
                    + "\"messageHash\":\"" + messageHash + "\","
                    + "\"recipient\":\"" + safeRecipient + "\","
                    + "\"message\":\"" + safeMessage + "\""
                    + "}" + System.lineSeparator();

            writer.write(json);

            return "Message successfully stored.";

        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
        }
    }

    public int returnTotalMessages() {
        return totalMessages;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }
}
