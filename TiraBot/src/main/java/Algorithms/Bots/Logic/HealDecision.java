package Algorithms.Bots.Logic;

import Algorithms.Astar;
import bot.AdvancedGameState;
import bot.Pub;
import dto.GameState;

public class HealDecision {
    private AdvancedGameState state;

    public HealDecision(AdvancedGameState state){
        this.state = state;
    }

    public Decision Decide() {
        int life = state.getMe()
                .getLife();
        Decision d;

        if(life <= 30){
            d = generateDecision(1);
        }else if (30<life&&life<70){
            d = generateDecision(5);
        }else{
            d = generateDecision(10);
        }
        return d;
    }

    private Decision generateDecision(int priority){
        int distance = Integer.MAX_VALUE;
        GameState.Position destination = null;

        for(GameState.Position p: state.getPubs().keySet()){
            int newDistance = Astar.estimateDistance(state.getMe().getPos(), p);
            if(distance>newDistance) {
                destination = p;
                distance = newDistance;
            }
        }

        return new Decision(destination, priority, "heal");

    }

}
