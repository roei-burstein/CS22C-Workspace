public class Card {
    int number;
    Suit suit;
    final int[] ACE = {1, 14};

    enum Suit{
        HEARTS, DIAMONDS, SPADES, CLUBS;
    }

    public Card()
    {

    }

    public Card(int n, Suit s){
        number = n;
        suit = s;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

}

