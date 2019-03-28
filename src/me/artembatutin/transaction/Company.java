package me.artembatutin.transaction;

import java.util.Comparator;

public class Company implements Comparable<Company> {

    private String company;

    private double amountSpent;

    public Company(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void spent(double amountSpent) {
        this.amountSpent += amountSpent;
    }

    @Override
    public int compareTo(Company company) {
        return this.getAmountSpent() > company.getAmountSpent() ? -1 : 1;
    }
}
