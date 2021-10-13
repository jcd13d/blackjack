import java.util.*;
public class Hand implements Comparable<Hand>{
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
        this.hand.add(newCard);
    }

    /* 
     * removeCard - removes a Card object from the hand
     */
    public void removeCard(int i) {
        this.hand.remove(i);
    }

    /* 
     * getHand - gets cards in Hand object
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

    /* 
     * printHand - prints out cards in Hand object
     */
    public String printHand() {
        return this.hand.toString();
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
