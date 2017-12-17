package Structures;


/**
 * Class to implement a hash map data structure.
 *
 * @param <K> The class of the keys to be used in this map.
 * @param <V> The class of the values to be used in this map.
 */
public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private int capacity = 91;
    private int size;

    /**
     * Creates a new hash map, with a set capacity. If two objects have the same hashcode, overflow lists are used.
     * @param cap the capacity for the hash table.
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(int cap) {
        capacity = cap;
        table = new Entry[capacity];
        size = 0;
    }

    /**
     * Creates a new hash map with the default capacity of 91. If two objects have the same hashcode, overflow lists are used.
     */
    @SuppressWarnings("unchecked")
    public MyHashMap(){
        table = new Entry[capacity];
        size = 0;
    }

    /**
     * Private method for the hash function. Uses the keys own {@link #hashCode()}
     * to generate hash, and sizes it into a suitable size for the set.
     *
     * @param key the object for which to generate a index
     * @return The index for this key.
     */
    private int hash(K key) {//Decides the entry's place in the table.
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Adds the key, value pair into this map.
     *
     * @param newKey   The object to be used as a key
     * @param newValue The object to be added as the value.
     */
    public void put(K newKey, V newValue) {
        if (newKey == null) return; //No empty keys allowed.

        int hash = hash(newKey);

        Entry<K, V> newEntry = new Entry<>(newKey, newValue, null);
        //check if the spot is empty. If so place new Entry there.
        if (table[hash] == null) {
            table[hash] = newEntry;
            size++;
            return;
        }

        Entry<K, V> current = table[hash];
        Entry<K, V> prev = null;

        while (current != null) { //This is the end.

            if (current.key.equals(newKey)) { //If the same key is entered, replace old one.
                if (prev == null) { //this is in the first point.
                    newEntry.next = current.next;
                    table[hash] = newEntry;
                    return;
                } else { //there is something before this point, so we slot new Entry here
                    newEntry.next = current.next;
                    prev.next = newEntry;
                    return;
                }
            }
            //Move forward a spot
            prev = current;
            current = current.next;
        }
        size++;
        //we have reached the end so just place newEntry here and nothing else is needed.
        prev.next = newEntry;


    }

    /**
     * Finds and returns the value that is associated with the given key.
     *
     * @param key The key which will be searched.
     * @return The value associated with the key. Null if there is no such key.
     */
    public V get(K key) {
        int hash = hash(key);

        if (table[hash] == null) return null;

        Entry<K, V> temp = table[hash];

        while (temp != null) {
            if (temp.key.equals(key)) //we have found the correct entry, so return value
                return temp.value;
            temp = temp.next;// Not this one, so check next one.
        }

        return null;//the correct one was not found.
    }

    /**
     * Returns the number of Key-Value pairs in the map
     *
     * @return The number of elements in this map.
     */
    public int size() {
        return size;
    }

    /**
     * Checks to see if the map contains the specified Key.
     *
     * @param key THe key to be searched
     * @return <code>true</code> if the specified key is in the map;
     * <code>false</code> otherwise.
     */
    public boolean containsKey(K key) { //this is basically the get method, but it returns boolean.
        int hash = hash(key);

        if (table[hash] == null) return false;

        Entry<K, V> temp = table[hash];

        while (temp != null) {
            if (temp.key.equals(key))
                return true;
            temp = temp.next;
        }

        return false;
    }


    /**
     * The class for the entries.
     *
     * @param <K> The class of keys
     * @param <V> The class of values
     */
    static class Entry<K, V> {//A class for Map to use, with key value mappings.
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
