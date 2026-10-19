package com.mycompany.prog5121poe;

import java.util.ArrayList;
import java.util.Scanner;

public class PROG5121POE {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Message> messages = new ArrayList<>();

        boolean running = true;

        System.out.println("==================================");
        System.out.println("       Welcome to QuickChat");
        System.out.println("==================================");

        while (running) {

            System.out.println("\nPlease select an option:");
            System.out.println("1. Send Message");
            System.out.println("2. View Sent Messages");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            String choice = input.nextLine().trim();

            switch (choice) {

                case "1":

                    System.out.print("Enter recipient's phone number: ");
                    String recipient = input.nextLine().trim();

                    System.out.print("Enter your message (maximum 250 characters): ");
                    String messageText = input.nextLine();

                    if (messageText.length() > 250) {
                        int excess = messageText.length() - 250;

                        System.out.println(
                                "Message exceeds 250 characters by "
                                + excess
                                + "; please reduce the size."
                        );
                        break;
                    }

                    Message message = new Message(recipient, messageText);

                    String phoneResult = message.checkRecipientCell();

                    if (!phoneResult.equals(
                            "Cell phone number successfully captured.")) {
                        System.out.println(phoneResult);
                        break;
                    }

                    System.out.println(phoneResult);
                    System.out.println("Message ready to send.");

                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");
                    System.out.print("Enter your choice: ");

                    String messageChoice = input.nextLine().trim();

                    switch (messageChoice) {

                        case "1":
                            System.out.println(message.SentMessage("send"));
                            messages.add(message);

                            System.out.println("\nMessage Details:");
                            System.out.println(message.printMessages());
                            break;

                        case "2":
                            System.out.println(
                                    message.SentMessage("disregard"));
                            break;

                        case "3":
                            System.out.println(
                                    message.SentMessage("store"));
                            break;

                        default:
                            System.out.println(
                                    "Invalid choice. Please choose 1, 2, or 3.");
                            break;
                    }

                    break;

                case "2":

                    if (messages.isEmpty()) {
                        System.out.println("No sent messages to display.");
                    } else {
                        System.out.println("\nMessages Sent:");

                        for (Message sentMessage : messages) {
                            System.out.println(sentMessage.printMessages());
                            System.out.println("------------------------------");
                        }
                    }

                    break;

                case "3":
                    running = false;
                    System.out.println("Thank you for using QuickChat!");
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please select 1, 2, or 3.");
                    break;
            }
        }

        System.out.println("\nTotal messages sent: " + messages.size());

        input.close();
    }
}
