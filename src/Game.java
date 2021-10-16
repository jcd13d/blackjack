public abstract class Game {

    String gameName;

    public Game(String gameName){
        this.gameName = gameName;
    }

    public abstract void playGame();
}
