import Algorithms.Astar;
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
        GameState state;

        try {
            state = mapper.fromJson(new FileReader(filePath), GameState.class);

        }catch (Exception e){
            state = null;
        }
        testState = new AdvancedGameState(state);
    }



    @Test
    public void WhenBeginOnTargetReturnEmpty(){
        generateTestMap("/src/test/resources/testGame");
        end = testState.getMe().getPos();
        start = testState.getMe().getPos();
        for(int i = 0; i<10;i++) {
            List<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(0, list.size());
        }
    }
    @Test
    public void emptyFieldPathFound(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position( 0,8);
        start = testState.getMe().getPos();

        long aikaAlussa = System.currentTimeMillis();
        for(int i = 0; i<10;i++) {
            List<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(5, list.size());
        }

        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon emptyFieldPathFound kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
    }

    @Test
    public void longPathFound(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position( 17,10);
        start = testState.getMe().getPos();

        long aikaAlussa = System.currentTimeMillis();
        for(int i = 0; i<10;i++) {
            List<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(22, list.size());
        }

        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon longPathFound kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
    }

    @Test
    public void noPathReturnsNull(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position(0,0);
        start = testState.getMe().getPos();
        List<Vertex> list = Astar.findPath(start, testState, end);
        assertEquals(null, list);
    }
    @Test
    public void longPathTesting(){
        generateTestMap("/src/test/resources/longTestGame");

        end = new GameState.Position(17, 17);
        start = testState.getMe().getPos();
        long aikaAlussa = System.currentTimeMillis();
        for(int i = 0; i<10;i++) {
            List<Vertex> list = Astar.findPath(start, testState, end);
            assertEquals(18 * 9 + 1, list.size());
        }
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon longPathTesting kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
    }

    @Test
    public void canMoveIntoAMine(){
        generateTestMap("/src/test/resources/testGame");
        end = new GameState.Position(3, 7);
        start = testState.getMe().getPos();
        List<Vertex> list = Astar.findPath(start, testState, end);
        System.out.println(list.size());
        for(Vertex v : list){
            System.out.println(v.getPosition().toString());
        }
        assertNotEquals(null, list);




    }

}
