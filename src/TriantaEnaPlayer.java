//Trianta Ena Player class extends the BlackJack Player class and override appropriate methods to allow proper Trianta Ena gameplay
public class TriantaEnaPlayer extends BJPlayer{
  //Value used to set dealer stratgey
  private static int dealerStaysOn = 27;

  //Constructor for Trianta Ena Player
  public TriantaEnaPlayer(String name, boolean dealer){
    super(name, dealer, 100.0);
    if(dealer){
      setBalance(300.0);
      setPlayingRound(true);
    }
  }

  //Overriden getPlayerMove method, gives a Trianta Ena player the option to Stand or Hit
  @Override
  public void getPlayerMove(Deck deck){
    if (!super.isDealer() && super.getPlayingRound()){
      for (int i = 0; i < getHands().size(); i++) {
        System.out.println(String.format("\n%s's current hand: ", super.name));
        getHands().get(i).getHand().get(0).setCardFaceUp(true);
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
          if(getHands().get(i).getHandValue() == 0){
            System.out.println(String.format("\nPlayer %s Busted!: ", super.name));
            break;
          }
          i--;    // Go again
        }
      }
    }
    else if(super.isDealer()){
      // dealer move
      System.out.println("\nDealer's move: ");
      for (Hand hand : getHands()) {
        for(Card card : hand.getHand()){
          card.setCardFaceUp(true);
        }
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

  //Method to used to execute the dealer strategy
  private void dealerLogic(Deck deck, Hand hand) {
    while ((hand.getHandValue() < TriantaEnaPlayer.dealerStaysOn) & hand.getHandValue() != 0) {
      hit(deck, hand);
      System.out.println("Dealer Hits!");
      System.out.println(hand);
    }
  }

  //Overridden method adjust the BlackJAck player prompt for Trianta Ena
  @Override
  public String getMovePrompt() {
      return "What move would you like to make? Type: 'Stand' or 'Hit'";
  }

  //Validates the move made by the player for Trianta Ena
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

  //Compares players by their balance, used to order players accordingly
  @Override
  public int compareTo(Gambler otherPlayer) {
      return Double.compare(this.getBalance(), otherPlayer.getBalance());
  }
}
