package mechanics;

import java.sql.Array;
import java.util.*;

public class Round {
    private Deck deck;

    private enum Cardholder {Player, Dealer}

    Vector<Card> playerCards = new Vector<Card>();
    Vector<Card> dealerCards = new Vector<Card>();

    public Round(Deck deck) {
        this.deck = deck;

        initializeRound();
        startGame();
    }

    private void initializeRound() {

        // Player draws one card
        playerCards.add(deck.getCard());

        // Dealer draws one card
        dealerCards.add(deck.getCard());

        // Player draws second card
        playerCards.add(deck.getCard());

        // Dealer draws second card
        dealerCards.add(deck.getCard());
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
//            cardNum++;
//            c.print();
            if (c.isAce()) {
                containsAce = true;
//                totalValue += 11;
//                secondTotalValue += 1;
            }
//                totalValue += c.getCardValue();
//            }
        }
        System.out.println("Player has: " + cardIcons);
        if (containsAce) {
            System.out.println("Soft totals: " + getTotalHandValue(cardHolder));
        } else {
            System.out.println("Hard total: " + getTotalHandValue(cardHolder));
        }
    }

    public void showDealerCardsBeforeReveal() {
        int totalValue = 0;
        Card firstCard = dealerCards.getFirst();
        String cardIcon = firstCard.getIcon();
        System.out.println("Dealer has: " + cardIcon);
        System.out.println("Visible total:" + firstCard.getCardValue());
    }

    public void showDealerCardsAfterReveal() {
        int totalValue = 0;
        Card firstCard = dealerCards.getFirst();
        String cardIcon = firstCard.getIcon();
        System.out.println("Dealer has: " + cardIcon);
        System.out.println("Visible total:" + firstCard.getCardValue());
    }


    private void startGame() {

        showDealerCardsBeforeReveal();
        System.out.println();
        showAllCards(Cardholder.Player);


        if (playerHasBlackJack() && !dealerHasBlackJack()) {
            System.out.println("Player has blackjack!");
            showAllCards(Cardholder.Player);
            showAllCards(Cardholder.Dealer);
        }
        else if (dealerHasBlackJack() && !playerHasBlackJack()) {
            System.out.println("Dealer has blackjack!");
            showAllCards(Cardholder.Player);
            showAllCards(Cardholder.Dealer);
        }
        else if (playerHasBlackJack() && dealerHasBlackJack()) {
            System.out.println("Player and dealer both have blackjack, push.");
            showAllCards(Cardholder.Player);
            showAllCards(Cardholder.Dealer);
        }
 //todo: loop until player stands or hits 21
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

//        while (input == "h" && getTotalValue().contains) {}
while (true) {
    if (input.contains("h")) {
//        System.out.println("Hit");
        hit(Cardholder.Player);
        ArrayList<Integer> playersCardsTotalValues = getTotalHandValue(Cardholder.Player);
        Collections.sort(playersCardsTotalValues);
        showAllCards(Cardholder.Player);
        if (playersCardsTotalValues.getFirst() > 0 && playersCardsTotalValues.getFirst() < 21) {
            input = scanner.nextLine();
        } else if (playersCardsTotalValues.getFirst() == 21) {
            input = "s"; // Automatically force player to stand on 21
            System.out.println("Player stands at " + getBestValueFromHand(playersCardsTotalValues));
            break;
        } else if (playersCardsTotalValues.getFirst() > 21) {
            System.out.println("Player busted with value: " + playersCardsTotalValues.getFirst());
            break;
        }



    }
    else break;
}
        ArrayList<Integer> playersCardsTotalValues = getTotalHandValue(Cardholder.Player);
        System.out.println("Player stands at " + getBestValueFromHand(playersCardsTotalValues));
//        ArrayList<Integer> playersCardsTotalValues = getTotalHandValue(Cardholder.Player);
//        Collections.sort(playersCardsTotalValues);
//        System.out.println("Player stands at " + getBestValueFromHand(playersCardsTotalValues));

            // Hit
//        } else if (input == "s") {
//            System.out.println("Stand");
//             Stand
//        }
    }

    private void hit(Cardholder cardHolder) {
        if (cardHolder == Cardholder.Player) {
            Card newCard = deck.getCard();
            System.out.println("Hit -> " + newCard.getIcon());
            playerCards.add(newCard);
        }
        else if (cardHolder == Cardholder.Dealer) {
            Card newCard = deck.getCard();
            System.out.println("Hit -> " + newCard.getIcon());
            dealerCards.add(newCard);
        }
    }

    public boolean playerHasBlackJack() {
        if (playerCards.getFirst().isAce() && !playerCards.getLast().isAce()){
           if (playerCards.getLast().getCardValue() == 10){
               return true;
           }
        }
        if (playerCards.getLast().isAce() && !playerCards.getFirst().isAce()){
            if (playerCards.getFirst().getCardValue() == 10){
                return true;
            }
        }
        return false;
    }

    public boolean dealerHasBlackJack() {
        if (dealerCards.getFirst().isAce() && !dealerCards.getLast().isAce()){
            if (dealerCards.getLast().getCardValue() == 10){
                return true;
            }
        }
        if (dealerCards.getLast().isAce() && !dealerCards.getFirst().isAce()){
            if (dealerCards.getFirst().getCardValue() == 10){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getTotalHandValue(Cardholder cardHolder){
        ArrayList<Integer> totalHandValues = new ArrayList<Integer>(Arrays.asList(0));
        Vector<Card> cardsToCount = (cardHolder == Cardholder.Player) ? playerCards : dealerCards;
        for (Card c : cardsToCount) {
//            System.out.println("totalHandValues: " + totalHandValues);
//            System.out.println(" Counting card: ");
//            c.print();
            if (c.isAce()) {
                ArrayList<Integer> totalHandValuesWithNewAce = new ArrayList<Integer>();
                for(Integer i: totalHandValues){
                    totalHandValuesWithNewAce.add(i + 1);
                    totalHandValuesWithNewAce.add(i + 11);
                }
                totalHandValues = totalHandValuesWithNewAce;
/*
                for(Integer i: totalHandValues){
                    System.out.println("At index: " + i + " we got " + totalHandValues.get(i));
                    totalHandValues.set(i,totalHandValues.get(i)+1);
                    if (totalHandValues.size() <= i+1){
                        totalHandValues.add(totalHandValues.get(i)+11);
                    }
                    totalHandValues.set(i+1,totalHandValues.get(i)+11);
                }
*/
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


}
