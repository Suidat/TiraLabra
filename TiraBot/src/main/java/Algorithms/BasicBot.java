package Algorithms;

import Structures.MyHashMap;
import bot.AdvancedGameState;
import bot.Mine;
import bot.Vertex;
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

import java.util.concurrent.Callable;

/**
 * Created by frestmau on 19.11.2017.
 */
public class BasicBot implements Callable<GameState> {
    private static final HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();
    private static final JsonFactory JSON_FACTORY = new GsonFactory();
    private static final HttpRequestFactory REQUEST_FACTORY =
            HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) {
                    request.setParser(new JsonObjectParser(JSON_FACTORY));
                }
            });
    private static final Logger logger = LogManager.getLogger(BasicBot.class);

    /*private final ApiKey apiKey;
    private final GenericUrl gameUrl;
    private final BasicBot bot;*/

    public BasicBot(ApiKey key, GenericUrl url){

    }

    @Override
    public GameState call() throws Exception {
        HttpContent content;
        HttpRequest request;
        HttpResponse response;
        GameState gameState = null;
        AdvancedGameState advancedGameState;
        //Bot Setup here


        //Game Loop
        //Not yet implemented
        /*while (!gameState.getGame().isFinished()&&!gameState.getHero().isCrashed()){
            BotMove move;

            MyHashMap<GameState.Position, Integer> distanceMap = new MyHashMap();
            for(Mine m : advancedGameState.getMines().values()){
                distanceMap.put(m.getPosition(), Astar.estimateDistance(m.getPosition(), advancedGameState.getMe().getPos()));
            }


        }*/


        return null;
    }
}
