import java.util.ArrayList;
import java.util.*;

public class BJPlayer extends Player implements Gambler {
    public static int dealerStaysOn = 17;
    private double balance;
    private double bet;

    public BJPlayer(String name, boolean dealer, double initBalance) {
        super(name, dealer);
        balance = initBalance;
        bet = 0;
    }

    public void getPlayerMove(Deck deck) {
        String input;
        if (!super.isDealer()) {
            for (int i = 0; i < hands.size(); i++) {
                // ask for input
                // based on input do conditional logic to call stay hit or split
                input = utils.getString(getMovePrompt());

                if (input.equalsIgnoreCase("Stand")) {
                    stand();
                    // doesnt go again
                } else if (input.equalsIgnoreCase("Hit")) {
                    hit(deck, hands.get(i));
                    i--;    // Go again
                } else if (input.equalsIgnoreCase("Split")){
                    split(deck, hands.get(i));
                    i--;    // Go again
                } else if (input.equalsIgnoreCase("Double up")) {
                    doubleUp(deck, hands.get(i));
                    // doesnt go again
                } else {
                    //invalid input
                    System.out.println(invalidMovePrompt());
                    i--;    // go again
                }

            }
        } else {
            // dealer move
            for (Hand hand : hands) {
                dealerLogic(deck, hand);
            }
        }
    }

    private void dealerLogic(Deck deck, Hand hand) {
        while (hand.getHandValue() < BJPlayer.dealerStaysOn) {
            hit(deck, hand);
        }
    }

    public void stand() {
        System.out.println(getStayPrompt());
    }

    public void hit(Deck deck, Hand hand) {
        // draw from deck add to hand
        hand.addCard(deck.getTopCard());
    }

    private void split(Deck deck, Hand hand) {
        // TODO check that there are two cards in hand - no more no less

        // decrease bank by bet amount
        decrementBalance(getBet());
        // append new hand to list
        addHand(new Hand());
        // remove second card from first hand, add to last hand
        hands.get(hands.size() - 1).addCard(hand.removeCard(1));
        // add card to first hand
        hit(deck, hand);
        // add card to last hand
        hit(deck, hands.get(hands.size() - 1));
    }

    private void doubleUp(Deck deck, Hand hand) {
        // double bet
        placeBet(getBet());
        // then hit
        hit(deck, hand);
        // then stand prompt
        stand();
    }

    public double getBet() {
        return bet;
    }

    //Prompt for the bet
    //Place the bet into this player's bet value
    //Decrement the balance available to make bets
    public void placeBet() {
      double betAttempt = Double.parseDouble(utils.getString(getBetPrompt()));
      while (!checkBet(betAttempt)){
        betAttempt = Double.parseDouble(utils.getString(getBetPrompt()));
        }
        decrementBalance(betAttempt);
        this.bet = this.bet + betAttempt;
      }
    public void placeBet(double betAttempt) {
      decrementBalance(betAttempt);
      this.bet = this.bet + betAttempt;
    }
    public void decrementBalance(double dec) {
        // TODO Handle error for balance less than zero
        this.balance = this.balance - dec;
    }

    public void incrementBalance(double inc) {
        this.balance = this.balance + inc;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setBet(double bet) {
        this.bet = bet;
    }
    public void endHand(boolean winner) {
        // think about if there are multiple hands, how do we handle?
        // Maybe we need to use separate method to reset bet to zero
        if (winner) {
            incrementBalance(2.0*getBet());
        }
    }

    public void clearHand() {
        hands = new ArrayList<Hand>();
        bet = 0;
    }

    public String getMovePrompt() {
        return "What move would you like to make? Type: 'Stand', 'Hit', 'Split', or 'Double Up'";
    }

    public String getStayPrompt() {
        String prompt;
        prompt = String.format("%s stays!", this.name);
        return prompt;
    }

    public String getBetPrompt() {
        return String.format("How much would you like to bet? Enter value between 0-%d", this.balance) ;
    }

    public String invalidMovePrompt() {
        return "Invalid Move!!";
    }
    public boolean checkBet(double betAttempt){
      if (betAttempt < 0 || betAttempt > this.balance){
        return false;
      }
      else{ return true;}
    }

    public boolean checkMove(String move){
      if (move.equalsIgnoreCase("Hit") || move.equalsIgnoreCase("Stand") || move.equalsIgnoreCase("Double Up") ||move.equalsIgnoreCase("Split")){
        return true;
      }
      else{
        System.out.println(invalidMovePrompt());
        return false;
      }
    }
}
