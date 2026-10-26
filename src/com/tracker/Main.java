package com.tracker;

import com.tracker.model.TransactionType;
import com.tracker.repository.TransactionRepository;
import com.tracker.service.ExpenseService;
import com.tracker.storage.FileStorageManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionRepository repository = new TransactionRepository();
        ExpenseService service = new ExpenseService(repository);
        FileStorageManager storageManager = new FileStorageManager();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Starting Terminal Expense Tracker...");
        
        // Load data into repository before opening menu
        storageManager.loadAll(repository); 
        
        boolean running = true;

        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. View Financial Summary");
            System.out.println("5. Delete a Transaction");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the dangling newline character

            switch (choice) {
                case 1:
                    processNewTransaction(scanner, service, TransactionType.INCOME);
                    break;
                case 2:
                    processNewTransaction(scanner, service, TransactionType.EXPENSE);
                    break;
                case 3:
                    System.out.println("\n--- Transaction History ---");
                    for (var t : service.listAllTransactions()) {
                        System.out.println(t);
                    }
                    break;
                case 4:
                    System.out.println(service.generateSummaryReport());
                    break;
                case 5:
                    System.out.print("Enter Transaction ID to delete: ");
                    int id = scanner.nextInt();
                    if (service.removeTransaction(id)) {
                        System.out.println("Success: Transaction #" + id + " deleted.");
                    } else {
                        System.out.println("Error: Transaction ID not found.");
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("Saving records to database...");
                    storageManager.saveAll(service.listAllTransactions());
                    System.out.println("Save complete. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }
        }
        scanner.close();
    }

    private static void processNewTransaction(Scanner scanner, ExpenseService service, TransactionType type) {
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter amount: $");
        double amount = scanner.nextDouble();
        service.addTransaction(desc, amount, type);
    }
}