interface Gambler{

  //Method to place a bet to be used by a player
  void placeBet(int bet);

  //Method to get a bet to be placed by a player
  double getBet();

  //Method to set the players balance
  void setBalance();

  //Method to end a turn of a player's hand
  void endHand(boolean winner);

}
