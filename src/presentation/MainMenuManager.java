package presentation;

import java.util.Scanner;

public class MainMenuManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("➢ ===== MAIN MENU =====");
            System.out.println("1. GENERAL");
            System.out.println("2. ADMIN");
            System.out.println("3. USER");
            System.out.println("4. EXIT");
            System.out.println("Your choice: ");
            int choose;
            try {
                choose = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Try again.");
                continue;
            }
            switch (choose) {
                case 1:
                    GeneralMenu.generalMenu(scanner);
                    break;
                case 2:
                    AdminMenu.adminMenu(scanner);
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
}
