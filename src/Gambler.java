import java.util.ArrayList;

//Gambler interface used for force implementation of methods needed for betting card games
public abstract class Gambler extends Player implements Comparable<Gambler>{
  ArrayList<Hand> hands;
  private boolean dealer;

  public Gambler(String name){
    super(name);
  }

  public Gambler(String name, boolean dealer){
    super(name);
    hands = new ArrayList<Hand>();
    this.dealer = dealer;
  }
  //Method to place a bet to be used by a player
  public abstract void placeBet();

  //Method to get a bet to be placed by a player
  public abstract double getBet();

  public abstract void setBet(double bet);

  //Method to set the players balance
  public abstract void setBalance(double balance);

  public abstract double getBalance();

  public abstract void incrementBalance(double inc);

  public abstract void getPlayerMove(Deck deck);

  public abstract void setPlayingRound(boolean b);

  public abstract boolean getPlayingRound();

  public abstract int compareTo(Gambler otherPlayer);

  public boolean isDealer() {
    return this.dealer;
  }

  public void setDealer(boolean dealer) {
    this.dealer = dealer;
  }

  public void addHand(Hand hand) {
    hands.add(hand);
  }

  public void resetHands() {
    hands = new ArrayList<>();
  }

  public ArrayList<Hand> getHands() {
    return hands;
  }

  }
