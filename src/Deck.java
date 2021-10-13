import java.util.*;

public class Deck{

    private ArrayList<Card> deck;
    private String[] suits = {"Clubs","Spades","Hearts","Diamonds"};

    /* 
     * Deck - Constructor class for a Standard 52 card deck
     */
    public Deck() {
        createStandardDeck();
    }

    /* 
     * Deck - Constructor class for multiple Standard 52 card decks in one deck
     */
    public Deck(int numDecks) {
        this.deck = new ArrayList<Card>();
        for (int n = 0; n < numDecks; n++) {
            for (String suit : suits) {
                for (int j = 1; j < 14; j++) {
                    deck.add(new Card(suit,j));
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
        for (String suit : suits) {
            for (int j = 1; j < 14; j++) {
                deck.add(new Card(suit,j));
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
