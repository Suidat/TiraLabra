package Algorithms.Bots;

import Algorithms.Astar;
import Algorithms.Bots.Logic.Decision;
import Algorithms.Bots.Logic.DecisionMaker;
import Structures.MyLinkedList;
import bot.AdvancedGameState;
import bot.Vertex;
import dto.BotMove;
import dto.GameState;


public class BasicBot implements Bot {
    private DecisionMaker maker = new DecisionMaker(null);

    /**
     * Generates the move for this turn.
     * @param state The state of the current turn
     * @return The best move.
     */
    @Override
    public BotMove move(AdvancedGameState state) {
        maker.update(state);

        Decision d = maker.Decide();
        System.out.println(d.getDestination().toString());

        System.out.println(d);
        System.out.println("Me = " + state.getMe().getPos());
        return generateMove(Astar.findPath(
                state.getMe().getPos(),
                state, d.getDestination()),
                state.getMe().getPos());

    }

    private BotMove generateMove(MyLinkedList<Vertex> path, GameState.Position me) {
        if (path == null) {
            return BotMove.STAY;
        }

        Vertex target = path.get(path.size() - 2);
        System.out.println("Target = " + target.getPosition());
        return getDirection(me, target.getPosition());
    }


    private BotMove getDirection(GameState.Position me, GameState.Position target) {
        int x = me.getX() - target.getX();
        int y = me.getY() - target.getY();
        if (x < 0)
            return BotMove.SOUTH;
        if (x > 0)
            return BotMove.NORTH;
        if (y < 0)
            return BotMove.EAST;
        if (y > 0)
            return BotMove.WEST;

        return BotMove.STAY;

    }
}
