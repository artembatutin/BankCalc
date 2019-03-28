package me.artembatutin.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class TransactionContainer {

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public void printReport() {
        HashMap<String, Counter> counters = new HashMap<>();

        for(Transaction transaction : transactions) {
            Counter c = counters.getOrDefault(transaction.getCategory(), new Counter(transaction.getCategory()));
            c.apply(transaction);
            counters.put(transaction.getCategory(), c);
        }

        ArrayList<Counter> sortedCounters = new ArrayList<>(counters.values());
        sortedCounters.sort(Counter::compareTo);
        sortedCounters.forEach(System.out::println);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
