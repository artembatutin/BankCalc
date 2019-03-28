package me.artembatutin.transaction;

import me.artembatutin.entry.Entry;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {

    private String description;

    private String category;

    private Date date;

    private double amountDebit;

    private double amountCredit;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmountDebit() {
        return amountDebit;
    }

    public void setAmountDebit(double amountDebit) {
        this.amountDebit = amountDebit;
    }

    public double getAmountCredit() {
        return amountCredit;
    }

    public void setAmountCredit(double amountCredit) {
        this.amountCredit = amountCredit;
    }

    @Override
    public int compareTo(Transaction transaction) {
        return this.getAmountDebit() > transaction.getAmountDebit() ? -1 : 1;
    }
}
