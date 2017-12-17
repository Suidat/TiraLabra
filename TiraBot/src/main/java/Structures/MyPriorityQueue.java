package Structures;

import Algorithms.Astar.*;

/**
 * A class that implements a minimum heap for Holders
 * Only accepts Holder objects, and ordering is based on fscore.
 *
 * @see Algorithms.Astar.Holder
 */
public class MyPriorityQueue {
    public Holder[] heap;
    private int size;
    private int capacity = 20;

    /**
     * Creates a new minimum-heap, which accepts only Holder objects.
     */
    public MyPriorityQueue() {
        this.heap = new Holder[capacity];
        this.size = 0;
    }

    /**
     * Tells, whether the heap is empty.
     *
     * @return <code>true</code> if the heap is empty;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Gives the current amount of objects in the heap.
     *
     * @return An integer, depicting the current number of elements in the heap. Always a positive integer.
     */
    public int size() {
        return size;
    }

    /**
     * Adds a Holder object to the heap, and places it in the correct spot.
     *
     * @param element The new element, that will be added to the heap.
     */
    public void add(Holder element) {
        size = size + 1;

        if (size == capacity) {
            capacity *= 2;
            Holder[] newHeap = new Holder[capacity];
            for (int i = 0; i < size; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }

        int i = size;
        while (i > 1 && heap[parent(i)].compareTo(element) > 0) {
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = element;
    }

    /**
     * Returns the top of the minimum heap.
     *
     * @return The Holder with the minimum fscore. Otherwise returns null.
     */
    public Holder poll() {
        if (isEmpty())
            return null;

        Holder toReturn = this.heap[1];
        this.heap[1] = this.heap[size];
        this.heap[size] = null;

        this.size--;

        heapify(1);

        return toReturn;
    }

    /**
     * Private method to fix the heap back into a minimum heap after a poll operation.
     *
     * @param i the index of the element that needs to be checked, and possibly moved.
     */
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smaller;
        if (r < this.size) {
            if (heap[l].compareTo(heap[r]) < 0) {
                smaller = l;
            } else smaller = r;

            if (heap[i].compareTo(heap[smaller]) > 0) {
                swap(i, smaller);
                heapify(smaller);
            }
        } else if (l == size && heap[i].compareTo(heap[l]) > 0) {
            swap(i, l);
        }
    }

    /**
     * Private method to swap two elements in the heap
     *
     * @param i Index of first element
     * @param s Index of second element
     */
    private void swap(int i, int s) {
        Holder temp = heap[s];
        heap[s] = heap[i];
        heap[i] = temp;
    }

    /**
     * Returns the parent of the specified element
     *
     * @param i Index of element
     * @return Index of parent
     */
    private static int parent(int i) {
        return i / 2;
    }

    /**
     * Returns the left child of the element
     *
     * @param i Index of element
     * @return Index of child
     */
    private static int left(int i) {
        return i * 2;
    }

    /**
     * Returns the right child of the element
     *
     * @param i Index of element
     * @return Index of child
     */
    private static int right(int i) {
        return i * 2 + 1;
    }

    /**
     * Checks to see if the heap already contains the specified Holder object
     *
     * @param h The holder to be found
     * @return <code>true</code> if the object is in the heap;
     * <code>false</code> otherwise.
     */
    public boolean contains(Holder h) {
        if (!this.isEmpty()) {
            for (Holder holder : this.heap) {
                if (holder == null)
                    continue;
                if (holder.equals(h))
                    return true;
            }
        }
        return false;
    }

}
