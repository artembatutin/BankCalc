package me.artembatutin.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class Counter implements Comparable<Counter> {

    private String category;

    private double amount;

    private HashMap<String, Company> companies = new HashMap<>();


    public Counter(String category) {
        this.category = category;
    }

    public void apply(Transaction transaction) {
        amount += transaction.getAmountDebit();

        Company company = companies.getOrDefault(transaction.getDescription(), new Company(transaction.getDescription()));
        company.spent(transaction.getAmountDebit());
        companies.put(transaction.getDescription(), company);
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Spend");
        sb.append(' ').append(round(amount, 2)).append('$');
        sb.append(" on ").append(category);

        sb.append("\n");

        ArrayList<Company> companies = new ArrayList<>(this.companies.values());
        companies.sort(Company::compareTo);

        int place = 0;
        for(Company company : companies) {
            place++;
            if(place > 3)
                break;
            sb.append(place).append('-').append(company.getCompany()).append(" spent ").append(round(company.getAmountSpent(), 2)).append("$\n");
        }
        sb.append("\n");

        return sb.toString();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public int compareTo(Counter counter) {
        return this.getAmount() > counter.getAmount() ? -1 : 1;
    }
}
