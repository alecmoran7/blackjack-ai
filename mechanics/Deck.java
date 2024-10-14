package mechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Collections;

public class Deck {

    //TODO: Construct methods to draw cards, shuffle, etc.

    private Vector<Card> cards;

    public Deck() {
        this.cards = initializeDeck();
    }

    private Vector<Card> initializeDeck() {

        Vector<Card> deck = new Vector<Card>();

        // Including 8 full decks of cards
        for (int i = 0; i < 8; i++) {
            Card heart_card_1 = new Card("Ace of Hearts", "A♥", true);
            Card heart_card_2 = new Card("2 of Hearts", "2♥", 2);
            Card heart_card_3 = new Card("3 of Hearts", "3♥", 3);
            Card heart_card_4 = new Card("4 of Hearts", "4♥", 4);
            Card heart_card_5 = new Card("5 of Hearts", "5♥", 5);
            Card heart_card_6 = new Card("6 of Hearts", "6♥", 6);
            Card heart_card_7 = new Card("7 of Hearts", "7♥", 7);
            Card heart_card_8 = new Card("8 of Hearts", "8♥", 8);
            Card heart_card_9 = new Card("9 of Hearts", "9♥", 9);
            Card heart_card_10 = new Card("10 of Hearts", "10♥", 10);
            Card heart_card_11 = new Card("Jack of Hearts", "J♥", 10);
            Card heart_card_12 = new Card("Queen of Hearts", "Q♥", 10);
            Card heart_card_13 = new Card("King of Hearts", "K♥", 10);

            Card diamond_card_1 = new Card("Ace of Diamonds", "A♦", true);
            Card diamond_card_2 = new Card("2 of Diamonds", "2♦", 2);
            Card diamond_card_3 = new Card("3 of Diamonds", "3♦", 3);
            Card diamond_card_4 = new Card("4 of Diamonds", "4♦", 4);
            Card diamond_card_5 = new Card("5 of Diamonds", "5♦", 5);
            Card diamond_card_6 = new Card("6 of Diamonds", "6♦", 6);
            Card diamond_card_7 = new Card("7 of Diamonds", "7♦", 7);
            Card diamond_card_8 = new Card("8 of Diamonds", "8♦", 8);
            Card diamond_card_9 = new Card("9 of Diamonds", "9♦", 9);
            Card diamond_card_10 = new Card("10 of Diamonds", "10♦", 10);
            Card diamond_card_11 = new Card("Jack of Diamonds", "J♦", 10);
            Card diamond_card_12 = new Card("Queen of Diamonds", "Q♦", 10);
            Card diamond_card_13 = new Card("King of Diamonds", "K♦", 10);

            Card club_card_1 = new Card("Ace of Clubs", "A♣", true);
            Card club_card_2 = new Card("2 of Clubs", "2♣", 2);
            Card club_card_3 = new Card("3 of Clubs", "3♣", 3);
            Card club_card_4 = new Card("4 of Clubs", "4♣", 4);
            Card club_card_5 = new Card("5 of Clubs", "5♣", 5);
            Card club_card_6 = new Card("6 of Clubs", "6♣", 6);
            Card club_card_7 = new Card("7 of Clubs", "7♣", 7);
            Card club_card_8 = new Card("8 of Clubs", "8♣", 8);
            Card club_card_9 = new Card("9 of Clubs", "9♣", 9);
            Card club_card_10 = new Card("10 of Clubs", "10♣", 10);
            Card club_card_11 = new Card("Jack of Clubs", "J♣", 10);
            Card club_card_12 = new Card("Queen of Clubs", "Q♣", 10);
            Card club_card_13 = new Card("King of Clubs", "K♣", 10);

            Card spades_card_1 = new Card("Ace of Spades", "A♠", true);
            Card spades_card_2 = new Card("2 of Spades", "2♠", 2);
            Card spades_card_3 = new Card("3 of Spades", "3♠", 3);
            Card spades_card_4 = new Card("4 of Spades", "4♠", 4);
            Card spades_card_5 = new Card("5 of Spades", "5♠", 5);
            Card spades_card_6 = new Card("6 of Spades", "6♠", 6);
            Card spades_card_7 = new Card("7 of Spades", "7♠", 7);
            Card spades_card_8 = new Card("8 of Spades", "8♠", 8);
            Card spades_card_9 = new Card("9 of Spades", "9♠", 9);
            Card spades_card_10 = new Card("10 of Spades", "10♠", 10);
            Card spades_card_11 = new Card("Jack of Spades", "J♠", 10);
            Card spades_card_12 = new Card("Queen of Spades", "Q♠", 10);
            Card spades_card_13 = new Card("King of Spades", "K♠", 10);
            deck.addAll(new ArrayList<Card>(List.of(heart_card_1, heart_card_2, heart_card_3, heart_card_4, heart_card_5, heart_card_6, heart_card_7, heart_card_8, heart_card_9, heart_card_10, heart_card_11, heart_card_12, heart_card_13, diamond_card_1, diamond_card_2, diamond_card_3, diamond_card_4, diamond_card_5, diamond_card_6, diamond_card_7, diamond_card_8, diamond_card_9, diamond_card_10, diamond_card_11, diamond_card_12, diamond_card_13, club_card_1, club_card_2, club_card_3, club_card_4, club_card_5, club_card_6, club_card_7, club_card_8, club_card_9, club_card_10, club_card_11, club_card_12, club_card_13, spades_card_1, spades_card_2, spades_card_3, spades_card_4, spades_card_5, spades_card_6, spades_card_7, spades_card_8, spades_card_9, spades_card_10, spades_card_11, spades_card_12, spades_card_13)));
        }

        Collections.shuffle(deck);
        return deck;
    }

    public Card getCard(){
        Card card = this.cards.getFirst();
        this.cards.removeElementAt(0);
        return card;
    }

    public void revealAllCards(){
        int numberOfCardsRemaining = 0;
        for (Card card : this.cards){
            card.print();
            numberOfCardsRemaining++;
        }
        System.out.println("Total number of cards remaining: " + numberOfCardsRemaining);
    }
}
