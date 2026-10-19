package com.tracker.model;

public class Transaction {
    private static int idCounter = 1;
    
    private final int id;
    private String description;
    private double amount;
    private TransactionType type;

    public Transaction(String description, double amount, TransactionType type) {
        this.id = idCounter++;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("ID: #%03d | Type: %-7s | Amount: $%-8.2f | Description: %s", 
                id, type, amount, description);
    }
}