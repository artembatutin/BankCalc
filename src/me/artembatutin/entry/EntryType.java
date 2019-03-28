package me.artembatutin.entry;

import me.artembatutin.transaction.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * An entry type to be used on decoding.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public enum EntryType {

    DATE {
        @Override
        public void apply(Transaction transaction, String in) {
            try {
                Date date = new SimpleDateFormat("YYYY-MM-DD").parse(in);
                transaction.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    },

    DESCRIPTION {
        @Override
        public void apply(Transaction transaction, String in) {
            transaction.setDescription(in);
        }
    },

    CATEGORY {
        @Override
        public void apply(Transaction transaction, String in) {
            transaction.setCategory(in);
        }
    },

    DEBIT {
        @Override
        public void apply(Transaction transaction, String in) {
            transaction.setAmountDebit(Double.parseDouble(in));
        }
    },

    CREDIT {
        @Override
        public void apply(Transaction transaction, String in) {
            transaction.setAmountCredit(Double.parseDouble(in));
        }
    },

    BALANCE {
        @Override
        public void apply(Transaction transaction, String in) {

        }
    };

    /**
     * Cached {@link HashMap} reference with {@link EntryType}s.
     */
    private final static HashMap<String, EntryType> types = new HashMap<>();

    static {

        //initially caches entry types for quick reference.
        for(EntryType type : values()) {
            System.out.println("registered: " + type.name());
            types.put(type.name().toLowerCase(), type);
        }

    }


    /**
     * Gets an {@link EntryType}.
     * @param in input string.
     * @return {@link EntryType} if retrieved otherwise <code>null</code>.
     */
    public static EntryType getType(String in) {
        return types.getOrDefault(in.toLowerCase(), null);
    }

    /**
     * Default apply method that will be used for each type.
     * @param transaction transaction decoding.
     * @param in input string part.
     */
    public void apply(Transaction transaction, String in) {
        //apply.
    }
}
