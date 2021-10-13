public class Card {
    private String suit;
    //private String rank; Need this???
    private int value;
    private boolean faceUp;

    /* 
     * Card - Constructor class for a card
     */
    public Card(String suit, int value) {
        this.setCardSuit(suit);
        this.setCardValue(value);
    }

    /* 
     * getCardSuit - an accessor method for the Card's suit.
     */
    public String getCardSuit() {
        return this.suit;
    }
    
    /*
     * getCardValue - an accessor method for the Card's value.
     */
    public int getCardValue() {
        return this.value;
    }

    /*
     * getCardFaceUp - an accessor method for if the Card is face up or face down.
     */
    public boolean getCardFaceUp() {
        return this.faceUp;
    }

    /*
     * setCardSuit - a mutator method that changes the Card's suit.
     *
     * precondition: w must be positive
     */
    public void setCardSuit(String suit) {
        //if (suit <= 0 || suit > 10) {
        //    throw new IllegalArgumentException();
        //}

        this.suit = suit;
    }

    /*
     * setHeight - a mutator method that changes the Card's value.
     *
     * precondition: h must be positive
     */
    public void setCardValue(int value) {
        //if (h <= 0 || h > 11) {
        //    throw new IllegalArgumentException();
        //}
        
        this.value = value;
    }

    /*
     * setHeight - a mutator method that changes the if the Card is face up or face down.
     */
    public void setCardFaceUp(boolean isCardFaceUp) {
        //if (h <= 0 || h > 11) {
        //    throw new IllegalArgumentException();
        //}
        
        this.faceUp = isCardFaceUp;
    }

    /*
     * toString - prints the card
     */
    @Override
    public String toString() {
        if (this.getCardFaceUp()) {
            return this.suit + " , " + String.valueOf(this.value);
        } else {
            return "-,-";
        }
    }
}
