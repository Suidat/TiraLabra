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

            long myAddTime = System.nanoTime();
            for (int i = 1; i <= 1000; i++) {
                list.add(i*(n+1));
            }
            myAddTime = (System.nanoTime()-myAddTime)/1000;

            long javaAddTime = System.nanoTime();
            for (int i = 1; i <= 1000; i++) {
                list1.add(i*(n+1));

            }
            javaAddTime = (System.nanoTime()-javaAddTime)/1000;

            System.out.println(list.size());
            long javaGetTime = System.nanoTime();
            System.out.println(list1.get(500*(n+1)));
            javaGetTime = System.nanoTime() - javaGetTime;
            long myGetTime = System.nanoTime();
            System.out.println(list.get(500*(n+1)));
            myGetTime = System.nanoTime() - myGetTime;


            System.out.println("Java time to add = " + javaAddTime+"ns");
            System.out.println("My time to add = " + myAddTime+"ns");
            System.out.println("faster = "+(myAddTime < javaAddTime));

            System.out.println("Java time to find = " + javaGetTime+"ns");
            System.out.println("My time to find = " + myGetTime+"ns");
            System.out.println("faster = "+(myGetTime<javaGetTime));
            System.out.println();
        }
        System.out.println();
    }

}
