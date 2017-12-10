import Algorithms.Astar.Holder;
import Structures.MyPriorityQueue;
import bot.Vertex;
import dto.GameState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {
    private MyPriorityQueue queue;

    @Before
    public void before(){
        queue = new MyPriorityQueue();
    }

    @Test
    public void canAddElement(){
        Holder h = new Holder(1, new Vertex());
        queue.add(h);
        assertEquals(1, queue.size());
    }

    @Test
    public void heapifyWorksCorrectly(){
        Holder h = new Holder(10, new Vertex());
        queue.add(h);
        Holder h1 = new Holder(1, new Vertex());
        queue.add(h1);

        queue.add(h1);
        h = new Holder(2, new Vertex());
        queue.add(h);
        h = new Holder(6, new Vertex());
        queue.add(h);

        assertEquals(h1, queue.poll());
        assertEquals(h1, queue.poll());
    }

    @Test
    public void speedTest() {
        System.out.println("PriorityQueue");
        PriorityQueue<Holder> queue1 = new PriorityQueue<>();
        Vertex v = new Vertex();

        for (int n = 0; n < 10; n++) {
            queue1.add(new Holder(0, v));
            queue.add(new Holder(0, v));
            for (int i = 1000*(n+1); i > 1000*n; i--) {
                Holder h = new Holder(i, v);
                queue1.add(h);
                queue.add(h);
            }
            System.out.println(queue.size());

            long startJava = System.nanoTime();
            System.out.println(queue1.poll().fscore);
            long endJava = System.nanoTime();
            long startMe = System.nanoTime();
            System.out.println(queue.poll().fscore);
            long endMe = System.nanoTime();

            System.out.println("Java time to find = " + (endJava - startJava));
            System.out.println("My time to find = " + (endMe - startMe));
            System.out.println("faster = "+((endMe - startMe) < (endJava - startJava)));

            System.out.println();
        }
        System.out.println();

    }
}
