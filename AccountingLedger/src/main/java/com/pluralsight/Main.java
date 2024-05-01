package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner userInput = new Scanner(System.in);
    private static final String TRANSACTION_FILE = "files/Transactions.csv";
    private static final ArrayList<Transaction> transactions = new ArrayList<>();


    public static void main(String[] args) {
        homeScreen();
    }

    public static void homeScreen() {
        while (true) {
            System.out.println();
            System.out.println("Welcome to Plural accounting ledger");

            System.out.println("What can we get started on today?");
            System.out.println("D) Add deposit ");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Please make your selection (D, P, L, X): ");
            String choice = userInput.nextLine().strip().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting application.... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private void saveAsCsv() {
        File file = new File("files/Transaction.csv");

        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(fileWriter);) {
            for (Transaction transaction : transactions) {
                writer.printf("%s|%s|%s|%s|%.2f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                writer.close();

            }
        } catch (IOException e) {

        }

    }

    private static void addDeposit() {
        try (FileWriter fileWriter = new FileWriter(TRANSACTION_FILE, true);
            PrintWriter writer = new PrintWriter(fileWriter);
        ) {
            System.out.println("-------------------------------------");
            System.out.println("Welcome to Plural deposit ");
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            System.out.println("Enter description : ");
            String description = userInput.nextLine();

            System.out.println("Enter vendor name:");
            String vendorName = userInput.nextLine();

            System.out.print("Deposit amount: ");
            double amount = userInput.nextDouble();
            userInput.nextLine();

            writer.println(currentDate + "|" + currentTime + "|" + vendorName + "|" + description + "|" + amount);
            System.out.println("Deposit successful!");
        } catch (IOException e) {
            System.out.println("Error: Failed to add deposit.");
        }
    }

    private static void makePayment() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE), true)) {
            System.out.println("-------------------------------------");
            System.out.println("Welcome to Plural payment ");
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            System.out.print("Description: ");
            String description = userInput.nextLine();
            System.out.print("Enter vendor name: ");
            String vendor = userInput.nextLine();
            System.out.print("Deposit amount: ");
            double amount = userInput.nextDouble();
            userInput.nextLine();

            writer.println(currentDate + "|" + currentTime + "|" + "|" + description + "|" + vendor + "|" + (-amount));
            System.out.println("Payment successful!");
        } catch (IOException e) {
            System.out.println("Error: Failed to make payment.");
        }
    }

    private static void displayLedger() {
        try (Scanner fileScanner = new Scanner(new File(TRANSACTION_FILE))) {
            ArrayList<Transaction> transactions = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Transaction transaction = Transaction.fromString(line);
                transactions.add(transaction);
            }

            while (true) {
                System.out.println("-------------------------------------");
                System.out.println("Welcome to Plural Ledger ");
                System.out.println("Please pick one of the following options");
                System.out.println("A) Display all transactions");
                System.out.println("D) Display deposits");
                System.out.println("P) Display payments");
                System.out.println("O) Back to main menu");

                System.out.println("Enter one of the following choices");
                String choice = userInput.nextLine().strip().toUpperCase();

                switch (choice) {
                    case "A":
                        System.out.println("\nAll Transactions: ");
                        for (Transaction transaction : transactions) {
                            System.out.println(transaction.toString());
                        }
                        break;
                    case "D":
                        System.out.println("\nDeposits: ");
                        for (Transaction transaction : transactions) {
                            if (transaction.getAmount() > 0) {
                                System.out.println(transaction.toString());
                            }
                        }
                        break;
                    case "P":
                        System.out.println("\nPayments: ");
                        for (Transaction transaction : transactions) {
                            if (transaction.getAmount() < 0) {
                                System.out.println(transaction.toString());
                            }
                        }
                        break;
                    case "O":
                        System.out.println("Back to main menu");
                        return;
                    default:
                        System.out.println("Invalid selection. Try again.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Transactions file not found.");
        }
    }
}