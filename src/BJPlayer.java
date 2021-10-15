import java.util.ArrayList;

public class BJPlayer extends Gambler {
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
                System.out.println(String.format("\n%s's current hand: ", this.name));
                System.out.println(hands.get(i));
                System.out.printf("Hand Value: %s\n", hands.get(i).getHandValue());

                if (earlyHandStopCheck(i, deck)) {
                    break; // does this break the for loop?
                }

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
            System.out.println("\nDealer's move: ");
            for (Hand hand : hands) {
                System.out.println(String.format("%s's current hand: ", this.name));
                System.out.println(hand);
                dealerLogic(deck, hand);
            }
            hands.get(0).getHand().get(0).setCardFaceUp(true);
            System.out.println("Reveal!");
            System.out.println(hands.get(0).getHand());
            System.out.printf("Dealer hand value: %s\n", hands.get(0).getHandValue());
        }
    }

    private boolean earlyHandStopCheck(int handIndex, Deck deck) {
        boolean earlyStop = false;
        if (hands.get(handIndex).getHandValue() == 0) {
            // bust
            System.out.println(String.format("\n%s busted!", this.getName()));
            System.out.println(hands.get(handIndex));
            deck.recycleCards(hands.get(handIndex).getHand());
            hands.remove(handIndex);
            earlyStop = true;
        } else if ((hands.get(handIndex).getHandValue() == hands.get(handIndex).getBustValue()) &
                (hands.get(handIndex).getHand().size() == 2)){
            // blackjack
            System.out.println(String.format("\n%s has blackjack!", this.getName()));
            System.out.println(hands.get(handIndex));
            this.incrementBalance(2 * this.getBet());
            deck.recycleCards(hands.get(handIndex).getHand());
            hands.remove(handIndex);
            earlyStop = true;
        }
        return earlyStop;
    }

    private void dealerLogic(Deck deck, Hand hand) {
        while ((hand.getHandValue() < BJPlayer.dealerStaysOn) & hand.getHandValue() != 0) {
            hit(deck, hand);
            System.out.println("Dealer Hits!");
            System.out.println(hand);
        }
    }

    public void stand() {
        System.out.println(getStayPrompt());
    }

    public void hit(Deck deck, Hand hand) {
        // draw from deck add to hand
        hand.addCard(deck.getTopCard(true));
    }

    private void split(Deck deck, Hand hand) {
        // TODO check that there are two cards in hand - no more no less

        // decrease bank by bet amount
        decrementBalance(getBet());
        // append new hand to list
        addHand(new BJHand());
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
    public double getBalance(){
      return this.balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void incrementBalance(double inc) {
        this.balance = this.balance + inc;
    }

    //Prompt for the bet
    //Place the bet into this player's bet value
    //Decrement the balance available to make bets
    public void placeBet() {
      double betAttempt = utils.getDouble(getBetPrompt());
      while (!checkBet(betAttempt)){
        betAttempt = utils.getDouble(getBetPrompt());
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

    public void clearHand() {
        hands = new ArrayList<Hand>();
        bet = 0;
    }

    public String getMovePrompt() {
        return "\nWhat move would you like to make? Type: 'Stand', 'Hit', 'Split', or 'Double Up'";
    }

    public String getStayPrompt() {
        String prompt;
        prompt = String.format("%s stays!", this.name);
        return prompt;
    }

    public String getBetPrompt() {
        return String.format("\n%s, How much would you like to bet? Enter value between 0-%.2f", this.name, this.balance) ;
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

    @Override
    public int compareTo(Gambler otherPlayer) {
        return Double.compare(this.getBalance(), otherPlayer.getBalance());
    }

    @Override
    public String toString() {
        return String.format("Player: %s, Balance: %s", name, balance);
    }
}
