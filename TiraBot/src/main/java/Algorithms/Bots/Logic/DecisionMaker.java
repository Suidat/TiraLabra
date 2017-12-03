package Algorithms.Bots.Logic;

import bot.AdvancedGameState;

public class DecisionMaker {
    private AdvancedGameState state;
    private HealDecision healDecision;
    private KillDecision killDecision;
    private CaptureDecision captureDecision;

    public DecisionMaker(AdvancedGameState state){
        this.state = state;
        this.healDecision = new HealDecision(state);
        this.killDecision = new KillDecision(state);
        this.captureDecision = new CaptureDecision(state);
    }

    public Decision Decide(){
        Decision heal = healDecision.Decide();
        Decision kill = killDecision.Decide();
        Decision capture = captureDecision.Decide();

        if(kill.getPriority()<heal.getPriority()){
            if(kill.getPriority()<capture.getPriority()){
                return kill;
            } else return capture;
        }else if(heal.getPriority()<capture.getPriority()){
            return heal;
        }else return capture;
    }

    public void update(AdvancedGameState newState){
        this.state = newState;
        this.healDecision = new HealDecision(state);
        this.killDecision = new KillDecision(state);
        this.captureDecision = new CaptureDecision(state);
    }
}
