import java.util.ArrayList;

public class BlackJack extends Game {
    public static final int BJNumCards = 2;

    private ArrayList<BJPlayer> playerList;
    private int numPlayers;
    private Deck deck;
    private Utility utils;
    private Player dealer;


    public BlackJack(){
        super("Black Jack", 6);
        utils = new Utility();
    }

    public void playGame() {
        gameSetup();

        getBets();

        dealCards();

        playerTurns();

        dealerTurn();

        // handle wins/losses

    }

    public boolean checkWin(Hand playerHand, Hand dealerHand) {
        // should have correct value from hand class
        return playerHand
    }

    public void endGame(){

    }

    public void checkForWin(){
        
    }

    public void gameSetup() {
        // opening prompts
        // get information to start
            // player names, num players, starting amount, etc.
    }

    public int getNumPlayers() {
        // prompt players etc
    }

    public void dealerTurn() {
        dealer.getPlayerMove(deck, true);
    }

    public void playerTurns() {
        for (Player player : playerList) {
            player.getPlayerMove(deck, false);
        }
    }

    public void getBets() {
        for (BJPlayer player : playerList) {
            player.placeBet();
        }
    }

    public void dealCards() {
        for (int i = 0; i < BlackJack.BJNumCards; i++) {
            for (BJPlayer player : playerList) {
                if (player.getBet() != 0) {
                    player.addHand(new BJHand());
                    for (Hand hand : player.getHands()) {
                        hand.addCard(deck.getTopCard(true)); // TODO add face up/down when implemented
                    }
                }
            }
        }
    }

}
