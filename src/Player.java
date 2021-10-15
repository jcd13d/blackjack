import java.util.ArrayList;

// General player class
public abstract class Player {
    static int totalPlayers = 0;

    String name;
    private int numWins;
    protected Utility utils;

    public Player() {
        this(String.format("Player %S", totalPlayers));
    }

    public Player(String name) {
        this.name = name;
        numWins = 0;
        utils = new Utility();
    }

    // implement the move logic for this type of player
    public abstract void getPlayerMove(Deck deck);

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

}
