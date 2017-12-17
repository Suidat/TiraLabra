package Structures;

import java.util.Iterator;

/**
 * Class that implements a linked list.
 *
 * @param <V> The class of objects to be placed into this linked list.
 */
public class MyLinkedList<V> implements Iterable<V> {

    private V value;
    private MyLinkedList<V> next;
    private int size;
    private MyLinkedList<V> end;

    /**
     * Creates a new and empty linked list.
     */
    public MyLinkedList() {
        value = null;
        next = null;
        size = 0;
        end = this;
    }

    /**
     * A private method, that adds a new object into the list.
     *
     * @param value The object to be added into this list.
     */
    private MyLinkedList(V value) {
        this.value = value;
        next = null;
        size = 1;
        end = this;
    }

    /**
     * Adds the specified object at the end of the list
     *
     * @param v Object to be added.
     */
    public void add(V v) {
        this.size = this.size + 1;
        end.next = new MyLinkedList<>(v);
        this.end = end.next;
    }

    /**
     * Returns the object at the specified index.
     *
     * @param i Index of object between 0 and the size of the list.
     * @return The object at index;
     * if the index is out of bounds returns null.
     */
    public V get(int i) {

        if (i < 0 || i > size) {
            return null;
        }
        MyLinkedList<V> current = this;
            for (int x = 0; x <= i; x++) {
                current = current.next;
            }

        return current.value;
    }

    /**
     * returns the number of objects in the queue
     *
     * @return The amount of objects int he queue.
     */
    public int size() {
        return size;
    }


    @Override
    public Iterator<V> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<V> {

        private int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public V next() {
            return get(index++);
        }
    }

}
