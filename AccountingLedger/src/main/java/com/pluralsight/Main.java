package com.pluralsight;

import java.util.Scanner;

public class Main
{
    private static Scanner userInput = new Scanner(System.in);

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

        }
    }
}