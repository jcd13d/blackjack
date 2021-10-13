import java.util.ArrayList;

public abstract class Player {
    static int totalPlayers = 0;

    String name;
    private int numWins;
    ArrayList<Hand> hands;
    private boolean dealer;
    protected Utility utils;

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
        utils = new Utility();
    }

    public abstract boolean getPlayerMove(Deck deck, boolean dealer);

    public static int getTotalPlayers() {
        return totalPlayers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Hand> getHands() {
        return hands;
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
