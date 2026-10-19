package com.tracker.service;

import com.tracker.model.Transaction;
import com.tracker.model.TransactionType;
import com.tracker.repository.TransactionRepository;

public class ExpenseService {
    private final TransactionRepository repository;

    public ExpenseService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void addTransaction(String description, double amount, TransactionType type) {
        if (amount <= 0) {
            System.out.println("Error: Amount must be greater than zero.");
            return;
        }
        Transaction t = new Transaction(description, amount, type);
        repository.add(t);
        System.out.println("Transaction added successfully with ID: #" + String.format("%03d", t.getId()));
    }

    public boolean removeTransaction(int id) {
        return repository.deleteById(id);
    }

    public Transaction[] listAllTransactions() {
        return repository.getAll();
    }

    public double getTotalIncome() {
        double total = 0;
        for (Transaction t : repository.getAll()) {
            if (t.getType() == TransactionType.INCOME) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getTotalExpense() {
        double total = 0;
        for (Transaction t : repository.getAll()) {
            if (t.getType() == TransactionType.EXPENSE) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    // Constructs a formatted report using StringBuilder for memory efficiency
    public String generateSummaryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=========================================\n");
        sb.append("         FINANCIAL SUMMARY REPORT        \n");
        sb.append("=========================================\n");
        sb.append(String.format("Total Income  : $%.2f%n", getTotalIncome()));
        sb.append(String.format("Total Expense : $%.2f%n", getTotalExpense()));
        sb.append("-----------------------------------------\n");
        sb.append(String.format("Net Balance   : $%.2f%n", getNetBalance()));
        sb.append("=========================================\n");
        return sb.toString();
    }
}