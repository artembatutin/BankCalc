package me.artembatutin;

import me.artembatutin.entry.Entry;
import me.artembatutin.entry.EntryType;
import me.artembatutin.transaction.Transaction;
import me.artembatutin.transaction.TransactionContainer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Calculates bank history and provides a small analysis.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class BankCalculator {

    /**
     * The character used to split csv files.
     */
    private static final String SPLIT_CHAR = ";";

    public static final void main(String... args) throws Exception {

        //reading file into a buffered reader.
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./bank_file.csv"))));

        //splitting first line.
        String layout = br.readLine();
        String[] parts = layout.split(SPLIT_CHAR);

        //decoding entries from first line.
        ArrayList<Entry> entries = new ArrayList<>();
        for(int i = 0; i < parts.length; i++) {
            EntryType type = EntryType.getType(parts[i]);
            if(type != null) {
                System.out.println("registered: " + type + " on " + i + " with " + parts[i]);
                entries.add(new Entry(i, type));
            }
        }

        //creating transaction container.
        TransactionContainer container = new TransactionContainer();

        //shared variables for memory efficiency.
        String line;
        String[] lineParts;
        //reading all other lines.
        while((line = br.readLine()) != null) {
            //creating one transaction.
            Transaction transaction = new Transaction();
            lineParts = line.split(SPLIT_CHAR);
            //decoding line with all entry decoders.
            for(Entry type : entries) {
                String in = lineParts[type.getIndex()].replaceAll("\"", "");
                type.getType().apply(transaction, in);
            }
            //adding transaction to container.
            container.getTransactions().add(transaction);
        }

        container.printReport();


    }

}
