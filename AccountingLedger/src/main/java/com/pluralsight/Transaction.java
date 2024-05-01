package com.pluralsight;

public class Transaction
{
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount)
    {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 5) {
            System.err.println("Invalid transaction format: " + line);
            return null;
        }

        String date = parts[0];
        String time = parts[1];
        String description = parts[2];
        String vendor = parts[3];

        try {
            double amount = Double.parseDouble(parts[4]);
            return new Transaction(date, time, description, vendor, amount);
        } catch (NumberFormatException e) {
            System.err.println("Invalid amount format in transaction: " + line);
            return null;
        }
    }
}