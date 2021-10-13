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
        try {
            return Double.parseDouble(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value");
            return getDouble(prompt);
        }
        
    }

    public int getInt(String prompt){
        System.out.println(prompt);
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value");
            return getInt(prompt);
        }
    }

    public boolean getYesNo(String prompt) {
        System.out.println(prompt);
        String response = scan.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            return true;
        } else if (response.equalsIgnoreCase("no")) {
            return false;
        } else {
            System.out.println("Please enter either 'Yes' or 'No'. ");
            return getYesNo(prompt);
        }
    }
}