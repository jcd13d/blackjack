public abstract class Game {
    
    String gameName;
    int numPlayers;

    public Game(String gameName, int numPlayers){
        this.gameName = gameName;
        this.numPlayers = numPlayers;
    }

    public abstract void playGame();

    public abstract void endGame();

    public abstract void checkForWin();

}
