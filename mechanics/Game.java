package mechanics;
public class Game {

    Deck deck = new Deck();
    int numGamesToPlay = 100;
    int gamesWon = 0;
    int gamesLost = 0;
    double winPercentage = 0.0;
    int numGamesPlayed = 0;

    public Game(){
//        System.out.println("Presenting the deck of cards");
//        deck.revealAllCards();
        //TODO: Once games can be played automatically, setup looped games
//        for (int i = 0; i < numGamesToPlay; i++){}
        double playerCash = 0.0;
        double playerWager = 1.0;

        while (numGamesPlayed < 1000000){
//        while (numGamesPlayed < 10000 && playerCash > 0){
//        while (playerCash > 0){
            try {
                ++numGamesPlayed;
                System.out.println("    Game #" + numGamesPlayed);
                Round round = new Round(deck, playerCash, playerWager);
                playerCash = round.startGame();
            }
            catch (Exception e){
                System.err.println("Error with running blackjack game");
                e.printStackTrace();
            }
            System.out.println("Player now has $" + playerCash);
            // get a fresh new shuffled deck every 5 games;
            if(numGamesPlayed % 20 == 0){
                System.out.println("Shuffling deck");
                deck = new Deck();
            }

            double netWins =  (numGamesPlayed / 2) + (playerCash/2);
            double netLosses =  (numGamesPlayed / 2) - (playerCash/2);
//            double netLosses = numGamesPlayed - netWins;
//            double netWins =  playerCash / numGamesPlayed;
//            double netLosses = numGamesPlayed - netWins;
            System.out.println("Wins: " + netWins + ", Losses: " + netLosses);
            winPercentage = netWins / numGamesPlayed;
            System.out.println("Win percentage: " + winPercentage);
        }
    }


    public static void main(String[] args){
        Game game = new Game();

    }
}
