public class Player {
    String name;
    float chips;
    Hand hand;

    public Player(){
        name = "Default Name";
        chips = 0;
        hand = new Hand();
    }

    public Player(String n){
        name = n;
        chips = 0;
        hand = new Hand();
    }

    public Player(String n, float money, Hand h){
        name = n;
        chips = money;
        hand = h;
    }
}
