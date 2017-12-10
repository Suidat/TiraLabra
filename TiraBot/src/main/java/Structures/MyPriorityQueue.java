package Structures;

import Algorithms.Astar.*;

public class MyPriorityQueue {
    public Holder[] heap;
    private int size;
    private int capacity = 10;

    public MyPriorityQueue(){
        this.heap = new Holder[capacity];
        this.size = 0;
    }


    public boolean isEmpty(){
        return this.size == 0;
    }

    public int size(){
        return size;
    }

    public void add(Holder element){
        size = size+1;

        if(size == capacity){
            capacity*=2;
            Holder[] newHeap = new Holder[capacity];
            for(int i = 0; i<size; i++){
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }

        int i = size;
        while(i>1 && heap[parent(i)].compareTo(element)>0){
            heap[i] = heap[parent(i)];
            i = parent(i);
        }
        heap[i] = element;
    }

    public Holder poll(){
        Holder toReturn = this.heap[1];
        this.heap[1] = this.heap[size];
        this.heap[size]=null;

        this.size--;

        heapify(1);

        return toReturn;
    }

    private void heapify(int i){
        int l = left(i);
        int r = right(i);
        int smaller;
        if(r<this.size){
            if(heap[l].compareTo(heap[r])<0){
                smaller = l;
            }else smaller = r;

            if(heap[i].compareTo(heap[smaller])>0){
                swap(i, smaller);
                heapify(smaller);
            }
        }else if(l==size&&heap[i].compareTo(heap[l])>0){
            swap(i, l);
        }
    }

    private void swap(int i, int s){
        Holder temp = heap[s];
        heap[s] = heap[i];
        heap[i] = temp;
    }

    private static int parent(int i){
        return i/2;
    }

    private static int left(int i){
        return i*2;
    }

    private static int right(int i){
        return i*2+1;
    }


    public boolean contains(Holder h){
        if(!this.isEmpty()) {
            for (Holder holder : this.heap) {
                if(holder == null)
                    continue;
                if (holder.equals(h))
                    return true;
            }
        }
        return false;
    }

}
