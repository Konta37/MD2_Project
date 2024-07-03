package presentation;

import java.util.Scanner;

public class UserMenu {
    public static void userMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER MENU =====");
            System.out.println("1. Show Address User by Address ID");
            System.out.println("2. Show all Address Users");
            System.out.println("3. Delete Address User by Address ID");
            System.out.println("4. Add new Address User");
            System.out.println("5. Update User Information by User ID");
            System.out.println("6. Show User Information by User ID");
            System.out.println("7. Back");
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
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
}
