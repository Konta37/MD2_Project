package presentation;

import java.util.Scanner;

public class UserMenu {
    public static void userMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER MENU =====");
            System.out.println("1. Add number of Categories");
            System.out.println("2. Show all Categories");
            System.out.println("3. Edit Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Search Category by ID");
            System.out.println("6. Add number of Products");
            System.out.println("7. Show all Products");
            System.out.println("8. EXIT");
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
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
}
