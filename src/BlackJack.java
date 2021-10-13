import java.util.ArrayList;

public class BlackJack extends Game {
    public static final int BJNumCards = 2;

    ArrayList<BJPlayer> playerList;
    int numPlayers;
    Deck deck;
    Utility utils;

    public BlackJack(){
        super("Black Jack", 6);
        utils = new Utility();
    }

    public void playGame() {
        gameSetup();

        getBets();

        dealCards();

        playerTurns();

        // dealer move

        // handle wins/losses

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

    }

    public void dealerTurn() {

    }

    public void playerTurns() {
        for (Player player : playerList) {
            player.getPlayerMove(deck, false);
        }
    }

    public void getBets() {
        for (BJPlayer player : playerList) {
            player.placeBet(utils.getDouble(betPrompt()));
        }
    }

    public void dealCards() {
        for (int i = 0; i < BlackJack.BJNumCards; i++) {
            for (BJPlayer player : playerList) {
                if (player.getBet() != 0) {
                    player.addHand(new Hand());
                    for (Hand hand : player.getHands()) {
                        hand.addCard(deck.getTopCard(true)); // TODO add face up/down when implemented
                    }
                }
            }
        }
    }

    public String betPrompt() {
        return "Please enter decimal number for dollar amount to bet: ";
    }
    
}
