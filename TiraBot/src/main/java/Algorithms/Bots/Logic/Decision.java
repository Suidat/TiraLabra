package Algorithms.Bots.Logic;

import bot.Vertex;
import dto.GameState;

public class Decision {
    private int priority;
    private GameState.Position destination;
    public String type;

    public Decision(GameState.Position destination, int priority, String type){
        this.destination = destination;
        this.priority = priority;
        this.type = type;
    }

    public GameState.Position getDestination() {
        return destination;
    }

    public String getType(){
        return type;
    }
    
    public int getPriority(){
        return priority;
    }

    @Override
    public String toString() {
        return "Decision{" +
                "priority=" + priority +
                ", destination=" + destination +
                ", type='" + type + '\'' +
                '}';
    }
}

