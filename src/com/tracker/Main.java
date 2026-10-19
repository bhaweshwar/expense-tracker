package com.tracker;

import com.tracker.model.Transaction;
import com.tracker.model.TransactionType;
import com.tracker.repository.TransactionRepository;

public class Main {
    public static void main(String[] args) {
        TransactionRepository repo = new TransactionRepository();

        System.out.println("=== Testing Array Auto-Resizing ===");
        
        repo.add(new Transaction("Salary", 3500.00, TransactionType.INCOME));
        repo.add(new Transaction("Groceries", 124.50, TransactionType.EXPENSE));
        repo.add(new Transaction("Electricity Bill", 85.00, TransactionType.EXPENSE));

        System.out.println("Total Records: " + repo.getSize());
        for (Transaction t : repo.getAll()) {
            System.out.println(t);
        }

        System.out.println("\n=== Testing Deletion (ID #002) ===");
        boolean deleted = repo.deleteById(2);
        System.out.println("Deleted ID 2: " + deleted);

        System.out.println("\nRemaining Records:");
        for (Transaction t : repo.getAll()) {
            System.out.println(t);
        }
    }
}