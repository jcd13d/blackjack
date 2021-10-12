public class GameSession extends Utility{

    
    public GameSession(){
        startSession();
    }

    private void startSession(){
        System.out.println("Welcome to Encore!");
        System.out.println("Please select what game you would like to play:");
        System.out.println("0 - Quit");
        System.out.println("1 - Black Jack");
        System.out.println("2 - Trianta Ena");
        String gameSelection = scan.nextLine();
        int gameNumber = Integer.parseInt(gameSelection);

        if (gameNumber == 1) {
            // Start Black Jack game 
            BlackJack BJgame = new BlackJack();

        } else if (gameNumber == 2) {
            // Start Trianta Ena
            TriantaEna TEgame = new TriantaEna();
            

        }

    }
}
