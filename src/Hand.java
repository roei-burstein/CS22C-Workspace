public class Hand extends Card{

    Card[] cardArr = new Card[7];

    public Hand(){
        cardArr[0] = new Card();
        cardArr[1] = new Card();
    }

    public Hand(Card a, Card b){
        cardArr[0] = a;
        cardArr[1] = b;
    }

    public Hand(Card a, Card b, Card c, Card d, Card e, Card f, Card g){
        cardArr[0] = a;
        cardArr[1] = b;
        cardArr[2] = c;
        cardArr[3] = d;
        cardArr[4] = e;
        cardArr[5] = f;
        cardArr[6] = g;
    }

    public Hand(Card[] c){
        cardArr = c.clone();
    }

    public Card getHole1() {
        return cardArr[0];
    }

    public void setHole1(Card hole1) {
        cardArr[0] = hole1;
    }

    public Card getHole2() {
        return cardArr[1];
    }

    public void setHole2(Card hole2) {
        cardArr[1] = hole2;
    }

    public Card addCard(Card c){
        for(int i = 2; i < cardArr.length; i++){
            if(cardArr[i] == null) {
                cardArr[i] = c;
                return cardArr[i];
            }
        }
        throw new ArithmeticException("Hand is already full");
    }

    public Card[] getCardArr() {
        return cardArr;
    }
}
