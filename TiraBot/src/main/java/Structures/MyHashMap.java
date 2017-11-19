package Structures;

/**
 * Created by frestmau on 19.11.2017.
 */
public class MyHashMap<K, V> {

    private Entry<K,V>[] table; 
    private int capacity = 6;
    private int size;
    
    @SuppressWarnings("unchecked")
    public MyHashMap(){
        table = new Entry[capacity];
    }

    private int hash(K key){//Decides the entry's place in the table.
        return Math.abs(key.hashCode())%capacity;
    }
    
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
                    prev.next=newEntry;
                    return;
                }
            }
            //Move forward a spot
            prev=current;
            current=current.next;
        }
        size++;
        //we have reached the end so just place newEntry here and nothing else is needed.
        prev.next=newEntry;
    }

    public V get(K key){
        int hash=hash(key);

        if(table[hash]==null)return null;

        Entry<K,V> temp = table[hash];

        while(temp!=null){
            if(temp.key.equals(key)) //we have found the correct entry, so return value
                return temp.value;
            temp = temp.next;// Not this one, so check next one.
        }

        return null;//the correct one was not found.
    }

    public int getSize(){
        return size;
    }

    public boolean containsKey(K key){ //this is basically the get method, but it returns boolean.
        int hash = hash(key);

        if(table[hash]==null)return false;

        Entry<K,V> temp = table[hash];

        while(temp!=null){
            if(temp.key.equals(key))
                return true;
            temp = temp.next;
        }

        return false;
    }

    static class Entry<K, V> {//A class for Map to use, with key value mappings.
        K key;
        V value;
        Entry<K, V> next;
        public Entry(K key, V value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
