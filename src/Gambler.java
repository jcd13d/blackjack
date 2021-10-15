//Gambler interface used for force implementation of methods needed for betting card games
public abstract class Gambler extends Player implements Comparable<Gambler>{

  public Gambler(String name){
      super(name);
  }

  public Gambler(String name, boolean dealer){
    super(name, dealer);
  }
  //Method to place a bet to be used by a player
  public abstract void placeBet();

  //Method to get a bet to be placed by a player
  public abstract double getBet();

  //Method to set the players balance
  public abstract void setBalance(double balance);

  public abstract double getBalance();

  public abstract void incrementBalance(double inc);

  public abstract void getPlayerMove(Deck deck);

  public abstract int compareTo(Gambler otherPlayer);

  }
