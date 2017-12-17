import Algorithms.BasicBotRunner;
import Algorithms.Bots.BasicBot;
import Algorithms.Bots.Bot;
import com.google.api.client.http.GenericUrl;

import dto.ApiKey;

import java.util.Scanner;

/**
 * Created by frestmau on 11.11.2017.
 */

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws Exception {
        ApiKey apiKey;
        System.out.println("Please enter your key:");
        apiKey = new ApiKey(scanner.nextLine());

        GenericUrl url;
        System.out.println("Please enter game type ([1]training or [2]arena):");
        urlScan:while(true) {
            try {
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
            }catch(Exception e){
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
                    break;
            }
        }

    }
    private static void setupBasic(GenericUrl url, ApiKey key){

        Bot bot = new BasicBot();
        BasicBotRunner botRunner = new BasicBotRunner(key, url, bot);
        try {
            botRunner.call();
        }catch (Exception e){
            System.out.println("Error in call");
        }

    }

    /**
     * A class to hold the different Vindinium game urls.
     */
    public static class VindiniumUrl extends GenericUrl{
        private final static String TRAINING_URL = "http://vindinium.org/api/training";
        private final static String COMPETITION_URL = "http://vindinium.org/api/arena";

        /**
         * Creates the object.
         * @param encodedUrl
         */
        public VindiniumUrl(String encodedUrl) {
            super(encodedUrl);
        }

        /**
         * Returns the url for a arena.
         * @return The url of arena games.
         */
        public static VindiniumUrl getCompetitionUrl() {
            return new VindiniumUrl(COMPETITION_URL);
        }

        /**
         * Returns the url for training.
         * @return The url for training games.
         */
        public static VindiniumUrl getTrainingUrl() {
            return new VindiniumUrl(TRAINING_URL);
        }
    }

}
