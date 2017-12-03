package Algorithms.Bots.Logic;

import Algorithms.Astar;
import bot.AdvancedGameState;
import bot.Mine;
import dto.GameState;

public class CaptureDecision {
    private AdvancedGameState state;


    public CaptureDecision(AdvancedGameState state){
        this.state = state;
    }

    public Decision Decide() {
        GameState.Position target = null;
        int distance = 0;
        for(GameState.Position m : state.getMines().keySet()){
            if(state.getMines().get(m).getOwner()!=null) {
                if (state.getMines().get(m).getOwner().getId() == (state.getMe().getId()))
                    continue;
            }
            if(target == null) {
                target = m;
                distance = Astar.estimateDistance(state.getMe().getPos(), m);
            }
            else if(distance>Astar.estimateDistance(state.getMe().getPos(), m)){
                target = m;
                distance = Astar.estimateDistance(state.getMe().getPos(), m);
            }
        }
        return new Decision(target, 3, "capture");
    }
}
