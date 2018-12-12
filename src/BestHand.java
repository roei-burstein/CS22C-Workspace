import java.util.*;
public class BestHand extends Hand {
    Card[] cardArr = new Card[7];
    ArrayList<Card> idCards;

    enum HandIdentifier {
        HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND,
        STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND,
        STRAIGHT_FLUSH, ROYAL_FLUSH;
    }

    public BestHand() {
    }

    public BestHand(Card a, Card b) {
        cardArr[0] = a;
        cardArr[1] = b;
    }

    public BestHand(Card a, Card b, Card c, Card d, Card e, Card f, Card g) {
        cardArr[0] = a;
        cardArr[1] = b;
        cardArr[2] = c;
        cardArr[3] = d;
        cardArr[4] = e;
        cardArr[5] = f;
        cardArr[6] = g;

    }

    public BestHand(Card[] c){
        cardArr = c.clone();
    }

    public BestHand(Hand h){
        cardArr = h.getCardArr().clone();
    }

    //public BestHand calculateBestHand(Hand h) {

    //}

    public HandIdentifier identifyHand(Hand h) {
        HandIdentifier handId;
        handId = HandIdentifier.HIGH_CARD; //High Card
        //idCards.add(findHighestCard(h));

        ArrayList<Card> CardList = findPairs(h);
        if(CardList.size() >= 2) {
            if (CardList.size() == 2) {
                handId = HandIdentifier.PAIR; //Pair
                //idCards.clear();
            } else if (CardList.size() == 4) {
                handId = HandIdentifier.TWO_PAIR; //Two Pair
                //idCards.clear();
            } else if (CardList.size() == 3) {
                handId = HandIdentifier.THREE_OF_A_KIND; //Three of a Kind
                //idCards.clear();
            }
            //idCards = new ArrayList<Card>(CardList);
        }

        if(checkIfStraightNew(h)){
            handId = HandIdentifier.STRAIGHT;
        }

        if(checkIfFlush(h)){
            handId = HandIdentifier.FLUSH;
        }

        if(CardList.size() == 5){
            handId = HandIdentifier.FULL_HOUSE;
        }

        if(CardList.size() == 4){
            int[] cardArray = new int[14];
            for(Card c: CardList){
                cardArray[c.getNumber() - 1]++;
            }
            for(int i: cardArray){
                if(i == 4) handId = HandIdentifier.FOUR_OF_A_KIND;
            }
        }


        return handId;
    }

    public boolean checkIfFlush(Hand h) {
        Card[] cardArray = h.getCardArr();
        Card highestFlushCard = null;
        Suit flushSuit = null;
        int[] suitCount = new int[4];
        for (Card c : cardArray) {
            if (c.getSuit() == Suit.HEARTS) ++suitCount[0];
            else if (c.getSuit() == Suit.DIAMONDS) ++suitCount[1];
            else if (c.getSuit() == Suit.SPADES) ++suitCount[2];
            else ++suitCount[3];
            }
            for (int i = 0; i < suitCount.length; i++) {
                if (suitCount[i] >= 5){
                    /*switch(i){
                        case 0: flushSuit = Suit.HEARTS;
                        case 1: flushSuit = Suit.DIAMONDS;
                        case 2: flushSuit = Suit.SPADES;
                        case 3: flushSuit = Suit.CLUBS;
                    }
                    */
                    return true;
                }
            }
            return false;
        }

