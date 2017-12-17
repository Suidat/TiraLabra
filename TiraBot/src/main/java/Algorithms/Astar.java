package Algorithms;

import Structures.MyHashMap;
import Structures.MyHashSet;
import Structures.MyLinkedList;
import Structures.MyPriorityQueue;
import bot.AdvancedGameState;
import bot.Vertex;
import dto.GameState;

/**
 * This class is used for pathfinding and for distance estimation
 * All methods are static.
 */
public class Astar {

    /**
     * Takes two points on a map, and the current game state
     * and returns the fastest path from start to finnish, as a list of the vertexes.
     * Implements A* search algorithm.
     *
     * @param start       The starting position of the search.
     * @param gameState   The current state of the game. Requires Advanced version of game state.
     * @param destination The destination position:
     * @return A MyLinkedList object, that contains the fastest path between the start and destination positions.
     * @see MyLinkedList
     * @see AdvancedGameState
     */
    public static MyLinkedList<Vertex> findPath(GameState.Position start, AdvancedGameState gameState, GameState.Position destination) {

        //Check to see if starting location is the destination
        if (start.equals(destination))
            return new MyLinkedList<>();
        int cap = gameState.getBoardGraph().size()*2;
        MyHashSet closed = new MyHashSet();
        MyPriorityQueue open = new MyPriorityQueue();
        MyHashMap<Vertex, Vertex> cameFrom = new MyHashMap<>(cap);
        MyHashMap<GameState.Position, Integer> gscore = new MyHashMap<>(cap);
        MyHashMap<GameState.Position, Integer> fscore = new MyHashMap<>(cap);

        for (Vertex v : gameState.getBoardGraph().values()) {
            fscore.put(v.getPosition(), Integer.MAX_VALUE);
            gscore.put(v.getPosition(), Integer.MAX_VALUE);
        }//for

        fscore.put(start, Astar.estimateDistance(start, destination));
        gscore.put(start, 0);

        Holder temp = new Holder(fscore.get(start), gameState.getBoardGraph().get(start));
        open.add(temp);

        //A* loop.
        while (!open.isEmpty()) {

            Vertex current = open.poll().vertex;

            //We have reached the destination and will create and return the path.


            if (current.equals(gameState.getBoardGraph().get(destination)))
                return reconstructPath(cameFrom, gameState.getBoardGraph().get(destination));

            closed.add(current);

            //if the location contains a hero other than the player, it is treated as impassable.
            if (gameState.getHeroesByPosition().containsKey(current.getPosition())
                    && !current.getPosition().equals(gameState.getMe().getPos()))
                continue;

            for (Vertex neighbour : current.getAdjacentVertices()) {


                //if the vertex has been handled skip it.
                if (closed.contains(neighbour))
                    continue;

                int tmpscore = gscore.get(current.getPosition()) + 1;

                if (tmpscore >= gscore.get(neighbour.getPosition()))
                    continue;

                cameFrom.put(neighbour, current);
                gscore.put(neighbour.getPosition(), tmpscore);
                fscore.put(neighbour.getPosition(), tmpscore + estimateDistance(neighbour.getPosition(), destination));

                temp = new Holder(fscore.get(neighbour.getPosition()), neighbour);
                if (!open.contains(temp))
                    open.add(temp);

            }//forEach

        }//while

        return null;

    }//Astar()


    /**
     * Generates the path between the two points on the map.
     *
     * @param cameFrom The MyHashMap gained from {@link #findPath(dto.GameState.Position, AdvancedGameState, dto.GameState.Position)}
     * @param v        the final vertex.
     * @return
     */

    //Method provides a list that walks from the destination to the start. So index 0 is the destination
    private static MyLinkedList<Vertex> reconstructPath(MyHashMap<Vertex, Vertex> cameFrom, Vertex v) {
        MyLinkedList<Vertex> total_path = new MyLinkedList<>();
        total_path.add(v);
        while (cameFrom.containsKey(v)) {
            v = cameFrom.get(v);
            total_path.add(v);
        }//while
        return total_path;
    }//reconstructPath

    /**
     * Gives the Manhattan distance between two different positions.
     *
     * @param s First position
     * @param v Second position
     * @return The Manhattan distance between the two positions.
     */
    //Heuristic based on Manhattan-distance
    public static int estimateDistance(GameState.Position s, GameState.Position v) {
        int xd = Math.abs(s.getX() - v.getX());
        int yd = Math.abs(s.getY() - v.getY());
        return xd + yd;
    }//estimateDistance


    /**
     * Class for the use of MyPriortiyQueue.
     */
    //Class to make PriorityQueue comarison possible
    public static class Holder implements Comparable<Holder> {
        public int fscore;
        public Vertex vertex;

        public Holder(int f, Vertex v) {
            this.fscore = f;
            this.vertex = v;
        }//Constructor

        @Override
        public int hashCode() {
            return (int) Math.pow(fscore, (vertex.getPosition().getX() + vertex.getPosition().getY()));

        }

        public int compareTo(Holder holder) {
            return Integer.compare(this.fscore, holder.fscore);
        }//compareTo


    }//Holder

}//Class
