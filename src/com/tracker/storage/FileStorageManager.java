package com.tracker.storage;

import com.tracker.model.Transaction;
import com.tracker.model.TransactionType;
import com.tracker.repository.TransactionRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileStorageManager {
    private static final String FILE_NAME = "transactions.csv";

    // Writes the entire active array to a CSV file
    public void saveAll(Transaction[] transactions) {
        // Using try-with-resources to automatically close the file stream
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.println(t.getId() + "," + t.getDescription() + "," + t.getAmount() + "," + t.getType());
            }
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Reads the CSV file and populates the repository array
    public void loadAll(TransactionRepository repository) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // No previous data exists
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String desc = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    TransactionType type = TransactionType.valueOf(parts[3]);

                    Transaction t = new Transaction(id, desc, amount, type);
                    repository.add(t);
                }
            }
            System.out.println("Successfully loaded existing records from disk.");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}