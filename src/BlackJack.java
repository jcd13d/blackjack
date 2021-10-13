import java.util.ArrayList;

public class BlackJack extends Game {
    public static final int BJNumCards = 2;

    private ArrayList<BJPlayer> playerList;
    private int numPlayers;
    private Deck deck;
    private Utility utils;
    private Player dealer;
    private boolean keepPlaying;


    public BlackJack(){
        super("Black Jack", 6);
        utils = new Utility();
        deck = new Deck();
        dealer = new BJPlayer("Dealer", true, 0);
        keepPlaying = true;
        gameSetup();
    }

    public void playRound() {
        getBets();

        dealCards();

        playerTurns();

        dealerTurn();

        checkHandWins();
    }

    public void playGame() {
        while(keepPlaying) {
            playRound();
            keepPlaying = anotherRound();
        }
    }

    @Override
    public void endGame() {

    }

    @Override
    public void checkForWin() {

    }

    public boolean anotherRound() {
        return utils.getYesNo(playAgainPrompt());
    }

    public String playAgainPrompt() {
        return "Play again? (Yes or No)";
    }

    public void checkHandWins() {
        for (BJPlayer player : playerList) {
            for (Hand hand : player.getHands()) {
                if (hand.compareTo(dealer.getHands().get(0)) > 0) {
                    // win! get double money!
                    player.addWin();
                    player.incrementBalance(2 * player.getBet());
                } else if (hand.compareTo(dealer.getHands().get(0)) == 0) {
                    // get your money back!
                    player.incrementBalance(player.getBet());
                }
            }
            player.resetHands();
        }
    }

    public void gameSetup() {
        openingPrompts();
        numPlayers = getNumPlayers();
        playerList = new ArrayList<>();
        String playerName;
        double initBalance;

        for (int i = 0; i < numPlayers; i++) {
            // add player to list init w info needed
            playerName = getPlayerName();
            initBalance = getPlayerBalance();
            playerList.add(new BJPlayer(playerName, false, initBalance));
            playerList.get(i).addHand(new BJHand());
        }
    }

    public double getPlayerBalance() {
        // TODO add error checking
        return utils.getDouble(playerBalancePrompt());
    }

    public String playerBalancePrompt() {
        return "Please enter player initial balance: ";
    }

    public String getPlayerName() {
        return utils.getString(playerNamePrompt());
    }

    public String playerNamePrompt() {
        return String.format("Player %s, please enter your name.", playerList.size() + 1);
    }

    public void openingPrompts() {
        // TODO print opening prompts
    }

    public int getNumPlayers() {
        // prompt players etc
        // TODO input checking - put limit on players
        return utils.getInt(getNumPlayersPrompt());
    }

    public String getNumPlayersPrompt() {
        return "Please enter the (integer) number of players that will play: ";
    }

    public void dealerTurn() {
        dealer.getPlayerMove(deck);
    }

    public void playerTurns() {
        for (Player player : playerList) {
            System.out.println("Dealer Hand: ");
            System.out.println(dealer.hands.get(0));
            player.getPlayerMove(deck);
        }
    }

    public void getBets() {
        for (BJPlayer player : playerList) {
            player.placeBet();
        }
    }

    public void dealCards() {

        for (BJPlayer player_init : playerList) {
            if (player_init.getBet() != 0) {
                player_init.addHand(new BJHand());
            }
        }

        for (int i = 0; i < BlackJack.BJNumCards; i++) {
            for (BJPlayer player : playerList) {
                for (Hand hand : player.getHands()) {
                    hand.addCard(deck.getTopCard(true));
                }
            }
            dealer.getHands().get(0).addCard(deck.getTopCard(i % 2 != 0));
        }
    }

    public static void main(String[] args) {
        Game bjgame = new BlackJack();
        bjgame.playGame();
    }

}
