import java.util.ArrayList;

public class BlackJack extends Game {
    public static final int BJNumCards = 2;
    private double balanceLimit = 100000;

    protected ArrayList<Gambler> playerList;
    protected int numPlayers;
    protected Deck deck;
    public Utility utils;
    protected Gambler dealer;
    protected boolean keepPlaying;
    protected int maxPlayers;


    // Blackjack constructor initializing necessary variables
    public BlackJack(){
        super("Black Jack");
        utils = new Utility();
        deck = new Deck();
        deck.shuffleDeck();
        dealer = new BJPlayer("Dealer", true, 0);
        keepPlaying = true;
        maxPlayers = 10;
        gameSetup();
    }
    public BlackJack(String name){
        super(name);
    }

    // logic associated with playing a round of blackjack
    public void playRound() {
        getBets();

        dealCards();

        playerTurns();

        dealerTurn();

        checkHandWins();

        playerStatus();
    }

    // check status of the player - if they have run out of money
    public void playerStatus() {
        System.out.println();
        playerList.forEach(System.out::println);        // bonus for this line??
        for(Gambler player : playerList){
            if(player.getBalance() <= 0){
                System.out.println(String.format("\nPlayer %s went broke! Player %s was removed!", player.getName(), player.getName()));
                playerList.remove(player);
                break;
            }
        }
    }

    // main loop for playing a session of blackjack
    public void playGame() {
        while(keepPlaying) {
            playRound();
            if ( playerList.size() == 0) {
                keepPlaying = false;
                System.out.println("No more players playing! Game Ended");
                break;
            }
            keepPlaying = anotherRound();
        }
    }

    // method to prompt and get user input on playing another round
    public boolean anotherRound() {
        return utils.getYesNo(playAgainPrompt());
    }

    public String playAgainPrompt() {
        return "\nPlay again? (Yes or No)";
    }

    // logic to handle hand status and payouts at the end of a round
    public void checkHandWins() {
        System.out.println("\n Results! \n");
        for (Gambler player : playerList) {
            for (Hand hand : player.getHands()) {
                if (hand.compareTo(dealer.getHands().get(0)) > 0) {
                    System.out.println(String.format("\n%s's hand is a winner!", player.getName()));
                    System.out.println(hand);
                    System.out.printf("Hand value: %s\n", hand.getHandValue());
                    // win! get double money!
                    player.addWin();
                    player.incrementBalance(2 * player.getBet());
                } else if (hand.compareTo(dealer.getHands().get(0)) == 0) {
                    // get your money back!
                    System.out.println(String.format("\n%s's hand ties, money refunded.", player.getName()));
                    System.out.println(hand);
                    System.out.printf("Hand value: %s\n", hand.getHandValue());
                    player.incrementBalance(player.getBet());
                } else {
                    System.out.println(String.format("\n%s's hand loses!", player.getName()));
                    System.out.println(hand);
                    System.out.printf("Hand value: %s\n", hand.getHandValue());
                }
                deck.recycleCards(hand.getHand());
            }
            player.resetHands();
        }
        deck.recycleCards(dealer.getHands().get(0).getHand());
        dealer.resetHands();
    }

    // General setup that happens at the beginning of each game
    public void gameSetup() {

        do {
            numPlayers = getNumPlayers();
        } while (getNumPlayersCheck(numPlayers));

        playerList = new ArrayList<>();
        String playerName;
        double initBalance;

        for (int i = 0; i < numPlayers; i++) {
            // add player to list init w info needed
            playerName = getPlayerName();
            do {
                initBalance = getPlayerBalance();
            } while (playerBalanceCheck(initBalance, balanceLimit));
            playerList.add(new BJPlayer(playerName, false, initBalance));
        }
    }

    // prompt and get start balance from user
    public double getPlayerBalance() {
        return utils.getDouble(playerBalancePrompt());
    }

    // Check for valid balance
    private boolean playerBalanceCheck(double balance, double balanceLimit) {
        System.out.println(balance);
        return !((balance > 0) & (balance < balanceLimit));
    }

    public String playerBalancePrompt() {
        return String.format("\nPlease enter player initial balance (number > 0 and less than %s): ", balanceLimit);
    }

    public String getPlayerName() {
        return utils.getString(playerNamePrompt());
    }

    public String playerNamePrompt() {
        return String.format("\nPlayer %s, please enter your name.", playerList.size() + 1);
    }

    // prompt and logic to get input for number of players
    public int getNumPlayers() {
        // prompt players etc
        return utils.getInt(getNumPlayersPrompt());
    }

    public String getNumPlayersPrompt() {
        return String.format("\nPlease enter the (integer) number of players that will play (between 1 and %s players): ", maxPlayers);
    }

    // ensure valid number of players
    private boolean getNumPlayersCheck(int input) {
        return !((input >= 1) & (input <= maxPlayers));
    }

    // dealer move
    public void dealerTurn() {
        dealer.getPlayerMove(deck);
    }

    // wrapper to handle all players taking their turns
    public void playerTurns() {
        for (Gambler player : playerList) {
            if (!player.getPlayingRound()){ continue;}
            System.out.println("\nDealer Hand: ");
            System.out.println(dealer.hands.get(0));
            player.getPlayerMove(deck);
        }
    }

    // facilitate getting bets from each player
    public void getBets() {
        for (Gambler player : playerList) {
            if(player.getPlayingRound()) {
                player.placeBet();
            }
            else{
                player.setBet(0);
            }
        }
    }

    public ArrayList<Gambler> getPlayerList(){
      return this.playerList;
    }

    public Gambler getDealer(){
      return this.dealer;
    }

    // deal cards to each player
    public void dealCards() {
        // add hands if player has bet
        for (Gambler player_init : playerList) {
            if (player_init.getBet() != 0) {
                player_init.addHand(new BJHand());
            }
        }

        dealer.addHand(new BJHand());

        // two cards delt to each players hand
        for (int i = 0; i < BlackJack.BJNumCards; i++) {
            for (Gambler player : playerList) {
                for (Hand hand : player.getHands()) {
                    hand.addCard(deck.getTopCard(true));
                }
            }
            dealer.getHands().get(0).addCard(deck.getTopCard(i % 2 != 0));
        }
    }

}
