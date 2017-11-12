import Algoritmi.Astar;
import bot.Vertex;
import dto.GameState;
import org.junit.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by frestmau on 12.11.2017.
 */
public class AstarTest {

    private static Map<GameState.Position, Vertex> testMap;
    private static GameState.Position start;
    private static GameState.Position end;

    @BeforeClass
    public static void generateEasyTestMap(){
        testMap = new HashMap<>();
        List<GameState.Position> positions = new LinkedList<>();
        for(int x = 0; x<2; x++){
            for(int y = 0; y<2; y++){
                GameState.Position p = new GameState.Position(x,y);
                Vertex v = new Vertex(p, new LinkedList<Vertex>());
                positions.add(p);
                if(p.getY()==0 && p.getX()==0)
                    start = p;
                else if(p.getY()==1 && p.getX() == 1)
                    end = p;
                testMap.put(p,v);
            }
        }
        testMap.get(positions.get(0)).getAdjacentVertices().add(testMap.get(positions.get(1)));
        testMap.get(positions.get(0)).getAdjacentVertices().add(testMap.get(positions.get(2)));
        testMap.get(positions.get(1)).getAdjacentVertices().add(testMap.get(positions.get(0)));
        testMap.get(positions.get(1)).getAdjacentVertices().add(testMap.get(positions.get(3)));
        testMap.get(positions.get(2)).getAdjacentVertices().add(testMap.get(positions.get(0)));
        testMap.get(positions.get(2)).getAdjacentVertices().add(testMap.get(positions.get(3)));
        testMap.get(positions.get(3)).getAdjacentVertices().add(testMap.get(positions.get(1)));
        testMap.get(positions.get(3)).getAdjacentVertices().add(testMap.get(positions.get(2)));

    }



    @Test
    public void WhenBeginOnTargetReturnEmpty(){
        GameState.Position pos = new GameState.Position(1,1);

        assertEquals(new LinkedList<Vertex>(),Astar.findPath(pos, new HashMap<>(), pos));
    }
    //Koska Astar ei toimi aivan oikein, ei tämä testi ole luotettava.
    @Test
    public void emptyFieldPathFound(){
        List<Vertex> list = Astar.findPath(start, testMap, end);
        System.out.println(list.get(0).getPosition().getX()+" "+list.get(0).getPosition().getY());
        assertEquals(1, list.size());
    }
}
