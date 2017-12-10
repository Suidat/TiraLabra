package Structures;

import bot.Vertex;

public class MyHashSet {
    private Entry[] table;
    private int size;
    private int capacity = 47;

    public MyHashSet(){
        table = new Entry[capacity];
        size = 0;
    }

    private int hash(Vertex v){//Decides the entry's place in the table.
        return Math.abs(v.hashCode())%capacity;
    }


    public void add(Vertex v){
        if(v == null) return;
        int hash = hash(v);

        Entry newEntry = new Entry(v, null);
        size++;

        if(table[hash]==null){
            table[hash]=newEntry;
            return;
        }

        Entry current = table[hash];
        while(current.next!=null){
            current=current.next;
        }
        current.next=newEntry;
    }

    public boolean contains(Vertex v){
        int hash = hash(v);
        if(table[hash]==null)
            return false;
        Entry current = table[hash];

        if(current.vertex.equals(v))
            return true;

        while(current.next!=null){
            if(current.vertex.equals(v)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Vertex remove(Vertex v){
        int hash = hash(v);
        if(table[hash]==null)
            return null;
        Entry current = table[hash];
        Entry prev = null;
        while(current.next != null){
            if(v.equals(current.vertex)){
                if(prev != null){
                    prev.next = current.next;
                }
                return current.vertex;
            }
            prev = current;
            current = current.next;
        }
        return null;

    }


    public int size() {
        return size;
    }

    static class Entry {
        Vertex vertex;
        Entry next;

        public Entry(Vertex vertex, Entry next){
            this.vertex = vertex;
            this.next = next;
        }
    }
}
