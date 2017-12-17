package Algorithms.Bots.Logic;

import Algorithms.Astar;
import bot.AdvancedGameState;
import dto.GameState;

public class HealDecision {
    private AdvancedGameState state;

    /**
     * Creates a heal decision object for the current game state
     * @param state The current game state.
     */
    public HealDecision(AdvancedGameState state) {
        this.state = state;
    }


    /**
     * Finds the most suitable target for this action.
     * @return A decision that contains priority, and target
     */

    public Decision Decide() {
        int life = state.getMe()
                .getLife();
        Decision d;

        if (life <= 30) {
            d = generateDecision(1);
        } else if (30 < life && life < 70) {
            d = generateDecision(5);
        } else {
            d = generateDecision(10);
        }
        return d;
    }

    private Decision generateDecision(int priority) {
        int distance = Integer.MAX_VALUE;
        GameState.Position destination = null;

        for (GameState.Position p : state.getPubs().keySet()) {
            int newDistance = Astar.estimateDistance(state.getMe().getPos(), p);
            if (distance > newDistance) {
                destination = p;
                distance = newDistance;
            }
        }
        return new Decision(destination, priority, "heal");
    }

    /**
     * Updates this object to the new game state
     * @param state The new game state
     */
    public void update(AdvancedGameState state){
        this.state = state;
    }

}
