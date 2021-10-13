public class BJHand extends Hand{
    private int bustValue = 21;
    @Override
    public int getHandValue() {
        int handSum = 0;
        int aceCounter = 0;
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
        if (handSum > 21) {
            this.handValue = 0;
            return this.handValue;
        }
        this.handValue = handSum;
        return this.handValue;
    }
}