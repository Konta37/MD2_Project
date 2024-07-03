import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String input = sc.nextLine();

        if (isValidInput(input)) {
            System.out.println("Valid input.");
        } else {
            System.err.println("Invalid input (should not contain special characters).");
        }
    }

    public static boolean isValidInput(String input) {
        // Regex pattern to disallow special characters
        String regex = "^[a-zA-Z0-9 ]+$";  // Allows alphanumeric characters and spaces
        return input.matches(regex);
    }
}
