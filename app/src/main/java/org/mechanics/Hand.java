package org.mechanics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Hand {

    public enum Status {BLACKJACK, PLAYING, STAND, BUSTED}
    private Status handStatus;
    Vector<Card> cards = new Vector<Card>();
    double handWager = 0.0;
    Cardholder cardHolder;

    public Hand(Cardholder cardHolder, Vector<Card> cards, double handWager){
        if (cardHolder != Cardholder.Player){
            // Exit program if dealer tries to use this constructor //TODO: Use cleaner implementation in the future.
            System.exit(8);
        }
        this.cardHolder = cardHolder;
        this.cards = cards;
        this.handWager = handWager;
        this.handStatus = Status.PLAYING;
    }

    // For dealer's hand this is used
    public Hand(Cardholder cardHolder, Vector<Card> cards){
        this.cardHolder = cardHolder;
        this.cards = cards;
        this.handStatus = Status.PLAYING;
    }

    public void showAllCards() {
        int totalValue = 0;
        int secondTotalValue = 0;
        boolean containsAce = false;
        ArrayList<String> cardIcons = new ArrayList<String>();
        int cardNum = 0;
        for (Card c : cards) {
            cardIcons.add(c.getIcon());
            if (c.isAce()) {
                containsAce = true;
            }
        }
        System.out.println(this.cardHolder + " has: " + cardIcons);

        if (containsAce) {
            System.out.println("Soft totals: " + getTotalHandValue());
        } else {
            System.out.println("Hard total: " + getTotalHandValue());
        }
    }

    public ArrayList<Integer> getTotalHandValue(){
        ArrayList<Integer> totalHandValues = new ArrayList<Integer>(Arrays.asList(0));
        for (Card c : cards) {
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

    public boolean hasBlackJack(){
        if (cards.getFirst().getCardValue() + cards.getLast().getCardValue() == 21){
            return true;
        }
        else return false;
    }

    public Status getHandStatus(){
        return this.handStatus;
    }

    public void setHandStatus(Status handStatus){
        this.handStatus = handStatus;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void removeCard(Card card){
        this.cards.remove(card);
    }

    public Vector<Card> getCards(){
        return cards;
    }

    public Integer getBestValue(){
        int bestValue = 0;
        for (Integer i: getTotalHandValue()){
            if (i <= 21 && i > bestValue){
                bestValue = i;
            }
        }
        return bestValue;
    }

}