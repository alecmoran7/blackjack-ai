package org.mechanics;
public class Game {

    Deck deck = new Deck();
    double winPercentage = 0.0;
    int numGamesPlayed = 0;

    public Game(){
        double gameResults = 0.0;
        double playerWager = 1.0;
        double numWins = 0.0;
        double numLosses = 0.0;
        int numGamesToPlay = 100000;

        while (numGamesPlayed < numGamesToPlay){
                ++numGamesPlayed;
//                System.out.println("    Game #" + numGamesPlayed);
                Round round = new Round(deck, gameResults, playerWager);
                double gameResult = round.startGame();
                if (gameResult > 0.0) {
                    numWins += gameResult;
                }
                else if (gameResult < 0.0) {
                    numLosses += Math.abs(gameResult);
                }

            // Shuffle all cards (8 decks) every 20 games
            if(numGamesPlayed % 20 == 0){
                deck = new Deck();
            }
            double progressAmount = ((double)numGamesPlayed / (double)numGamesToPlay) * 100;
            if (progressAmount % 10 == 0){
                System.out.println("Progress: " + progressAmount + "%");
            }
        }
        winPercentage = numWins / numGamesPlayed;
        System.out.println("Wins: " + numWins + ", Losses: " + numLosses);
        System.out.println("Win percentage: " + winPercentage * 100 + "%");
    }

    public static void main(String[] args){
        System.out.println("Starting game");
        Game game = new Game();
    }
}
