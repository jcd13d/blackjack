import java.util.Collections;
import java.util.ArrayList;

public class TriantaEna extends BlackJack {
    public final static int TENumCards = 2;

    public TriantaEna() {
      super("Trianta Ena");
      super.utils = new Utility();
      super.deck = new Deck();
      super.deck.shuffleDeck();
      super.dealer = new TriantaEnaPlayer("Dealer", true);
      keepPlaying = true;
      this.gameSetup();
    }

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

    @Override
    public void gameSetup() {
        super.openingPrompts();
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

    public boolean getDecision(String name) {
        return utils.getYesNo(playPrompt(name));
    }

    public String beDealerPrompt(String name) {
        return String.format("\nPlayer %s, would you like to be the Banker? (Yes or No)", name);
    }
    public boolean getDealerDecision(String name) {
        return utils.getYesNo(beDealerPrompt(name));
    }

    public String playPrompt(String name) {
        return String.format("\nPlayer %s, would you like to fold? (Yes or No)", name);
    }
}
