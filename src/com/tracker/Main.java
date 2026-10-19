package com.tracker;

import com.tracker.model.TransactionType;
import com.tracker.repository.TransactionRepository;
import com.tracker.service.ExpenseService;

public class Main {
    public static void main(String[] args) {
        TransactionRepository repository = new TransactionRepository();
        ExpenseService service = new ExpenseService(repository);

        System.out.println("=== Testing Service Layer Logic ===");
        service.addTransaction("Monthly Salary", 3500.00, TransactionType.INCOME);
        service.addTransaction("Freelance Work", 450.00, TransactionType.INCOME);
        service.addTransaction("Grocery Shopping", 120.50, TransactionType.EXPENSE);
        service.addTransaction("Internet Bill", 60.00, TransactionType.EXPENSE);

        // Display financial calculations report
        System.out.println(service.generateSummaryReport());
    }
}