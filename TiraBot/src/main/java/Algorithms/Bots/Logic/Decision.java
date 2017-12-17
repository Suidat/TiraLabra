package Algorithms.Bots.Logic;

import dto.GameState;

public class Decision {
    private int priority;
    private GameState.Position destination;
    public String type;

    /**
     * An object that contains the destination and priority of a decision.
     * @param destination The destination for this action.
     * @param priority How important the action is. Lower numbers are of higher importance.
     * @param type What type of action this is.
     */
    public Decision(GameState.Position destination, int priority, String type) {
        this.destination = destination;
        this.priority = priority;
        this.type = type;
    }

    public GameState.Position getDestination() {
        return destination;
    }

    /**
     * returns the type of move.
     * @return
     */
    public String getType() {
        return type;
    }

    public int getPriority() {
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

