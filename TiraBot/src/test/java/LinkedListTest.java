import Structures.MyLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class LinkedListTest {
    static MyLinkedList<Integer> list;

    @Before
    public void before(){
        list = new MyLinkedList<>();
    }

    @Test
    public void canAdd(){
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void canGet(){
        list.add(1);
        int result = list.get(0);
        assertEquals(1, result);
    }

    @Test
    public void multipleValuesWorkProperly(){
        for(int i = 0; i<10; i++){
            list.add(i);
        }
        assertEquals(3, (int) list.get(3));
        assertEquals(6, (int) list.get(6));
        assertEquals(9, (int) list.get(9));
    }

    @Test
    public void speedTest(){
        System.out.println("LinkedList");
        LinkedList<Integer> list1 = new LinkedList<>();
        for(int n = 0; n<10; n++){
            for(int i = 0; i<1000; i++){
                list.add(i);
                list1.add(i);
            }

            System.out.println(list.size());
            long startJava = System.nanoTime();
            System.out.println(list1.get(999*n));
            long endJava = System.nanoTime();
            long startMe = System.nanoTime();
            System.out.println(list.get(999*n));
            long endMe = System.nanoTime();

            System.out.println("Java time to find = " + (endJava - startJava));
            System.out.println("My time to find = " + (endMe - startMe));
            System.out.println("faster = "+((endMe - startMe) < (endJava - startJava)));
            System.out.println();
        }
        System.out.println();
    }

}
