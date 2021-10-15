Team Members:

Shilpen Patel
shilpenp@bu.edu
U52493692

Matthew Gilgo
msgilgo@bu.edu
U43413276

Justin DiEmmanuele
jd9@bu.edu
U64306920

George Padavick
georgep4@bu.edu
U47019144

---------------------------------------------------------------------------------------------------------------------------------

BlackJack README.txt

Compiler Instructions:
javac *.java

Execution Instructions:
java Main

Class Descriptions:
Main - executes program
Game - abstract class that contains name of game and methods that enforce gameplay
Game Session - provides main menu for user to select game (Blackjack or Trianta Ena)
Player - abstract class that contains attributes associated with a general game player
Gambler - abstract class that contains attributes associated with a card game player that bets
BJPlayer - extends Gambler, provides attributes for player needing to play Blackjack
TriantaEnaPlayer - extends BJPlayer, provides attributes for player needing to play Trianta Ena
Hand - abstract class that implements Comparable<>, contains attributes for a Player's hand and contains compareTo() method
BJHand - extends Hand, provides attributes for a Hand in Blackjack
TEHand - extends BJHand, provides attributes for a Hand in Trianta Ena
Deck - class to implement a Deck object that contains ArrayList of Cards
Card - class to create a Card Object with a suit, rank, and value associated with the Card
Blackjack - extends Game, provides logic to play game of Blackjack
TriantaEna - extends Blackjack, provides logic to play game of Trianta Ena
Utility -  class contain all error checking for scanner inputs











