package org.mechanics;

import org.mechanics.strategytables.S17Das;
import org.mechanics.strategytables.StrategyTable;
import java.util.Vector;

public class Strategy{

    public enum strategyType {hardValue, softValue, split}
    public strategyType tableUsed = strategyType.hardValue;
    private String optimalPlay = "";
    private StrategyTable strategyTable;

    /*
     *  Strategy tables may be interchangeable between S17Das and S17NDas.
     *   H17Das and H17NDas are currently supported.
     */
    private final String[][] hardTable = S17Das.hardTable;
    private final String[][] softTable = S17Das.softTable;
    private final String[][] splitTable = S17Das.splitTable;

    Hand playerHand;
    Hand dealerHand;

    public Strategy(Hand playerHand, Hand dealerHand){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.getBestMove();
    }

    public void getBestMove(){

        Vector<Card> playerCards = playerHand.getCards();
        Vector<Card> dealerCards = dealerHand.getCards();

        // Check for soft value strategies
        if ((playerCards.getFirst().isAce() && !playerCards.getLast().isAce()) || (!playerCards.getFirst().isAce() && playerCards.getLast().isAce())){
            this.tableUsed = strategyType.softValue;
            boolean isFirstCardAce = (playerCards.getFirst().isAce());
            if (isFirstCardAce) {
                optimalPlay = softTable[playerCards.getLast().getCardValue()][dealerCards.getFirst().getCardValue()];
            }
            else {
                optimalPlay = softTable[playerCards.getFirst().getCardValue()][dealerCards.getFirst().getCardValue()];
            }
        }

        // Check for split strategies
        else if (playerCards.getFirst().getIcon().substring(0,1) == playerCards.getLast().getIcon().substring(0,1)){
            this.tableUsed = strategyType.split;
            optimalPlay = splitTable[playerCards.getFirst().getCardValue()][dealerCards.getFirst().getCardValue()];
        }

        // Else go with hard value strategies
        else {
            this.tableUsed = strategyType.hardValue;
            int bestPlayerHardValue = playerHand.getBestValue();
            optimalPlay = hardTable[bestPlayerHardValue][dealerCards.getFirst().getCardValue()];
        }
    }

    public strategyType getStrategy() {
        return tableUsed;
    }

    public String getOptimalPlay() {
        switch (optimalPlay){
            case "s": return"stand";
            case "h": return"hit";
            case "d": return"double";
            case "p": return"split";
            case "x":
            default: System.exit(5);
        }
        return "Error";
    }

    public String autoPlay(){
        return optimalPlay;
    }

}