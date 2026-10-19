package com.tracker;

import com.tracker.model.Transaction;
import com.tracker.model.TransactionType;

public class Main {
    public static void main(String[] args) {
        Transaction t1 = new Transaction("Monthly Salary", 3500.00, TransactionType.INCOME);
        Transaction t2 = new Transaction("Grocery Store", 124.50, TransactionType.EXPENSE);

        System.out.println("=== Testing Model Setup ===");
        System.out.println(t1);
        System.out.println(t2);
    }
}