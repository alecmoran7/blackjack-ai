package org.mechanics;

import java.sql.Array;
import java.util.*;

public class Round {
    private Deck deck;

    // Change this variable to toggle between manual and automatic play
    private boolean automateGame = true;

    double playerCash;
    double playerWager;
    private boolean doubledDown = false;

    Vector<Card> playerCards = new Vector<Card>();
    Vector<Card> dealerCards = new Vector<Card>();

    Vector<Hand> playerHands = new Vector<Hand>();
    Hand playerPrimaryHand = null;
    Hand playerSplitHand = null;
    Hand dealerHand;

    public Round(Deck deck, double playerCash, double playerWager) {
        this.playerCash = playerCash;
        this.playerWager = playerWager;
        this.deck = deck;
    }

    private void initializeRound() {

        // Player draws one card
        playerCards.add(deck.getCard());

        // Dealer draws down card
        dealerCards.add(deck.getCard());

        // Player draws second card
        playerCards.add(deck.getCard());

        // Dealer draws up card
        dealerCards.add(deck.getCard());

        playerPrimaryHand = new Hand(Cardholder.Player, playerCards, 1.00);
        this.dealerHand = new Hand(Cardholder.Dealer, dealerCards);

    }

    public void showAllCards(Cardholder cardHolder) {
        int totalValue = 0;
        int secondTotalValue = 0;
        boolean containsAce = false;
        ArrayList<String> cardIcons = new ArrayList<String>();
        int cardNum = 0;
        Vector<Card> cardsToShow = (cardHolder == Cardholder.Player) ? playerCards : dealerCards;
        for (Card c : cardsToShow) {
            cardIcons.add(c.getIcon());
            if (c.isAce()) {
                containsAce = true;
            }
        }

        if (cardHolder == Cardholder.Player) {
            System.out.println("Player has: " + cardIcons);
        }
        else if (cardHolder == Cardholder.Dealer) {
            System.out.println("Dealer has: " + cardIcons);
        }

        if (containsAce) {
            System.out.println("Soft totals: " + getTotalHandValue(cardHolder));
        } else {
            System.out.println("Hard total: " + getTotalHandValue(cardHolder));
        }
    }

    public void showDealerUpCard() {
        Card firstCard = dealerCards.getFirst();
        String cardIcon = firstCard.getIcon();
        System.out.println("Dealer has: [" + cardIcon + ", ??]");
    }

    public void showDealerDownCard() {
        Card lastCard = dealerCards.getLast();
        String cardIcon = lastCard.getIcon();
        System.out.println("Dealer reveals other card: " + cardIcon);
    }


    public double startGame() throws java.lang.InterruptedException {

        initializeRound();

        showDealerUpCard();
        playerHands.add(playerPrimaryHand);
        playHand(playerPrimaryHand);
        if (playerSplitHand != null){
            playHand(playerSplitHand);
            playerHands.add(playerSplitHand);
        }
        return compareToDealerHand(playerHands);
    }

    private void hit(Hand handToHit) {
        Card newCard = deck.getCard();
        System.out.println("Hit -> " + newCard.getIcon());
        handToHit.addCard(newCard);
    }

    public ArrayList<Integer> getTotalHandValue(Cardholder cardHolder){
        ArrayList<Integer> totalHandValues = new ArrayList<Integer>(Arrays.asList(0));
        Vector<Card> cardsToCount = (cardHolder == Cardholder.Player) ? playerCards : dealerCards;
        for (Card c : cardsToCount) {
            if (c.isAce()) {
                ArrayList<Integer> totalHandValuesWithNewAce = new ArrayList<Integer>();
                for(Integer i: totalHandValues){
                    totalHandValuesWithNewAce.add(i + 1);
                    totalHandValuesWithNewAce.add(i + 11);
                }
                totalHandValues = totalHandValuesWithNewAce;
            }
            else {
                for(Integer i: totalHandValues){
                    totalHandValues.set(totalHandValues.indexOf(i),i + c.getCardValue());
                }

            }
        }
        return totalHandValues;
    }

    public int getBestValueFromHand(ArrayList<Integer> totalHandValues){
        Collections.sort(totalHandValues);
        int bestValue = totalHandValues.get(0);
        for (Integer i : totalHandValues) {
            if (i > bestValue && i <= 21) {
                bestValue = i;
            }
        }
        return bestValue;
    }

