package com.tracker.repository;

import com.tracker.model.Transaction;

public class TransactionRepository {
    private Transaction[] transactions;
    private int size;
    private static final int INITIAL_CAPACITY = 2; // Set small to test dynamic resizing quickly

    public TransactionRepository() {
        this.transactions = new Transaction[INITIAL_CAPACITY];
        this.size = 0;
    }

    // Adds transaction and resizes array if capacity is reached
    public void add(Transaction transaction) {
        if (size == transactions.length) {
            resize();
        }
        transactions[size++] = transaction;
    }

    // Doubles the inner array capacity when full
    private void resize() {
        Transaction[] newArray = new Transaction[transactions.length * 2];
        for (int i = 0; i < transactions.length; i++) {
            newArray[i] = transactions[i];
        }
        transactions = newArray;
    }

    // Returns a copy containing only active valid elements
    public Transaction[] getAll() {
        Transaction[] activeTransactions = new Transaction[size];
        for (int i = 0; i < size; i++) {
            activeTransactions[i] = transactions[i];
        }
        return activeTransactions;
    }

    // Linear search by transaction ID
    public Transaction findById(int id) {
        for (int i = 0; i < size; i++) {
            if (transactions[i].getId() == id) {
                return transactions[i];
            }
        }
        return null;
    }

    // Deletes record by shifting subsequent elements left
    public boolean deleteById(int id) {
        int targetIndex = -1;
        for (int i = 0; i < size; i++) {
            if (transactions[i].getId() == id) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            return false;
        }

        // Shift left to fill gap
        for (int i = targetIndex; i < size - 1; i++) {
            transactions[i] = transactions[i + 1];
        }

        transactions[--size] = null; // Clear last element reference for GC
        return true;
    }

    public int getSize() {
        return size;
    }
}