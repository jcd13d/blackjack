public class TriantaEnaPlayer extends BJPlayer {
  private static int dealerStaysOn = 27;

  public TriantaEnaPlayer(String name, boolean dealer){
    super(name, dealer, 100.0);
    if(dealer){
      setBalance(300.0);
    }
  }
  @Override
  public void getPlayerMove(Deck deck){
    if (!isDealer()){
      for (int i = 0; i < getHands().size(); i++) {
        String move = utils.getString(this.getMovePrompt());
        while (!checkMove(move)){
          move = utils.getString(this.getMovePrompt());
        }
        if (move.equalsIgnoreCase("Stand")) {
          super.stand();
        } else if (move.equalsIgnoreCase("Hit")) {
          super.hit(deck, getHands().get(i));
          i--;    // Go again
        }
      }
    }
    else{
      for(Hand currentHand : getHands()){
        while(currentHand.getHandValue() < TriantaEnaPlayer.dealerStaysOn){
          super.hit(deck, currentHand);
        }
        super.stand();
      }
    }
  }

  @Override
  public String getMovePrompt() {
      return "What move would you like to make? Type: 'Stand' or 'Hit'";
  }

  @Override
  public boolean checkMove(String move){
    if (move.equalsIgnoreCase("Hit") || move.equalsIgnoreCase("Stand")){
      return true;
    }
    else{
      System.out.println(super.invalidMovePrompt());
      return false;
    }
  }
}
