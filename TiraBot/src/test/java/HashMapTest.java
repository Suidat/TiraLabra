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
    public void before(){
        map = new MyHashMap<>();
    }

    @Test
    public void putWorks(){
        map.put(1,1);
        assertEquals(1, map.getSize());
    }

    @Test
    public void putReplacesOld(){
        map.put(1,1);
        map.put(1,2);
        assertEquals(2, (int) map.get(1));
    }

    @Test
    public void putCanPutMultiples(){
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        assertEquals(3, map.getSize());
        assertEquals(3, (int) map.get(3));
    }

    @Test
    public void sizeIncrementsCorrectly(){
        map.put(1,1);
        map.put(2,2);
        map.put(1,3);
        assertEquals(2, map.getSize());
    }
    @Test
    public void nothingToGetNothingReturned(){
        map.put(1,1);
        assertEquals(null, map.get(2));
    }

}
