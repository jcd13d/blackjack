import java.util.ArrayList;

public abstract class Player {
    static int totalPlayers = 0;

    String name;
    int numWins;
    ArrayList<Hand> hands;
    boolean dealer;

    public Player() {
        this(String.format("Player %S", totalPlayers));
    }

    public Player(String name) {
        this(name, false);
    }

    public Player(String name, boolean dealer) {
        this.name = name;
        numWins = 0;
        hands = new ArrayList<Hand>();
        addHand(new Hand());
        this.dealer = dealer;
    }

    public abstract boolean getPlayerMove();

    public static int getTotalPlayers() {
        return totalPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumWins() {
        return numWins;
    }

    public void addWin() {
        numWins++;
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public void addHand(Hand hand) {
        hands.add(hand);
    }

}
