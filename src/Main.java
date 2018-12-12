public class Main {
    static Card a = new Card(1, Card.Suit.SPADES);
    static Card b = new Card(5, Card.Suit.DIAMONDS);
    static Card c = new Card(1, Card.Suit.SPADES);
    static Card d = new Card(11, Card.Suit.SPADES);
    static Card e = new Card(5, Card.Suit.SPADES);
    static Card f = new Card(8, Card.Suit.HEARTS);
    static Card g = new Card(2, Card.Suit.CLUBS);


    static Hand h = new Hand(a, b, c, d, e, f, g);
    static BestHand bestHand = new BestHand(h);

    public static void main(String[] args){
        // System.out.println(bestHand.checkIfStraight(h));
        System.out.println(bestHand.identifyHand(h));

        System.out.println("hi");

        //NEW test commit
    }
}
