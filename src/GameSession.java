public class GameSession extends Utility{

    
    public GameSession(){
    }

    public void startSession(){
        System.out.println("Welcome to Encore!");
        System.out.println("Please select what game you would like to play:");
        System.out.println("0 - Quit");
        System.out.println("1 - Black Jack");
        System.out.println("2 - Trianta Ena");
        String gameSelection = super.getString("");
        int gameNumber = 0;

        try {
            gameNumber = Integer.parseInt(gameSelection);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a 0, 1, or 2 to select an option.");
            startSession();
        }

        if (gameNumber == 1) {
            // Start Black Jack game 
            BlackJack BJgame = new BlackJack();
            BJgame.playGame();

        } else if (gameNumber == 2) {
            // Start Trianta Ena
            TriantaEna TEgame = new TriantaEna();
            TEgame.playGame();
 
        } else if (gameNumber < 0 | gameNumber > 2) {
            System.out.println("Please enter a 0, 1, or 2 to select an option.");
            startSession();
        }


    }
}
