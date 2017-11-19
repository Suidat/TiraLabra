import Algorithms.BasicBot;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;
import dto.ApiKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Created by frestmau on 11.11.2017.
 */


public class Main {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new GsonFactory();
    private static final Gson gson = new Gson();
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Logger gameStateLogger = LogManager.getLogger("gameStateLogger");
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String args[]) throws Exception {
        ApiKey apiKey;
        System.out.println("Please enter your key:");
        apiKey = new ApiKey(scanner.nextLine());

        GenericUrl url;
        System.out.println("Please enter game type ([1]training or [2]arena):");
        urlScan:while(true){
            int i = Integer.parseInt(scanner.nextLine());
            switch (i) {
                case 1:
                    url = VindiniumUrl.getTrainingUrl();
                    break urlScan;
                case 2:
                    url = VindiniumUrl.getCompetitionUrl();
                    break urlScan;
                default:
                    System.out.println("Please enter number 1 or 2");
            }
        }

        System.out.println("Please enter bot type:");
        botScan:while(true){
            String s = scanner.nextLine();
            switch (s) {
                case "basic":
                    setupBasic(url, apiKey);
                    break botScan;
                default:
                    System.out.println("Please enter a valid bot");
                    System.out.println("List of valid bots: ");
                    System.out.println("1. basic");
                    //System.out.println("2. advanced");
            }
        }

        System.out.println("Please enter api key:");
        ApiKey key = new ApiKey(scanner.nextLine());

    }
    private static void setupBasic(GenericUrl url, ApiKey key){
        //To do
        BasicBot bot = new BasicBot(key, url);
    }

    public static class VindiniumUrl extends GenericUrl {
        private final static String TRAINING_URL = "http://vindinium.org/api/training";
        private final static String COMPETITION_URL = "http://vindinium.org/api/arena";

        public VindiniumUrl(String encodedUrl) {
            super(encodedUrl);
        }

        public static VindiniumUrl getCompetitionUrl() {
            return new VindiniumUrl(COMPETITION_URL);
        }

        public static VindiniumUrl getTrainingUrl() {
            return new VindiniumUrl(TRAINING_URL);
        }
    }

}
