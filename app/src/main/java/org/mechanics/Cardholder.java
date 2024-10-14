package org.mechanics;
enum Cardholder {
    Player, Dealer;

public String toString(){
    switch (this) {
        case Player: return "Player";
        case Dealer: return "Dealer";
    }
    return "";
}

}
