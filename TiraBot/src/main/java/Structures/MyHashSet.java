package Structures;

import bot.Vertex;

/**
 * Implements a hash set data structure for Vertex
 *
 * @see bot.Vertex
 */

public class MyHashSet {
    private Entry[] table;
    private int size;
    private int capacity = 91;

    /**
     * Creates a new HashSet for Vertex objects
     *
     * @see bot.Vertex
     */
    public MyHashSet() {
        table = new Entry[capacity];
        size = 0;
    }

    /**
     * Creates a new HashSet for Vertex objects, with custom capacity fot the hash table.
     * @param cap the capacity for the hash table.
     */
    public MyHashSet(int cap) {
        capacity = cap;
        table = new Entry[capacity];
        size = 0;
    }


    /**
     * Private method for the hash function. Uses {@link #hash(Vertex)}
     * to generate hash, and sizes it into a suitable size for the set.
     *
     * @param v the Vertex to generate a index
     * @return The index for this Vertex
     */
    private int hash(Vertex v) {
        return Math.abs(v.hashCode()) % capacity;
    }

    /**
     * Adds the vertex into the this set
     *
     * @param v Vertex to add into the set.
     */
    public void add(Vertex v) {
        if (v == null) return;
        int hash = hash(v);

        Entry newEntry = new Entry(v, null);
        size++;

        if (table[hash] == null) {
            table[hash] = newEntry;
            return;
        }

        Entry current = table[hash];
        while (current.next != null) {
            current = current.next;
        }
        current.next = newEntry;
    }

    /**
     * Checks whether the vertex is already in this set.
     *
     * @param v Vertex to be checked
     * @return <code>true</code> if the set contains this vertex;
     * <code>false</code> otherwise
     */
    public boolean contains(Vertex v) {
        int hash = hash(v);
        if (table[hash] == null)
            return false;
        Entry current = table[hash];

        if (current.vertex.equals(v))
            return true;

        while (current.next != null) {
            if (current.vertex.equals(v)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes the specified vertex from the set.
     *
     * @param v Vertex to be removed
     * @return The vertex that was removed.
     * If the set does not contain this vertex, returns null.
     */
    public Vertex remove(Vertex v) {
        int hash = hash(v);
        if (table[hash] == null)
            return null;
        Entry current = table[hash];
        Entry prev = null;
        while (current.next != null) {
            if (v.equals(current.vertex)) {
                if (prev != null) {
                    prev.next = current.next;
                }
                return current.vertex;
            }
            prev = current;
            current = current.next;
        }
        return null;

    }

    /**
     * Returns the number of entries in the set.
     *
     * @return The number of objects in this set.
     */
    public int size() {
        return size;
    }

    /**
     * A class for hash set entries. Contains the vertex and the next entry
     */
    static class Entry {
        Vertex vertex;
        Entry next;

        public Entry(Vertex vertex, Entry next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
}
