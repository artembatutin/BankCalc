package me.artembatutin.entry;

import me.artembatutin.transaction.Transaction;

/**
 * Represents an entry decoding model.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class Entry {

    /**
     * The index of the entry.
     */
    private int index;

    /**
     * The {@link EntryType} type.
     */
    private EntryType type;

    /**
     * Constructs a new {@link Entry}.
     * @param index the index.
     * @param type the type.
     */
    public Entry(int index, EntryType type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public void apply(Transaction transaction, String[] split) {
        this.type.apply(transaction, split[index]);
    }
}
