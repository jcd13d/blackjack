public abstract class Game {

    String gameName;

    public Game(String gameName, int numPlayers){
        this.gameName = gameName;
    }

    public abstract void playGame();

    public abstract void endGame();

    public abstract void checkForWin();

}
