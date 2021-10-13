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

    public boolean getYesNo(String prompt) {
        System.out.println(prompt);
        String response = scan.nextLine().toLowerCase();
        if (response.equals("yes")) {
            return true;
        } else if (response.equals("no")) {
            return false;
        } else {
            System.out.println("Please enter either 'Yes' or 'No'. ");
            return getYesNo(prompt);
        }
    }
}