        public boolean checkIfStraightFlush(Hand h){
            Card[] cardArray = h.getCardArr();

            return false;
        }

/*
    public boolean checkIfStraight(Hand h) {
        Card[] cardArray = h.getCardArr();
        boolean[] straightCards = {false, false, false, false};

        for(Card b: cardArray) {
            for (Card c : cardArray) {
                if (c.getNumber() == b.getNumber() + 1) straightCards[0] = true;
                else if (c.getNumber() == b.getNumber() + 2) straightCards[1] = true;
                else if (c.getNumber() == b.getNumber() + 3) straightCards[2] = true;
                else if (c.getNumber() == b.getNumber() + 4) straightCards[3] = true;
                if((!Arrays.asList(straightCards).contains(false)){
                    System.out.println(Arrays.asList(straightCards).contains(false));
                    return true;
                }
            }
            straightCards = new boolean[]{false, false, false, false};
        }
        boolean[] aceHighStraight = {false, false, false, false, false};
        for(Card d: cardArray){
            if(d.getNumber() == 10) aceHighStraight[0] = true;
            else if(d.getNumber() == 11) aceHighStraight[1] = true;
            else if(d.getNumber() == 12) aceHighStraight[2] = true;
            else if(d.getNumber() == 13) aceHighStraight[3] = true;
            else if(d.getNumber() == 14 || d.getNumber() == 1) aceHighStraight[4] = true;
        }

        if(!(Arrays.asList(aceHighStraight).contains(false))) return true; //error
        
        return false;
    }
*/
    public boolean checkIfStraightNew(Hand h)
    {
        Card[] cardArray = h.getCardArr();
        int[] cardNumbers = new int[7];

        for(int i = 0; i < 7; i++)
            cardNumbers[i] = cardArray[i].number;

        Arrays.sort(cardNumbers);


        int consecutive = 0;

        for(int i = 0; i < 7; i++) {
            consecutive=0;
            for (int y = i; y < 6; y++) {
                if (cardNumbers[y] + 1 == cardNumbers[y+1])
                    consecutive++;

            }
            if(consecutive >= 4)
                return true;
        }



        return false;
    }
/*
    public boolean checkIfStraight(Hand h)
    {
        Card[] cardArray = h.getCardArr();
        ArrayList<Integer> cardNumbers = new ArrayList<>();
        int[] cardNumbersToBeConverted = new int[7];

        for(int i = 0; i < 7; i++)
            cardNumbersToBeConverted[i] = cardArray[i].number;

        Arrays.sort(cardNumbersToBeConverted);

        int numberOfAces = 0;

        for(int i : cardNumbersToBeConverted) {
            cardNumbers.add(i);
            if(i == 1)
                numberOfAces++;
        }

        for(int i = 0; i < numberOfAces; i++)
            cardNumbers.add(14);


        int consecutive = 0;

        for(int i = 0; i < 7; i++) {
            consecutive=0;
            for (int y = i; y < 6; y++) {
                if (cardNumbers.get(y) + 1 == cardNumbers.get(y+1))
                    consecutive++;
            }
            if(consecutive >= 4)
                return true;
        }



        return false;
    }
    */

    public Card findHighestCard(Hand h) {
        Card highestCard = new Card(-1, Suit.SPADES);
        for (Card c : h.getCardArr()) {
            if (c.getNumber() > highestCard.getNumber()) {
                highestCard = c;
            }
        }
        return highestCard;
    }

    public Card findLowestCard(Hand h) {
        Card lowestCard = new Card(15, Suit.SPADES);
        for (Card c : h.getCardArr()) {
            if (c.getNumber() < lowestCard.getNumber()) {
                lowestCard = c;
            }
        }
        return lowestCard;
    }

    public ArrayList<Card> findPairs(Hand h) {
        ArrayList<Card> CardList = new ArrayList<Card>();
        for (int j = 0; j < h.getCardArr().length; j++) {
            for (int k = j + 1; k < h.getCardArr().length; k++) {
                if (k != j && h.getCardArr()[k].getNumber() == h.getCardArr()[j].getNumber()){
                    CardList.add(cardArr[j]);
                    CardList.add(cardArr[k]);
                }
            }
        }
        ArrayList<Card> tempCardList = new ArrayList<Card>();
        for(int i = 0; i < CardList.size(); i++){
            if(!tempCardList.contains(CardList.get(i))) tempCardList.add(CardList.get(i));
        }
        return tempCardList;
    }
}
