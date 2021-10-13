import java.util.*;

public class TriantaEnaPlayer extends Player implements Gambler{
  public static int dealerStaysOn = 27;
  private double balance;
  private double bet;

  public void TriantaEnaPlayer(String name){
    super(name);
    this.balance = 100.0;
    this.bet = 0;
  }
  public void TriantaEnaPlayer(String name, boolean dealer){
    super(name, dealer);
    if(dealer){
      this.balance = 300.0;
    }
    else{
      this.balance = 100;
    }
    this.bet = 0;
  }

  public void getPlayerMove(Deck deck){
    if (!super.dealer){
      for (int i = 0; i < super.hands.size(); i++) {
        String move = Utility.getString(getMovePrompt());
        while (!checkMove(move)){
          move = Utility.getString(getMovePrompt());
        }
        if (move.equalsIgnoreCase("Stand")) {
          stand();
        } else if (move.equalsIgnoreCase("Hit")) {
          hit(deck, super.hands.get(i));
          i--;    // Go again
        }
      }
    }
    else{
      for(Hand currentHand : super.hands){
        while(currentHand.getHandValue() < this.dealerStaysOn){
          hit(deck, currentHand);
        }
        stand();
      }
    }
  }
  private void stand() {
         System.out.println(getStayPrompt());
     }
  private void hit(Deck deck, Hand hand) {
      // draw a card from the deck and add to hand
      hand.addCard(deck.getTopCard());
  }

  //Prompt for the bet
  //Place the bet into this player's bet value
  //Decrement the balance available to make bets
  public void placeBet() {
    double betAttempt = (double) Utility.getString(getBetPrompt());
    while (!checkBet(betAttempt)){
      betAttempt = (double) Utility.getString(getBetPrompt());
      }
      decrementBalance(betAttempt);
      this.bet = this.bet + betAttempt;
    }
  public double getBet(){
    return this.bet;
  }

  public void decrementBalance(double dec) {
    this.balance = this.balance - dec;
  }

  public void setBalance(double balance){
    this.balance = balance;
  }
  public void clearHand(){
    super.hands = new ArrayList<Hand>();
    this.bet = 0.0;
  }
  public void endHand(boolean winner){
    if(winner){
      setBalance(this.balance + 2.0*this.bet);
    }
  }

  public String getMovePrompt() {
      return "What move would you like to make? Type: 'Stand' or 'Hit'";
  }
  private String getBetPrompt() {
      return String.format("How much would you like to bet? Enter value between 0-%d", this.balance) ;
  }
  private String getStayPrompt() {
      String prompt;
      prompt = String.format("%s stays!", super.name);
      return prompt;
  }
  private String invalidMovePrompt() {
      return "Invalid Move!!";
  }
  private boolean checkBet(double betAttempt){
    if (betAttempt < 0 || betAttempt > this.balance){
      return false;
    }
    else{ return true;}
  }
  private boolean checkMove(String move){
    if (move.equalsIgnoreCase("Hit") || move.equalsIgnoreCase("Stand")){
      return true;
    }
    else{
      System.out.println(invalidMovePrompt());
      return false;
    }
  }
}
