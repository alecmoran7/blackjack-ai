package org.mechanics;
public class Game {

    Deck deck = new Deck();
    double winPercentage = 0.0;
    int numGamesPlayed = 0;

    public Game(){
        double playerCash = 0.0;
        double playerWager = 1.0;

        while (numGamesPlayed < 1000000){
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
            System.out.println("Win (+) vs Loss (-) ratio: " + playerCash);
            // Shuffle all cards (8 decks) every 20 games
            if(numGamesPlayed % 20 == 0){
                System.out.println("Shuffling deck");
                deck = new Deck();
            }

            double netWins =  (numGamesPlayed / 2) + (playerCash/2);
            double netLosses =  (numGamesPlayed / 2) - (playerCash/2);
            System.out.println("Wins: " + netWins + ", Losses: " + netLosses);
            winPercentage = netWins / numGamesPlayed;
            System.out.println("Win percentage: " + winPercentage);
        }
    }


    public static void main(String[] args){
        System.out.println("Starting game");
        Game game = new Game();
    }
}
