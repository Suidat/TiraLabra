package Algorithms;

import Algorithms.Bots.Bot;
import bot.AdvancedGameState;
import com.google.api.client.http.*;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;
import dto.ApiKey;
import dto.BotMove;
import dto.GameState;
import dto.Move;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Callable;

/**
 * Created by frestmau on 19.11.2017.
 */
public class BasicBotRunner implements Callable<GameState> {
    private static final HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
    private static final JsonFactory JSON_FACTORY = new GsonFactory();
    private static final HttpRequestFactory REQUEST_FACTORY =
            HTTP_TRANSPORT.createRequestFactory(request -> request.setParser(new JsonObjectParser(JSON_FACTORY)));
    private static final Logger logger = LogManager.getLogger(BasicBotRunner.class);

    private final ApiKey apiKey;
    private final GenericUrl gameUrl;
    private final Bot bot;

    public BasicBotRunner(ApiKey key, GenericUrl url, Bot bot){
        this.apiKey = key;
        this.gameUrl = url;
        this.bot = bot;
    }

    @Override
    public GameState call() throws Exception {
        HttpContent content ;
        HttpRequest request;
        HttpResponse response;
        GameState gameState = null;
        AdvancedGameState advancedGameState;


        try {
            // Initial request
            logger.info("Sending initial request...");
            content = new UrlEncodedContent(apiKey);
            request = REQUEST_FACTORY.buildPostRequest(gameUrl, content);
            request.setReadTimeout(0); // Wait forever to be assigned to a game
            response = request.execute();

            gameState = response.parseAs(GameState.class);
            logger.info("Game URL: {}", gameState.getViewUrl());

            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("/src/main/resources/game");
            try (FileWriter file = new FileWriter(filePath)) {
                file.write(gameState.toString());
                logger.info("Successfully Copied JSON Object to File...");
            }catch(Exception e){
                logger.info("Copying went wrong" + e.toString());
            }


            advancedGameState = new AdvancedGameState(gameState);
            while (!gameState.getGame().isFinished() && !gameState.getHero().isCrashed()) {
                logger.info("Taking turn " + gameState.getGame().getTurn());
                BotMove direction;


                try {
                    direction = bot.move(advancedGameState);
                } catch (Throwable t) {
                    logger.error("Error while making move", t);
                    direction = BotMove.STAY;
                }
                Move move = new Move(apiKey.getKey(), direction.toString());
                logger.info("Bot direction is "+direction);

                HttpContent turn = new UrlEncodedContent(move);
                HttpRequest turnRequest = REQUEST_FACTORY.buildPostRequest(new GenericUrl(gameState.getPlayUrl()), turn);
                HttpResponse turnResponse = turnRequest.execute();

                gameState = turnResponse.parseAs(GameState.class);
                advancedGameState = new AdvancedGameState(advancedGameState, gameState);

            }
        }catch(Exception e){
            logger.error("Something went wrong during the game", e);
        }

        logger.info("Game Over");
        return gameState;
    }

}
