//Gambler interface used for force implementation of methods needed for betting card games
interface Gambler{

  //Method to place a bet to be used by a player
  void placeBet(double bet);

  //Method to get a bet to be placed by a player
  double getBet();

  //Method to set the players balance
  void setBalance(double balance);

  void incrementBalance(double inc);

  }
