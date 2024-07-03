package presentation;

import java.util.Scanner;

public class GeneralMenu {
    public static void generalMenu(Scanner sc){
        do {
            System.out.println("➢ ===== GENERAL MENU =====");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.println("6. ");
            System.out.println("7. EXIT");
            System.out.println("Your choice: ");
            int choose;
            try {
                choose = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Try again.");
                continue;
            }
            switch (choose) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
}
