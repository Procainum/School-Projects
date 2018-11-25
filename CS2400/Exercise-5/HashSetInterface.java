public interface HashSetInterface<T> {
    /** Gets the current number of entries in this set.
     @return  The integer number of entries currently in the set. */
    public int size();

    /** Sees whether this set is empty.
     @return  True if the set is empty, or false if not. */
    public boolean isEmpty();

    /** Tests whether this set contains a given entry.
     @param item  The entry to locate.
     @return  True if the set contains anEntry, or false if not. */
    public boolean contains(T item);

    /** Retrieves all entries that are in this set.
     @return  A newly allocated array of all the entries in the set.
     Note: If the set is empty, the returned array is empty. */
    public T[] toArray();

    /** Adds a new entry to this bag.
     @param item  The object to be added as a new entry.
     @return  True if the addition is successful, or false if not. */
    public boolean add(T item);

    /** Removes one unspecified entry from this set, if possible.
     @return  Either the removed entry, if the removal.
     was successful, or null. */
    public T remove(T item);

    /** Removes all entries from this bag. */
    public void clear();
}
