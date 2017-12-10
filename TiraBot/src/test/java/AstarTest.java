import Algorithms.Astar;
import Structures.MyLinkedList;
import bot.AdvancedGameState;
import bot.Vertex;
import com.google.gson.Gson;
import dto.GameState;
import org.junit.*;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by frestmau on 12.11.2017.
 */
public class AstarTest {

    private static AdvancedGameState testState;
    private static GameState.Position start;
    private static GameState.Position end;

    public static void generateTestMap(String path){
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat(path);
        Gson mapper = new Gson();
        GameState state = null;

        try {
            state = mapper.fromJson(new FileReader(filePath), GameState.class);
        }catch (Exception e){
            System.out.println("Error with filereading"+e.toString());
            System.exit(123);
        }
        testState = new AdvancedGameState(state);
    }

    @Test
    public void WhenBeginOnTargetReturnEmpty(){
        generateTestMap("/src/test/resources/testGame");
        end = testState.getMe().getPos();
        start = testState.getMe().getPos();
        for(int i = 0; i<300;i++) {
            MyLinkedList<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(0, list.size());
        }
    }
    @Test
    public void emptyFieldPathFound(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position( 0,8);
        start = testState.getMe().getPos();

        long aikaAlussa = System.currentTimeMillis();
        for(int i = 0; i<300;i++) {
            MyLinkedList<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(5, list.size());
        }

        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon emptyFieldPathFound kului aikaa: " + (aikaLopussa - aikaAlussa)/300 + "ms.");
    }

    @Test
    public void longPathFound(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position( 17,10);
        start = testState.getMe().getPos();

        long aikaAlussa = System.currentTimeMillis();
        for(int i = 0; i<300;i++) {
            MyLinkedList<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(20, list.size());
        }

        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon longPathFound kului aikaa: " + (aikaLopussa - aikaAlussa)/300 + "ms.");
    }

    @Test
    public void noPathReturnsNull(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position(0,0);
        start = testState.getMe().getPos();
        MyLinkedList<Vertex> list = Astar.findPath(start, testState, end);
        assertEquals(null, list);
    }
    @Test
    public void longPathTesting(){
        generateTestMap("/src/test/resources/longTestGame");

        end = new GameState.Position(17, 17);
        start = testState.getMe().getPos();
        long aikaAlussa = System.currentTimeMillis();
        for(int i = 0; i<300;i++) {
            MyLinkedList<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(18 * 9 + 1, list.size());
        }
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon longPathTesting kului aikaa: " + (aikaLopussa - aikaAlussa)/300 + "ms.");
    }

    @Test
    public void canMoveIntoAMine(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position(3, 7);
        start = testState.getMe().getPos();
        MyLinkedList<Vertex> list = Astar.findPath(start, testState, end);
        assertNotEquals(null, list);
    }

}
