import java.util.Collections;
import java.util.ArrayList;

//Trianta Ena class extends the BlackJack class to implement the game play logic for Trianta Ena
public class TriantaEna extends BlackJack {
    public final static int TENumCards = 2;

    //Constructor method sets the game to Trianta Ena and initializes appropriate objects
    public TriantaEna() {
      super("Trianta Ena");
      super.utils = new Utility();
      super.deck = new Deck();
      super.deck.shuffleDeck();
      super.dealer = new TriantaEnaPlayer("Dealer", true);
      keepPlaying = true;
      this.gameSetup();
    }

    /*
    Overridden playRound method executes the flow needed for Trianta Ena, the order is as follows:
        1. Deal one card to each player and the dealer
        2. Ask each player whether they want to play or fold
        3. Collect bets from players who want to play
        4. Carry out each player's turn
        5. Carry out dealer turn
        6. Determine round winners and losers and complete prize distributions
        7. Check balances and prompt players to become dealer
     */
    @Override
    public void playRound() {
        this.dealCards(1);

        printDealtHands();

        whoIsPlaying();
      
        getBets();

        this.dealCards();

        playerTurns();

        dealerTurn();

        checkHandWins();

        playerStatus();

        rotateDealer();
    }

    //Overridden method used to setup a game with Trianta Ena players
    @Override
    public void gameSetup() {
        maxPlayers = 7;
        super.numPlayers = getNumPlayers();
        while(!checkNumPlayers(super.numPlayers)){
            System.out.println("Incorrect number of players! Please choose between 1-7.");
            super.numPlayers = getNumPlayers();
        }
        super.playerList = new ArrayList<>();
        String playerName;

        for (int i = 0; i < numPlayers; i++) {
            // add player to list init w info needed
            playerName = getPlayerName();
            getPlayerList().add(new TriantaEnaPlayer(playerName, false));
        }
    }

    //Overridden method to deal cards appropriately to the players and dealer during each round
    @Override
    public void dealCards() {
        for (int i = 0; i < TriantaEna.TENumCards; i++) {
            for (Gambler player : getPlayerList()) {
                if(!player.getPlayingRound()) { continue;}
                for (Hand hand : player.getHands()) {
                    hand.addCard(deck.getTopCard(true));
                }
            }
            getDealer().getHands().get(0).addCard(deck.getTopCard(false));
        }
    }

    //Overloaded method used to specify how many cards to deal to each player/dealer
    public void dealCards(int numCards) {
        for (Gambler player_init : getPlayerList()) {
                player_init.addHand(new TEHand());
        }
        getDealer().addHand(new TEHand());
        for (int i = 0; i < numCards; i++) {
            for (Gambler player : getPlayerList()) {
                for (Hand hand : player.getHands()) {
                    hand.addCard(deck.getTopCard(false));
                }
            }
            getDealer().getHands().get(0).addCard(deck.getTopCard(true));
        }
    }

    //Method specific to Trianta Ena carries out logic to allow players to choose to become dealer/banker
    public void rotateDealer(){
      Collections.sort(getPlayerList(), Collections.reverseOrder());
      for (Gambler player: getPlayerList()){
        if( player.getBalance() < getDealer().getBalance()){
          break;
        }
        else{
          boolean decision = getDealerDecision(player.getName());
          if (decision){
            //If the player chooses to be the banker the balances of the player and current banker are swapped
            double tempBalance = player.getBalance();
            player.setBalance(getDealer().getBalance());
            getDealer().setBalance(tempBalance);
            break;
          }
        }
      }
    }

    //Method used to determine which player wants to continue playing the round or fold their hand
    public void whoIsPlaying(){
        for (Gambler player: getPlayerList()){
            boolean decision = getDecision(player.getName());
            if (decision){
                player.setPlayingRound(false);
            }
            else{
                player.setPlayingRound(true);
            }
        }
    }

    //Method prints each player's hand
    public void printDealtHands(){
        for (Gambler player : getPlayerList()) {
            for (Hand hand : player.getHands()) {
                System.out.println(String.format("\n%s's current hand:", player.getName()));
                System.out.println(hand);
                System.out.printf("Dealt Hand value: %s\n", hand.getHandValue());
            }
        }
        for (Hand hand : dealer.getHands()) {
            System.out.println(String.format("\n%s's current hand:", dealer.getName()));
            System.out.println(hand);
            System.out.printf("Dealt Hand value: %s\n", hand.getHandValue());
        }
    }

    public boolean checkNumPlayers(int numPlayers){
        if (numPlayers <= 7 & numPlayers > 0){ return true; }
        else {return false;}
    }

    //Helper method to ask player for input on whether or not to fold card, if player chooses to fold they sit out this round
    public boolean getDecision(String name) {
        return utils.getYesNo(playPrompt(name));
    }

    //Method that returns prompt used to ask player to be Dealer/Banker
    public String beDealerPrompt(String name) {
        return String.format("\nPlayer %s, would you like to be the Dealer/Banker? (Yes or No)", name);
    }
    //Method used to determine whether player wants to become the dealer or not
    public boolean getDealerDecision(String name) {
        return utils.getYesNo(beDealerPrompt(name));
    }
    //Prompt used to ask player if they would like to fold or not
    public String playPrompt(String name) {
        return String.format("\nPlayer %s, would you like to fold? (Yes or No)", name);
    }
}
