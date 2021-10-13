import java.util.*;

public class Deck{

    private ArrayList<Card> deck;
    private String[] suits = {"Clubs","Spades","Hearts","Diamonds"};
    private HashMap<Integer, String> valueToRank;

    /* 
     * Deck - Constructor class for a Standard 52 card deck
     */
    public Deck() {
        createStandardDeck();
    }

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /* 
     * Deck - Constructor class for multiple Standard 52 card decks in one deck
     */
    public Deck(int numDecks) {
        this.deck = new ArrayList<Card>();
        // Add keys and values (value, rank)
        this.valueToRank.put(1, "Ace");
        this.valueToRank.put(2, "2");
        this.valueToRank.put(3, "3");
        this.valueToRank.put(4, "4");
        this.valueToRank.put(5, "5");
        this.valueToRank.put(6, "6");
        this.valueToRank.put(7, "7");
        this.valueToRank.put(8, "8");
        this.valueToRank.put(9, "9");
        this.valueToRank.put(10, "10");
        this.valueToRank.put(11, "Jack");
        this.valueToRank.put(12, "Queen");
        this.valueToRank.put(13, "King");
        for (int n = 0; n < numDecks; n++) {
            for (String suit : suits) {
                for (int j = 1; j < 14; j++) {
                    Card c = new Card(suit,j);
                    String rank = valueToRank.get(j);
                    c.setCardRank(rank);
                    deck.add(c);
                }
            }
        }
    }

    /*
     * createStandardDeck - creates a standard deck of cards and stores them in 
     * the class ArrayList variable deck
     */
    public void createStandardDeck(){
        this.deck = new ArrayList<Card>();
        this.valueToRank = new HashMap<Integer, String>();

        // Add keys and values (value, rank)
        this.valueToRank.put(1, "Ace");
        this.valueToRank.put(2, "2");
        this.valueToRank.put(3, "3");
        this.valueToRank.put(4, "4");
        this.valueToRank.put(5, "5");
        this.valueToRank.put(6, "6");
        this.valueToRank.put(7, "7");
        this.valueToRank.put(8, "8");
        this.valueToRank.put(9, "9");
        this.valueToRank.put(10, "10");
        this.valueToRank.put(11, "Jack");
        this.valueToRank.put(12, "Queen");
        this.valueToRank.put(13, "King");
        for (String suit : suits) {
            for (int j = 1; j < 14; j++) {
                Card c = new Card(suit,j);
                String rank = valueToRank.get(j);
                c.setCardRank(rank);
                deck.add(c);
            }
        }
    }

    /*
     * shuffleDeck - class to shuffle object of type Deck
     */
    public void shuffleDeck() {
        Collections.shuffle(this.deck);
    }

    /*
     * getTopCard - class to get the Top Card from the deck
     */
    public Card getTopCard() {
        Card topCard = this.deck.get(0);
        this.deck.remove(0);
        return topCard;
    }
  
     /*
     * getTopCard - class to get the Top Card from the deck
     */
    public Card getTopCard(boolean faceUp) {
        Card topCard = this.deck.get(0);
        this.deck.remove(0);
        topCard.setCardFaceUp(faceUp);
        return topCard;
    } 
}
