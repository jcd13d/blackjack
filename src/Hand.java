import java.util.*;
public abstract class Hand implements Comparable<Hand>{
    public ArrayList<Card> hand;
    public int handValue;
    private int bustValue;

    /* 
     * Deck - Constructor class for a Standard 52 card deck
     */
    public Hand() {
        this.hand = new ArrayList<Card>();
    }

    /* 
     * addCard - receives a Card object and adds it to the hand
     */
    public void addCard(Card newCard) {
        hand.add(newCard);
    }
  
     /* 
     * removeCard - removes a Card object from the hand
     */
    public Card removeCard(int i) {
        return this.hand.remove(i);
    }

    /* 
     * getHand - shows cards in Hand object
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /* 
     * getBustValue - shows cards in Hand object
     */
    public int getBustValue() {
        return this.bustValue;
    }

    /* 
     * setBustValue - shows cards in Hand object
     */
    public void setBustValue(int bustVal) {
        this.bustValue = bustVal;
    }

    /* 
     * getHandValue - shows cards in Hand object
     */
    public abstract int getHandValue();

    /* 
     * setBustValue - shows cards in Hand object
     */
    public void setHandValue(int handVal) {
        this.handValue = handVal;
    }

    /* 
     * printHand - prints out cards in Hand object
     */
    public String toString() {
        String strHand = " ";
        for (Card card : this.hand) {
            strHand.concat(card.toString()) ;
        }
        return strHand;
    }

    /* 
     * compareTo  - comparing hand value to another hand value
     * Overrides compareTo method per Comparable interface
     */
    @Override
    public int compareTo(Hand otherHand) {
        int cur_hand = this.getHandValue();
        int oth_hand = otherHand.getHandValue();
        if (cur_hand > oth_hand) {
            return 1;
        }
        else if (cur_hand < oth_hand) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
