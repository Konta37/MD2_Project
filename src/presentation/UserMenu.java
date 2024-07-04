package presentation;

import entity.Address;
import entity.Role;
import entity.User;
import feature.service.AddressService;
import feature.service.UserService;
import utils.IOFile;

import java.util.Date;
import java.util.Scanner;

public class UserMenu {
    static AddressService addressService = new AddressService();
    public static void userMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER MENU =====");
            System.out.println("1. Show Address User by Address ID");
            System.out.println("2. Show all Address of Users");
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
                    showAllAddressByIdUser(sc);
                    break;
                case 2:
                    showAllAddress(sc);
                    break;
                case 3:
                    deleteAddressById(sc);
                    break;
                case 4:
                    addNewAddress(sc);
                    break;
                case 5:
                    updateUserInforById(sc);
                    break;
                case 6:
                    showUserInforById(sc);
                    break;
                case 7:
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void showAllAddressByIdUser(Scanner sc) {
        System.out.println("Enter User ID to show all Address of Users: ");
        int id = inputNumber(sc);
        int count = 0;
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getUserId() == id){
                AddressService.addressList.get(i).displayData();
                count++;
            }
        }
        String separator = "+-----------------+----------------------+----------------------------------------------------+------------+----------------------+\n";
        System.out.print(separator);

        System.out.println("There are " + count + " Address of Users in the system");
    }

    public static void showAllAddress(Scanner sc) {
        if (AddressService.addressList.isEmpty()) {
            System.err.println("Empty Address List");
            return;
        }
        // method referance
        AddressService.addressList.forEach(Address::displayData);
        String separator = "+-----------------+----------------------+----------------------------------------------------+------------+----------------------+\n";
        System.out.print(separator);
    }

    public static void addNewAddress(Scanner sc) {
        Address address = new Address();
        address.inputData();
        addressService.saveOrUpdate(address);
    }

    public static void deleteAddressById(Scanner sc) {
        System.out.println("Enter Address ID to delete: ");
        int id = inputNumber(sc);
        addressService.deleteById(id);
    }

    public static void updateUserInforById(Scanner sc) {
        System.out.println("Enter User ID to update: ");
        int idUpdate = inputNumber(sc);
        int indexUpdate = AdminMenu.userService.findIndexById(idUpdate);
        if (indexUpdate < 0 ){
            System.err.println("Can not find User ID to update");
            return;
        }
        User userUpdate = UserService.userList.get(indexUpdate);
        boolean isLoop = true;
        int numberOfActive = 0;
        do {
            System.out.println("1. Update email.");
            System.out.println("2. Update password.");
            System.out.println("3. Update full name.");
            System.out.println("4. Update avatar.");
            System.out.println("5. Update phone number.");
            System.out.println("6. Update address.");
            System.out.println("7. Back");
            System.out.println("Your choice: ");
            int choose = inputNumber(sc);
            switch (choose) {
                case 1:
                    userUpdate.setEmail(userUpdate.inputEmail(sc));
                    numberOfActive++;
                    break;
                case 2:
                    userUpdate.setPassword(userUpdate.inputPassword(sc));
                    numberOfActive++;
                    break;
                case 3:
                    userUpdate.setFullName(userUpdate.inputFullName(sc));
                    numberOfActive++;
                    break;
                case 4:
                    userUpdate.setAvatar(userUpdate.inputAvatar(sc));
                    numberOfActive++;
                    break;
                case 5:
                    userUpdate.setPhone(userUpdate.inputPhone(sc));
                    numberOfActive++;
                    break;
                case 6:
                    userUpdate.setAddress(userUpdate.inputAddress(sc));
                    numberOfActive++;
                    break;
                case 7:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Please enter a valid choice. Try again.");
            }
        }while (isLoop);
        if (numberOfActive>0){
            userUpdate.setUpdateDate(new Date());
        }
        IOFile.writeToFile(IOFile.PATH_USER, UserService.userList);
    }

    public static void showUserInforById(Scanner sc) {
        System.out.println("Enter User ID to show: ");
        int id = inputNumber(sc);
        boolean isExist = false;
        for (int i = 0; i < UserService.userList.size(); i++) {
            if (UserService.userList.get(i).getUserId() == id){
                UserService.userList.get(i).displayData();
                isExist = true;
                break;
            }
        }
        if (!isExist){
            System.err.println("Can not find User ID to show");
        }
        System.out.println("Finish showing User Information");

    }

    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please enter number. Try again.");
            }
        } while (true);
    }
}
