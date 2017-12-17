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
        System.out.println("HashSet\n");
        HashSet<Vertex> set1 = new HashSet<>();
        ArrayList<Vertex>list = new ArrayList<>();
        set = new MyHashSet(10000*2);


        for (int n = 0; n < 10; n++) {

            Vertex v = null;

            long myAddTime = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                v = new Vertex(new GameState.Position(i, i), list);
                set.add(v);
            }
            myAddTime = (System.nanoTime()-myAddTime)/1000;

            long javaAddTime = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                v = new Vertex(new GameState.Position(i, i), list);
                set1.add(v);

            }
            javaAddTime = (System.nanoTime()-javaAddTime)/1000;

            list.add(v);

            System.out.println(set.size());
            long javaContainsTime = System.nanoTime();
            set1.contains(v);
            javaContainsTime = System.nanoTime()-javaContainsTime;
            long myContainsTime = System.nanoTime();
            set.contains(v) ;
            myContainsTime = System.nanoTime() - myContainsTime;

            System.out.println("Java time to add = " + javaAddTime+"ns");
            System.out.println("My time to add = " + myAddTime+"ns");
            //System.out.println("faster = "+(myAddTime < javaAddTime));

            System.out.println("Java time to find = " + javaContainsTime+"ns");
            System.out.println("My time to find = " + myContainsTime+"ns");
            //System.out.println("faster = "+(myContainsTime<javaContainsTime));

            System.out.println();
        }
        System.out.println();
    }


}
