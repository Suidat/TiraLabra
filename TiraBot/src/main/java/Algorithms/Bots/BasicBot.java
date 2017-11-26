package Algorithms.Bots;

import bot.AdvancedGameState;
import dto.BotMove;
import dto.GameState;

public class BasicBot implements Bot {


    @Override
    public BotMove move(AdvancedGameState state) {


        return BotMove.STAY;
    }

    @Override
    public void setUp() {

    }

    @Override
    public void shutDown() {

    }
}