    private Hand playHand(Hand playerHand){
        playerHand.showAllCards();

        ArrayList<Integer> playersCardsTotalValues = playerHand.getTotalHandValue();
        boolean firstMove = true;

        while (true) {
            Strategy playerStrategy = new Strategy(playerHand, dealerHand);
            System.out.println("Best move: " + playerStrategy.getOptimalPlay());


            if (playerHand.hasBlackJack()){
                playerHand.setHandStatus(Hand.Status.BLACKJACK);
                break;
            }

            String input = getNextMove(playerStrategy);

            if (input.contains("d")) {
                if (!firstMove) {
                    input = "h";
                    System.out.println("Optimal move with these cards is double, however doubling not allowed after first hit");
                }
                else {
                    this.doubledDown = true;
                    hit(playerHand);
                    playersCardsTotalValues = playerHand.getTotalHandValue();
                    Collections.sort(playersCardsTotalValues);
                    System.out.println("Player doubled down with : " + getBestValueFromHand(playersCardsTotalValues));
                    break;
                }
            }
            if (input.contains("h")) {
                hit(playerHand);
                firstMove = false;
                playerHand.showAllCards();
                playersCardsTotalValues = playerHand.getTotalHandValue();
                Collections.sort(playersCardsTotalValues);
                    if (playersCardsTotalValues.getFirst() == 21) {
                    System.out.println("Player has 21");
                    playerHand.setHandStatus(Hand.Status.STAND);
                    break;
                } else if (playersCardsTotalValues.getFirst() > 21) {
                    System.out.println("Player busted with value: " + playersCardsTotalValues.getFirst());
                    playerHand.setHandStatus(Hand.Status.BUSTED);
                    break;
                    }
            }  else if (input.contains("s")) {
                System.out.println("Player stands with " + getBestValueFromHand(playersCardsTotalValues));
                playerHand.setHandStatus(Hand.Status.STAND);
                break;
            } else if (input.contains("p")) {
                if (playerHands.size() == 1 && playerHand.getCards().size() == 2){

                    Vector<Card> secondHandCards = new Vector<Card>();
                    Card secondCard = playerHand.getCards().getLast();
                    secondHandCards.add(secondCard);
                    playerHand.removeCard(secondCard);

                    this.playerSplitHand = new Hand(Cardholder.Player, secondHandCards, playerWager);
                }
            } else {
                System.out.println("Input not recognized, exiting");
                System.exit(9);
            }
        }
        return playerHand;
    }

    private double compareToDealerHand(Vector<Hand> playerHands){
        boolean playerBusted = false;
        if (playerHands.getFirst().getHandStatus() == Hand.Status.BUSTED && playerHands.getLast().getHandStatus() == Hand.Status.BUSTED){
            playerBusted = true;
        }

        ArrayList<Integer> dealersCardsTotalValues = dealerHand.getTotalHandValue();
        showDealerDownCard();
        if (!playerBusted) {
            while (dealerHand.getHandStatus() == Hand.Status.PLAYING) {
                if (dealersCardsTotalValues.getFirst() > 0 && dealersCardsTotalValues.getFirst() < 17) {
                    hit(dealerHand);
                    dealerHand.showAllCards();
                    dealersCardsTotalValues = dealerHand.getTotalHandValue();
                    Collections.sort(dealersCardsTotalValues);
                    continue;
                }
                //TODO: iterate through all values in dealer's soft totals if H17 is implemented in future
                for (Integer totalValue : dealersCardsTotalValues) {
                    if (totalValue >= 17 && totalValue <= 21) {
                        System.out.println("Dealer stands at " + getBestValueFromHand(dealersCardsTotalValues));
                        dealerHand.setHandStatus(Hand.Status.STAND);
                        break;
                    }
                }
                if (dealersCardsTotalValues.getFirst() > 21) {
                    System.out.println("Dealer busted with value: " + dealersCardsTotalValues.getFirst());
                    dealerHand.setHandStatus(Hand.Status.BUSTED);
                    break;
                }
            }
        }
        for (Hand playerHand: playerHands) {
            if (playerHand.getHandStatus() == Hand.Status.BLACKJACK && dealerHand.getHandStatus() == Hand.Status.BLACKJACK) {
                executePush();
            } else if (playerHand.getHandStatus() == Hand.Status.BLACKJACK && dealerHand.getHandStatus() != Hand.Status.BLACKJACK) {
                executePlayerWin(1.5);
            } else if (playerHand.getHandStatus() != Hand.Status.BLACKJACK && dealerHand.getHandStatus() == Hand.Status.BLACKJACK) {
                executePlayerLoss();
            } else if (playerHand.getHandStatus() == Hand.Status.BUSTED) {
                System.out.println("Dealer wins");
                playerCash = executePlayerLoss();
            } else if (dealerHand.getHandStatus() == Hand.Status.BUSTED) {
                System.out.println("Player wins");
                playerCash = executePlayerWin(1.0);
            } else if (dealerHand.getBestValue() > playerHand.getBestValue()) {
                System.out.println("Dealer wins");
                playerCash = executePlayerLoss();
            } else if (dealerHand.getBestValue() < playerHand.getBestValue()) {
                System.out.println("Player wins");
                playerCash = executePlayerWin(1.0);
            } else if (dealerHand.getBestValue() == playerHand.getBestValue()) {
                System.out.println("Push");
                executePush();
            }

        }
        return playerCash;
    }

    private double executePlayerWin(double payoutRatio){
        if (doubledDown){
            playerCash = playerCash + (playerWager * 2);
        }
        else {
            playerCash = playerCash + (playerWager * payoutRatio);
        }
        return playerCash;
    }

    private double executePlayerLoss(){
        if (doubledDown){
            playerCash = playerCash - (playerWager * 2);
        }
        else {
            playerCash = playerCash - playerWager;
        }
        return playerCash;
    }

    private double executePush(){
        return playerCash;
    }

    private String getNextMove(Strategy strategy){
        if (automateGame){
            return strategy.autoPlay();
        }
        else {
            System.out.print("Enter your next move: ('h' for hit, 's' for stand, 'd' for double, 'p' for split): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            return input;
        }
    }

}
