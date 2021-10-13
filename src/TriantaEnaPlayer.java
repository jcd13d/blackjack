import java.util.*;

public class TriantaEnaPlayer extends BJPlayer {
  private static int dealerStaysOn = 27;

  public void TriantaEnaPlayer(String name, boolean dealer){
    if(dealer){
      super(name, dealer, 300.0);
    }
    else{
      super(name, dealer, 100.0);
    }
  }

  @Override
  public void getPlayerMove(Deck deck){
    if (!super.dealer){
      for (int i = 0; i < getHands().size(); i++) {
        String move = Utility.getString(this.getMovePrompt());
        while (!checkMove(move)){
          move = Utility.getString(this.getMovePrompt());
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
        while(currentHand.getHandValue() < this.dealerStaysOn){
          super.hit(deck, currentHand);
        }
        super.stand();
      }
    }
  }

  @Override
  private String getMovePrompt() {
      return "What move would you like to make? Type: 'Stand' or 'Hit'";
  }

  @Override
  private boolean checkMove(String move){
    if (move.equalsIgnoreCase("Hit") || move.equalsIgnoreCase("Stand")){
      return true;
    }
    else{
      System.out.println(super.invalidMovePrompt());
      return false;
    }
  }
}
