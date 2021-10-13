import java.util.*;

public class Utility {
    Scanner scan;

    public Utility(){
        scan = new Scanner(System.in);
    }

    public String getString(String input){
        System.out.println(input);
        String response = scan.nextLine();
        return response;
    }
}