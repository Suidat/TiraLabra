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

            long myAddTime = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                Holder h = new Holder(i, v);
                queue.add(h);
            }
            myAddTime = (System.nanoTime()-myAddTime)/1000;

            long javaAddTime = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                Holder h = new Holder(i, v);
                queue1.add(h);

            }
            javaAddTime = (System.nanoTime()-javaAddTime)/1000;

            System.out.println(queue.size());

            long javaPollTime = System.nanoTime();
            queue1.poll();
            javaPollTime = (System.nanoTime() - javaPollTime);
            long myPollTime = System.nanoTime();
            queue.poll();
            myPollTime = (System.nanoTime() - myPollTime);

            System.out.println("Java time to add = " + javaAddTime+"ns");
            System.out.println("My time to add = " + myAddTime+"ns");
            System.out.println("faster = "+(myAddTime < javaAddTime));


            System.out.println("Java time to find = " + javaPollTime+"ns");
            System.out.println("My time to find = " + myPollTime+"ns");
            System.out.println("faster = "+(myPollTime < javaPollTime));

            System.out.println();

            queue1.add(new Holder(0, v));
            queue.add(new Holder(0, v));
        }
        System.out.println();

    }
}
