package Algorithms;

import Structures.MyHashMap;
import bot.AdvancedGameState;
import bot.Vertex;
import dto.GameState;

import java.util.*;

/**
 * Created by frestmau on 12.11.2017.
 */
public class Astar {


    public static List<Vertex> findPath(GameState.Position start, AdvancedGameState gameState, GameState.Position destination){

        //Check to see if starting location is the destination
        if(start.equals(destination))
            return new LinkedList<Vertex>();
        
        Set<Vertex> closed = new HashSet<>();
        Queue<Holder> open = new PriorityQueue<>();
        MyHashMap<Vertex, Vertex> cameFrom = new MyHashMap<>();
        MyHashMap<GameState.Position, Integer> gscore = new MyHashMap<>();
        MyHashMap<GameState.Position, Integer> fscore = new MyHashMap<>();
        
        for (Vertex v : gameState.getBoardGraph().values()) {
            fscore.put(v.getPosition(), Integer.MAX_VALUE);
            gscore.put(v.getPosition(), Integer.MAX_VALUE);
        }//for

        fscore.put(start, Astar.estimateDistance(start, destination));
        gscore.put(start, 0);

        Holder temp = new Holder(fscore.get(start), gameState.getBoardGraph().get(start));
        open.add(temp);

        //A* loop.
        while(!open.isEmpty()){

            Vertex current = open.poll().vertex;

            //We have reached the destination and will create and return the path.


            if(current.equals(gameState.getBoardGraph().get(destination)))
                return reconstructPath(cameFrom, gameState.getBoardGraph().get(destination));

            closed.add(current);

            //if the location contains a hero other than the player, it is treated as impassable.
            if(gameState.getHeroesByPosition().containsKey(current.getPosition())
                    && !current.getPosition().equals(gameState.getMe().getPos()))
                continue;
            
            for (Vertex neighbour : current.getAdjacentVertices()) {

                temp = new Holder(fscore.get(neighbour.getPosition()), neighbour);
                //if the vertex has been handled skip it.
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
            }//forEach

        }//while

        return null;

    }//Astar()


    //Method provides a list that walks from the destination to the start. So index 0 is the destination
    private static List<Vertex> reconstructPath(MyHashMap<Vertex, Vertex> cameFrom, Vertex v){
        List<Vertex> total_path = new LinkedList<>();
        total_path.add(v);
        while (cameFrom.containsKey(v)){
            v = cameFrom.get(v);
            total_path.add(v);
        }//while
        return total_path;
    }//reconstructPath


    //Heuristic based on Manhattan-distance
    public static int estimateDistance(GameState.Position s, GameState.Position v){
        int xd = Math.abs(s.getX()-v.getX());
        int yd = Math.abs(s.getY()-v.getY());
        return xd+yd;
    }//estimateDistance


    //Class to make PriorityQueue comarison possible
    static class Holder implements Comparable<Holder>{
        int fscore;
        Vertex vertex;

        public Holder(int f, Vertex v) {
            this.fscore = f;
            this.vertex = v;
        }//Constructor

        public int compareTo(Holder holder) {
            return Integer.compare(holder.fscore, this.fscore);
        }//compareTo

    }//Holder

}//Class
