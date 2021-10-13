import java.util.*;
public class Hand implements Comparable{
    private ArrayList<Card> hand;
    private int handValue;

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
     * getHand - shows cards in Hand object
     */
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /* 
     * getHandValue - shows cards in Hand object
     */
    public int getHandValue() {
        int handSum = 0;
        int aceCounter = 0;
        int bustValue = 21; // Might need to pass this into method depending on game
        for (int i = 0; i < this.hand.size(); i++) {
            Card card = this.hand.get(i);
            int value = card.getCardValue();
            if (value == 1) {
                value = 11;
                aceCounter += 1;
            }
            if (value >= 10) {
                value = 10;
            }
            handSum += value;
        }
        if (aceCounter >= 1 && handSum > bustValue) {
            handSum -= 10;

        }
        this.handValue = handSum;
        return this.handValue;
    }

    //public String printHand() {
    //
    //}

    //public int compareTo(Hand otherHand) {
    //
    //}

}
