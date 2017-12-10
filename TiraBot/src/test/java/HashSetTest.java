import Structures.MyHashSet;
import bot.Vertex;
import dto.GameState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class HashSetTest {
    MyHashSet set;

    @Before
    public void before(){
        set = new MyHashSet();
    }
    @Test
    public void addingWorks(){
        set.add(new Vertex());
        assertEquals(1, set.size());
    }

    @Test
    public void containsReturnsCorrect(){
        Vertex v = new Vertex();
        ArrayList<Vertex> list = new ArrayList<>();
        list.add(v);
        for(int i = 0; i<10; i++){
            v = new Vertex(new GameState.Position(i,i), new ArrayList<>());
            list.add(v);
            set.add(v);
        }
        assertTrue(set.contains(v));
    }

    @Test
    public void speedTest() {
        System.out.println("HashSet");
        HashSet<Vertex> set1 = new HashSet<>();
        ArrayList<Vertex>list = new ArrayList<>();

        for (int n = 0; n < 10; n++) {
            Vertex v = null;
            for (int i = 0; i < 1000; i++) {
                v = new Vertex(new GameState.Position(i, i), list);
                set.add(v);
                set1.add(v);
                list.add(v);
            }

            System.out.println(set.size());
            long startJava = System.nanoTime();
            System.out.println(set1.contains(v));
            long endJava = System.nanoTime();
            long startMe = System.nanoTime();
            System.out.println(set.contains(v));
            long endMe = System.nanoTime();


            System.out.println("Java time to find = " + (endJava - startJava));
            System.out.println("My time to find = " + (endMe - startMe));
            System.out.println("faster = "+((endMe - startMe) < (endJava - startJava)));

            System.out.println();
        }
        System.out.println();
    }


}
