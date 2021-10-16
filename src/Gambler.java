import java.util.ArrayList;

//Gambler interface used for force implementation of methods needed for betting card games
public abstract class Gambler extends Player implements Comparable<Gambler>{
  ArrayList<Hand> hands;
  private boolean dealer;

  //Constructor of a Gambler type Object by calling the Player constructor
  public Gambler(String name){
    super(name);
  }

  //Overloaded constructor that is used to set which player is the dealer
  public Gambler(String name, boolean dealer){
    super(name);
    hands = new ArrayList<Hand>();
    this.dealer = dealer;
  }

  //Method to place a bet to be used by a player
  public abstract void placeBet();

  //Method to get a bet to be placed by a player
  public abstract double getBet();

  //Method to set a bet to be placed by a player
  public abstract void setBet(double bet);

  //Method to set the players balance
  public abstract void setBalance(double balance);

  //Method to get the current bank balance of a player
  public abstract double getBalance();

  //Method to add to the balance of a player
  public abstract void incrementBalance(double inc);

  //Method to get the move the player wants to make depending on the type of player
  public abstract void getPlayerMove(Deck deck);

  //Setter method for indicting the player is playing that round
  public abstract void setPlayingRound(boolean b);

  //Getter method for finding if the player wants to play or not
  public abstract boolean getPlayingRound();

  //Method needed to implement the comparable interface
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
