package Algorithms.Bots;

import bot.AdvancedGameState;
import dto.BotMove;

public interface Bot {
    BotMove move(AdvancedGameState state);
}
