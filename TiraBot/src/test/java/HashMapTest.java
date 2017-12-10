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
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int n = 0; n < 10; n++) {
            for (int i = n * 1000; i < 1000 * (n + 1); i++) {
                map.put(i, n);
                map1.put(i, n);

            }
            System.out.println(map.size());
            long startJava = System.nanoTime();
            System.out.println(map1.get(977 * n));
            long endJava = System.nanoTime();
            long startMe = System.nanoTime();
            System.out.println(map.get(977 * n));
            long endMe = System.nanoTime();


            System.out.println("Java time to find = " + (endJava - startJava));
            System.out.println("My time to find = " + (endMe - startMe));
            System.out.println("faster = "+((endMe - startMe) < (endJava - startJava)));

            System.out.println();
        }
        System.out.println();
    }

}
