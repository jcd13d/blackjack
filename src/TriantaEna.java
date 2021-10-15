import java.util.Collections;
import java.util.ArrayList;

public class TriantaEna extends BlackJack {
    public final static int TENumCards = 2;

    public TriantaEna() {
      super("Trianta Ena");
      utils = new Utility();
      deck = new Deck();
      deck.shuffleDeck();
      super.dealer = new TriantaEnaPlayer("Dealer", true);
      keepPlaying = true;
      gameSetup();

    }

    @Override
    public void playRound() {
      
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
        String playerName;
        double initBalance;

        for (int i = 0; i < numPlayers; i++) {
            // add player to list init w info needed
            playerName = getPlayerName();
            getPlayerList().add(new TriantaEnaPlayer(playerName, false));
        }
    }

    @Override
    public void dealCards() {
        for (Gambler player_init : getPlayerList()) {
            if (player_init.getBet() != 0) {
                player_init.addHand(new TEHand());
            }
        }

        getDealer().addHand(new TEHand());

        for (int i = 0; i < TriantaEna.TENumCards; i++) {
            for (Gambler player : getPlayerList()) {
                for (Hand hand : player.getHands()) {
                    hand.addCard(deck.getTopCard(true));
                }
            }
            getDealer().getHands().get(0).addCard(deck.getTopCard(i % 2 != 0));
        }
    }

    public void rotateDealer(){
      Collections.sort(getPlayerList(), Collections.reverseOrder());
      for (Gambler player: getPlayerList()){
        if( player.getBalance() < getDealer().getBalance()){
          break;
        }
        else{
          boolean decision = getDecision(player.getName());
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

    public boolean getDecision(String name) {
        return utils.getYesNo(beDealerPrompt(name));
    }

    public String beDealerPrompt(String name) {
        return String.format("\nPlayer %s, would you like to be the Banker? (Yes or No)", name);
    }
}
