package Structures;

import java.util.Iterator;

public class MyLinkedList<V> implements Iterable<V>{

    V value;
    MyLinkedList<V> next;
    int size;
    MyLinkedList<V> end;

    public MyLinkedList (){
        value = null;
        next = null;
        size = 0;
        end = this;
    }

    private MyLinkedList (V value){
        this.value = value;
        next = null;
        size = 1;
        end = this;
    }

    public void add(V v){
        this.size = this.size+1;

        this.end.next = new MyLinkedList<>(v);
        this.end = end.next;

    }

    public V get(int i){

        if(i<0||i>size){
            return null;
        }

        MyLinkedList<V> current = this;

        for(int x = 0; x<=i; x++){
            current = current.next;
        }
        return current.value;
    }

    public int size(){
        return size;
    }


    @Override
    public Iterator<V> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<V>{

        private int index;

        @Override
        public boolean hasNext() {
            return index<size();
        }

        @Override
        public V next() {
            return get(index++);
        }
    }

}
