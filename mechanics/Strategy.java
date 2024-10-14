package mechanics;

import java.util.Vector;

public class Strategy{

    public enum strategyType {hardValue, softValue, split}
    public strategyType tableUsed = strategyType.hardValue;
    private String optimalPlay = "";

    private static final String[][] hardTable = {
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","h","h","h","d","d","h","h","h","h","h"},
            {"x","x","d","d","d","d","d","h","h","h","h","h"},
            {"x","x","d","d","d","d","d","d","d","d","h","h"},
            {"x","x","d","d","d","d","d","d","d","d","d","d"},
            {"x","x","h","h","s","s","s","h","h","h","h","h"},
            {"x","x","s","s","s","s","s","h","h","h","h","h"},
            {"x","x","s","s","s","s","s","h","h","h","h","h"},
            {"x","x","s","s","s","s","s","h","h","h","h","h"},
            {"x","x","s","s","s","s","s","h","h","h","h","h"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"}
    };

    private static final String[][] softTable = {
            {"x","x","x","x","x","x","x","x","x","x","x","x"},
            {"x","x","x","x","x","x","x","x","x","x","x","x"},
            {"x","x","h","h","d","d","d","h","h","h","h","h"},
            {"x","x","h","h","d","d","d","h","h","h","h","h"},
            {"x","x","h","h","d","d","d","h","h","h","h","h"},
            {"x","x","h","h","d","d","d","h","h","h","h","h"},
            {"x","x","d","d","d","d","d","h","h","h","h","h"},
            {"x","x","s","d","d","d","d","s","s","h","h","s"},
            {"x","x","s","s","s","s","d","s","s","s","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"}
    };

    private static final String[][] splitTable = {
            {"x","x","x","x","x","x","x","x","x","x","x","x"},
            {"x","x","p","p","p","p","p","p","h","h","h","h"},
            {"x","x","p","p","p","p","p","p","h","h","h","h"},
            {"x","x","p","p","p","p","p","p","p","h","h","h"},
            {"x","x","h","h","h","h","h","h","h","h","h","h"},
            {"x","x","d","d","d","d","d","d","d","d","h","h"},
            {"x","x","p","p","p","p","p","h","h","h","h","h"},
            {"x","x","p","p","p","p","p","p","h","h","h","h"},
            {"x","x","p","p","p","p","p","p","p","p","p","p"},
            {"x","x","p","p","p","p","p","s","p","p","s","s"},
            {"x","x","s","s","s","s","s","s","s","s","s","s"},
            {"x","x","p","p","p","p","p","p","p","p","p","p"}
    };

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
//            int playerHardValue = playerCards.getFirst().getCardValue() + playerCards.getLast().getCardValue();
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