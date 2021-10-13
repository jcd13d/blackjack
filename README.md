# TODO
* justin prints in blackjack
  * tostring in player to see their state at end
  * winners etc
  * loops in blackjack
* matt
  * prints in hand make prettier 
  * add rank vs value for printing
  * hand val debug
* shilpen 
  * player input loops
  * Start TE
* George 
  * loops error checking in utils 


# blackjack
Terminal Black Jack game


# BlackJack Logic Pseudocode 

Game Setup Method
* Add all players
  * dont include dealer
* Deck object init in BJ class
* Who is dealer
  * separate variable
* Initialize money

##**Everything below is in play method**

### Bets
**For Each Player**
**For each hand in player**
* Prompt for bet
  * Player class handles accounting for bet/win hand payout etc
  * BJPlayer implements Gambler

### Deal - method happens in BJ class
* 1 to each player until all players have 2 cards in their hand
* Use deck draw card to give each player cards in each hand they have
* no dealer, dealer is just a player named in BJ class 

### Turn Logic
**For each player**
**For each hand in player**
* Player.playerMove(dealer=False)
  * Prompts for hit stand split
    * stand: simple, turn ends
    * hit: semi simple, add cards to hand 
    * split: add hand, remove card from first hand add to second, 
      deal addl card to each hand, resume play as normal
      * dec player bank by amount they orig bet
  * calls other methods if needed for each of those

  * Dealer does their move, shows card
    * dealer.playerMove(dealer=True)
  * go through and pay out each hand based on dealer end value
    * check win condition for each player for each hand
    * for each hand
      * win condition based on hand.value()
      * do logic based on if you win or not
        * player.endHand(win/lose)



  
# Split
* 2 people on card related classes (Card, Deck, Hand, Utility, Main, StartGame, Game Abs)
  * Matt, George
* 2 people work on player related classes (Player abs, BJ Player, TEPlayer, Gambler Interface)
  * Shilpen, Justin
    * Shilpen: TEPlayer, Gambler Interface
    * Justin: Player abs, BJ player
* Then we move to game logic

# Deliverables
* Each person's class assigned
  * with "unit tests" in main function of that class


















