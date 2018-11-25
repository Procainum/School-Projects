public class HashSetLP<T> implements HashSetInterface {
    private T[] set;
    private final int DEFAULT_ENTRIES = 157;
    private int numberOfEntries;

    public HashSetLP() {
        @SuppressWarnings("unchecked")
        T[] tempSet = (T[])new Object[DEFAULT_ENTRIES];
        set = tempSet;
        numberOfEntries = 0;
    }

    public int size() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public boolean contains(Object item) {
        for(int i = 0; i < DEFAULT_ENTRIES; i++) {
            if (item.equals(set[i])) {
                return true;
            }
        }
        return false;
    }

    public Object[] toArray() {
        @SuppressWarnings("unchecked")
        T[] newSet = (T[])new Object[numberOfEntries];
        int j = 0;
        for(int i = 0; i < DEFAULT_ENTRIES; i++) {
            if(set[i] != null) {
                newSet[j++] = set[i];
            }
        }
        return newSet;
    }

    public boolean add(Object item) {
        if(!contains(item)) {
            int h = compressHash(hashCode(item));
            if(set[h] == null) {
                set[h] = (T) item;
                ++numberOfEntries;
                return true;
            }
            for(int i = 1; i < 32; i++) {
                int j = h + i;
                if(j > DEFAULT_ENTRIES - 1) {
                    break;
                }
                if(set[j] == null) {
                    set[j] = (T) item;
                    ++numberOfEntries;
                    return true;
                }
            }
        }
        return false;
    }

   public Object remove(Object item) {
       if (contains(item)) {
           for (int i = 0; i < DEFAULT_ENTRIES; i++) {
               if (set[i] != null && set[i].equals(item)) {
                   Object temp = set[i];
                   set[i] = null;
                   --numberOfEntries;
                   return temp;
               }
           }
       }
       return null;
   }

    public void clear() {
        for(int i = 0; i < DEFAULT_ENTRIES; i++) {
            set[i] = null;
        }
        numberOfEntries = 0;
    }

    //Creates the hashCode for any object
    private int hashCode(Object item) {
        String s = item.toString();
        int hash = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            hash = 31 * hash + s.charAt(i);
        }
        return hash;
    }

    // Used to bring the hashCode within the bounds of the set
    private int compressHash(int h) {
        return h % DEFAULT_ENTRIES;
    }
}
