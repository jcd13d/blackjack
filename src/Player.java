import java.util.ArrayList;

public abstract class Player {
    static int totalPlayers = 0;

    String name;
    int numWins;
    ArrayList<Hand> hand;
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
        hand = new ArrayList<Hand>();
        hand.add(new Hand());
        this.dealer = dealer;
    }

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

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

}
