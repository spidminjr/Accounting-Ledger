package com.pluralsight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static Scanner userInput = new Scanner(System.in);
    private ArrayList<Transaction> transactions;
    private static final String TRANSACTION_FILE = "Transactions.csv";


    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        homeScreen();
    }

    public static void homeScreen()
    {
        System.out.println();
        System.out.println("Welcome to Plural accounting ledger");

        System.out.println("What can we get started on today?");
        System.out.println("D) Add deposit ");
        System.out.println("P) Make Payment");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("Please make your selection (D, P, L, X): ");
        String choice = userInput.nextLine().strip().toLowerCase();

        switch (choice)
        {
            case "d":
                addDeposit();
                break;
            case "p":
                makePayment();
                break;
        }
    }

    private void saveAsCsv()
    {
        File file = new File("files/Transaction.csv");

        try(FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter writer = new PrintWriter(fileWriter);)
        {
            for(Transaction transaction : transactions)
            {
                writer.printf("%s|%s|%s|%s|%.2f\n",transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());

            }
        }
        catch (IOException e)
        {

        }

    }

    private static void addDeposit()
    {
        try(PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE, true)))
        {
            System.out.println("-------------------------------------");
            System.out.println("Welcome to Plural deposit ");
            System.out.print("Date (yyyy-MM-dd): ");
            String date = userInput.nextLine();
            System.out.print("Time (HH:mm:ss): )");
            String time = userInput.nextLine();
            System.out.print("Deposit amount: ");
            double amount = userInput.nextDouble();
            userInput.nextLine();

            writer.println(date + "|" + time + "|" + "|" + amount);
            System.out.println("Deposit successful!");
        } catch (IOException e)
        {
            System.out.println("Error: Failed to add deposit." );
        }
    }

    private static void makePayment()
    {
      try(PrintWriter writer = new PrintWriter(new FileWriter(TRANSACTION_FILE), true))
      {
          System.out.println("-------------------------------------");
          System.out.println("Welcome to Plural payment ");
          System.out.print("Date (yyyy-MM-dd): ");
          String date = userInput.nextLine();
          System.out.print("Time (HH:mm:ss): )");
          String time = userInput.nextLine();
          System.out.print("Description: ");
          String description = userInput.nextLine();
          System.out.print("Vendor: ");
          String vendor = userInput.nextLine();
          System.out.print("Amount: ");
          double amount = userInput.nextDouble();
          userInput.nextLine();

          writer.println(date + "|" + time + "|" + "|" + description + "|" + vendor + "|" + (-amount));
          System.out.println("Payment successful!");
      } catch(IOException e)
      {
          System.out.println("Error: Failed to make payment." );
      }
    }
    
}