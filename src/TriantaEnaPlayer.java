public class TriantaEnaPlayer extends BJPlayer{
  private static int dealerStaysOn = 27;

  public TriantaEnaPlayer(String name, boolean dealer){
    super(name, dealer, 100.0);
    if(dealer){
      setBalance(300.0);
    }
  }
  @Override
  public void getPlayerMove(Deck deck){
    if (!super.isDealer()){
      for (int i = 0; i < getHands().size(); i++) {
        System.out.println(String.format("\n%s's current hand: ", super.name));
        System.out.println(getHands().get(i));
        System.out.printf("Hand Value: %s\n", getHands().get(i).getHandValue());
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
      // dealer move
      System.out.println("\nDealer's move: ");
      System.out.println("\nDealer's move: ");
      for (Hand hand : getHands()) {
        System.out.println(String.format("%s's current hand: ", this.name));
        System.out.println(hand);
        this.dealerLogic(deck, hand);
      }
      getHands().get(0).getHand().get(0).setCardFaceUp(true);
      System.out.println("Reveal!");
      System.out.println(getHands().get(0).getHand());
      System.out.printf("Dealer hand value: %s\n", getHands().get(0).getHandValue());
    }
  }

  private void dealerLogic(Deck deck, Hand hand) {
    while ((hand.getHandValue() < TriantaEnaPlayer.dealerStaysOn) & hand.getHandValue() != 0) {
      hit(deck, hand);
      System.out.println("Dealer Hits!");
      System.out.println(hand);
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
  @Override
  public int compareTo(Gambler otherPlayer) {
      return Double.compare(this.getBalance(), otherPlayer.getBalance());
  }
}
