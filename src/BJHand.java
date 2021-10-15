public class BJHand extends Hand{

    public BJHand() {
        super();
        setBustValue(21);
    }
    public BJHand(int bustValue) {
        super();
        setBustValue(bustValue);
    }
    @Override
    public int getHandValue() {
        int handSum = 0;
        int aceCounter = 0;
        for (int i = 0; i < this.hand.size(); i++) {
            Card card = this.hand.get(i);
            int value = card.getCardValue();
            if (value >= 10) {
                value = 10;
            }
            if (value == 1) {
                value = 11;
                aceCounter += 1;
            }
            handSum += value;
        }
        if (aceCounter >= 1 && handSum > getBustValue()) {
            handSum -= 10;
        }
        if (handSum > getBustValue()) {
            this.handValue = 0;
            return this.handValue;
        }
        this.handValue = handSum;
        return this.handValue;
    }
}