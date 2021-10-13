import java.util.*;

public class Utility {
    Scanner scan;

    public Utility(){
        scan = new Scanner(System.in);
    }

    public String getString(String prompt){
        System.out.println(prompt);
        return scan.nextLine();
    }

    public double getDouble(String prompt) {
        System.out.println(prompt);
        return scan.nextDouble();
    }
}