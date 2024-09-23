package mechanics;
public class Game {

    Deck deck = new Deck();

    public Game(){
//        System.out.println("Presenting the deck of cards");
//        deck.revealAllCards();
        Round round = new Round(deck);
    }


    public static void main(String[] args){
        Game game = new Game();

    }
}
