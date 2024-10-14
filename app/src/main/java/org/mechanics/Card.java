package org.mechanics;

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
       this.cardValue = -1; // this makes sure aces get both 1 and 11 set as card values
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
            return 11;
        }
        else {
            return cardValue;
        }
    }

    public void print(){
        System.out.println(name);
    }
}
