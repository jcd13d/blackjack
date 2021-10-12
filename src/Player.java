import java.util.ArrayList;

public abstract class Player {
    String name;
    int numWins;
    ArrayList<Hand> hand;
    boolean dealer;

    public Player(String name) {
        this(name, false);
    }

    public Player(String name, boolean dealer) {
        this.name = name;
        numWins = 0;
        hand = new ArrayList<Hand>();
        hand.add(new Hand());
        this.dealer = dealer;
    }

}
