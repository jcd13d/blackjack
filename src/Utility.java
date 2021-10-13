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
        double returnDouble = Double.parseDouble(scan.nextLine());
        return returnDouble;
    }

    public int getInt(String prompt){
        System.out.println(prompt);
        int returnInt = Integer.parseInt(scan.nextLine());
        return returnInt; 
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