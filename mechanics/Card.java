package mechanics;

public class Card {

    private final String name;
    private final String icon;
    private final boolean isAce;
    private final int[] aceValues = new int[]{1,11};
    private final int cardValue;

    public Card(String name, String icon, boolean isAce){
       this.name = name;
       this.icon = icon;
       this.isAce = isAce;
       this.cardValue = -1; // Avoid calling getCardValue() on aces (because they have two values)
    }

    public Card(String name, String icon, int cardValue){
        this.name = name;
        this.icon = icon;
        this.cardValue = cardValue;
        this.isAce = false;
    }


    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isAce() {
        return isAce;
    }

    public int[] getAceValues() {
        return aceValues;
    }

    public int getCardValue() {
        if (this.isAce()){
            System.err.println("Cannot call getCardValue() on an Ace card");
            System.exit(1);
        }
        else {
            return cardValue;
        }
        return -1;
    }

    public void print(){
        System.out.println(name);
    }
}
