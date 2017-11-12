package Algoritmi;

import bot.Vertex;
import dto.GameState;

import java.util.*;

/**
 * Created by frestmau on 12.11.2017.
 */
public class Astar {
    //Perinteinen A* algoritmi.
    public static List<Vertex> findPath(GameState.Position start, Map<GameState.Position,
                                        Vertex> boardGraph, GameState.Position destination){
        //Tarkistetaan, että ei olla kohteessa.
        if(start.equals(destination))
            return new LinkedList<Vertex>();


        Set<Vertex> closed = new HashSet<>();
        Queue<Holder> open = new PriorityQueue<>();
        Map<Vertex, Vertex> cameFrom = new HashMap<>();
        Map<GameState.Position, Integer> gscore = new HashMap<>();
        Map<GameState.Position, Integer> fscore = new HashMap<>();


        for (Vertex v : boardGraph.values()) {
            fscore.put(v.getPosition(), Integer.MAX_VALUE);
            gscore.put(v.getPosition(), 0);
        }

        Holder temp = new Holder(fscore.get(start), boardGraph.get(start));
        open.add(temp);
        fscore.put(start, Astar.estimateDistance(start, destination));
        gscore.put(start, 0);

        //A* loop, jossa cameFrom ei jostain syystä päivity oikein.
        while(!open.isEmpty()){
            Vertex current = open.poll().vertex;
            if(current.getPosition().equals(destination))
                return reconstructPath(cameFrom, boardGraph.get(destination));
            closed.add(current);
            for (Vertex neighbour:current.getAdjacentVertices()) {
                temp = new Holder(fscore.get(neighbour.getPosition()), neighbour);
                if(closed.contains(neighbour))
                    continue;
                if(!open.contains(temp))
                    open.add(temp);

                int tmpscore = gscore.get(current.getPosition())+1;
                if (tmpscore>=gscore.get(neighbour.getPosition()))
                    continue;
                cameFrom.put(neighbour,current);
                gscore.put(neighbour.getPosition(),tmpscore);
                fscore.put(neighbour.getPosition(), tmpscore+estimateDistance(neighbour.getPosition(), destination));
            }
        }
        return null;
    }

    //Metodi tuottaa Listan, joka kulkee polun lopusta alkuun. Eli indeksi 0 on kohde.
    //Ei toimi toivotulla tavalla, koska cameFrom kartta ei jostain syystä päivity oikein.
    private static List<Vertex> reconstructPath(Map<Vertex, Vertex> cameFrom, Vertex v){
        List<Vertex> total_path = new LinkedList<>();
        total_path.add(v);
        while (cameFrom.containsKey(v)){
            v = cameFrom.get(v);
            total_path.add(v);
        }
        return total_path;
    }

    //Heuristiikka perustuu tällä hetkellä Manhattan etäisyyteen.
    private static int estimateDistance(GameState.Position s, GameState.Position v){
        int xd = Math.abs(s.getX()-v.getX());
        int yd = Math.abs(s.getY()-v.getY());
        return xd+yd;
    }

    //Luokka jonka avulla voidaan verrata PriorityQueuen sisällä olevien solmujen arvoja.
    static class Holder implements Comparable<Holder>{
        int fscore;
        Vertex vertex;
        public Holder(int f, Vertex v) {
            this.fscore = f;
            this.vertex = v;
        }

        public int compareTo(Holder holder) {
            if(this.fscore<holder.fscore)
                return 1;
            if(this.fscore>holder.fscore)
                return -1;
            else return 0;
        }
    }
}
