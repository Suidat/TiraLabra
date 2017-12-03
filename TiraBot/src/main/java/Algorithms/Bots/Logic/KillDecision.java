package Algorithms.Bots.Logic;

import Algorithms.Astar;
import bot.AdvancedGameState;
import dto.GameState;

public class KillDecision  {
    private AdvancedGameState state;

    public KillDecision(AdvancedGameState state){
        this.state = state;
    }


    public Decision Decide() {
        GameState.Hero target = scanTargets();
        if(target==null)
            return new Decision(null, 100, "target");
        return new Decision(target.getPos(), 2, "target");
    }

    private GameState.Hero scanTargets() {
        GameState.Hero target = null;
        for(GameState.Hero h : state.getHeroesByPosition().values()) {
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

}
