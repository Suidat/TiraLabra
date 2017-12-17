package Algorithms.Bots.Logic;

import bot.AdvancedGameState;

public class DecisionMaker {
    private AdvancedGameState state;
    private HealDecision healDecision;
    private KillDecision killDecision;
    private CaptureDecision captureDecision;

    /**
     * Creates a new decision maker, for the current game state.
     * @param state Current game state.
     */
    public DecisionMaker(AdvancedGameState state) {
        this.state = state;
        this.healDecision = new HealDecision(state);
        this.killDecision = new KillDecision(state);
        this.captureDecision = new CaptureDecision(state);
    }

    /**
     * Takes all the decision makers in this object and finds the most urgent action and returns it.
     * @return The action with the highest priority.
     */
    public Decision Decide() {
        Decision heal = healDecision.Decide();
        Decision kill = killDecision.Decide();
        Decision capture = captureDecision.Decide();

        if (kill.getPriority() < heal.getPriority()) {
            if (kill.getPriority() < capture.getPriority()) {
                return kill;
            } else return capture;
        } else if (heal.getPriority() < capture.getPriority()) {
            return heal;
        } else return capture;
    }


    /**
     * Updates the game state of all decision objects in the
     * @param newState Current game state.
     */
    public void update(AdvancedGameState newState) {
        this.state = newState;
        this.healDecision.update(state);
        this.killDecision.update(state);
        this.captureDecision.update(state);
    }
}
