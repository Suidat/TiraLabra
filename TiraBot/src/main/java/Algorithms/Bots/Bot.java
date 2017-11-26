package Algorithms.Bots;

import bot.AdvancedGameState;
import dto.BotMove;
import dto.GameState;

public interface Bot {
    BotMove move(AdvancedGameState state);
    void setUp();
    void shutDown();
}
