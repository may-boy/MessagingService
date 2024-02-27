package org.example.message.transformation;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String customerId;
    private double amount;
    private String transactionType;
    // Other transaction attributes, getters, and setters...

    public Transaction(String customerId, double amount, String transactionType) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
