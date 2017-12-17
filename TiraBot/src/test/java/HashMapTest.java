import Structures.MyHashMap;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by frestmau on 19.11.2017.
 */
public class HashMapTest {
    private static MyHashMap<Integer, Integer> map;

    @Before
    public void before() {
        map = new MyHashMap<>();
    }

    @Test
    public void putWorks() {
        map.put(1, 1);
        assertEquals(1, map.size());
    }

    @Test
    public void putReplacesOld() {
        map.put(1, 1);
        map.put(1, 2);
        assertEquals(2, (int) map.get(1));
    }

    @Test
    public void putCanPutMultiples() {
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        assertEquals(3, map.size());
        assertEquals(3, (int) map.get(3));
    }

    @Test
    public void sizeIncrementsCorrectly() {
        map.put(1, 1);
        map.put(2, 2);
        map.put(1, 3);
        assertEquals(2, map.size());
    }

    @Test
    public void nothingToGetNothingReturned() {
        map.put(1, 1);
        assertEquals(null, map.get(2));
    }


    @Test
    public void speedTest() {
        System.out.println("HashMap");
        map = new MyHashMap<>(10000*2);
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int n = 0; n < 10; n++) {
            long myAddTime = System.nanoTime();
            for (int i = n * 1000; i < 1000 * (n + 1); i++) {
                map.put(i, n);
            }
            myAddTime = (System.nanoTime() - myAddTime)/1000;

            long javaAddTime = System.nanoTime();
            for (int i = n * 1000; i < 1000 * (n + 1); i++) {
                map1.put(i, n);
            }
            javaAddTime = (System.nanoTime() - javaAddTime)/1000;

            System.out.println(map.size());
            long javaGetTime = System.nanoTime();
            map1.get(357 * n);
            javaGetTime = System.nanoTime() - javaGetTime;
            long myGetTime = System.nanoTime();
            map.get(357 * n);
            myGetTime = System.nanoTime()- myGetTime;

            System.out.println("Java time to add = " + javaAddTime+"ns");
            System.out.println("My time to add = " + myAddTime+"ns");
            //System.out.println("faster = "+(myAddTime < javaAddTime));

            System.out.println("Java time to find = " + javaGetTime+"ns");
            System.out.println("My time to find = " + myGetTime+"ns");
            //System.out.println("faster = "+(myGetTime < javaGetTime));


            System.out.println();
        }
        System.out.println();
    }

}
