package Algorithms.Bots.Logic;

import Algorithms.Astar;
import bot.AdvancedGameState;
import dto.GameState;

public class KillDecision {
    private AdvancedGameState state;


    /**
     * Creates a kill decision object for the current game state
     * @param state The current game state.
     */
    public KillDecision(AdvancedGameState state) {
        this.state = state;
    }


    /**
     * Finds the most suitable target for this action.
     * @return A decision that contains priority, and target
     */
    public Decision Decide() {
        GameState.Hero target = scanTargets();
        if (target == null)
            return new Decision(null, 100, "target");
        return new Decision(target.getPos(), 2, "target");
    }

    private GameState.Hero scanTargets() {
        GameState.Hero target = null;
        for (GameState.Hero h : state.getHeroesByPosition().values()) {
            if (!h.getPos().equals(state.getMe().getPos())) {
                if (h.getLife() < state.getMe().getLife() - 20 && Astar.estimateDistance(state.getMe().getPos(), h.getPos()) < 6) {
                    if (target != null) {
                        if (Astar.estimateDistance(state.getMe().getPos(), h.getPos()) < Astar.estimateDistance(state.getMe().getPos(), target.getPos())) {
                            target = h;
                        }
                    } else target = h;
                }
            }
        }
        return target;
    }


    /**
     * Updates this object to the new game state
     * @param state The new game state
     */
    public void update(AdvancedGameState state){
        this.state = state;
    }

}
