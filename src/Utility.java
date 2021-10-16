import java.util.*;

/*
Utility class contains common methods used in other classes for retrieving output from the user. 
Each method returns a specific type after prompting the user with the prompt in the argument. 
*/

public class Utility {
    Scanner scan;

    public Utility(){
        scan = new Scanner(System.in);
    }

    public String getString(String prompt){
        System.out.println(prompt);
        return scan.nextLine();
    }

    // Returns a double - checks that the value entered is numeric
    public double getDouble(String prompt) {
        System.out.println(prompt);
        try {
            return Double.parseDouble(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value");
            return getDouble(prompt);
        }
        
    }

    // Returns an int - checks that the value entered is numeric
    public int getInt(String prompt){
        System.out.println(prompt);
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value");
            return getInt(prompt);
        }
    }

    // Returns a boolean based on a yes or no prompt
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