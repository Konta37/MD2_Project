package entity;

import feature.service.AddressService;
import feature.service.UserService;
import utils.IOData;

import java.io.Serializable;
import java.util.Scanner;

public class Address implements IOData,Serializable {
    private int addressId; //auto when add
    private int userId;
    private String fullAddress;
    private String phone;
    private String receiveName; //name of person who receive

    public Address() {
    }

    public Address(int addressId, int userId, String fullAddress, String phone, String receiveName) {
        this.addressId = addressId;
        this.userId = userId;
        this.fullAddress = fullAddress;
        this.phone = phone;
        this.receiveName = receiveName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }


    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.addressId = inputAddressId(sc);
        this.userId = inputUserId(sc);
        this.fullAddress = inputFullAddress(sc);
        this.phone = inputPhone(sc);
        this.receiveName = inputReceiveName(sc);
    }

    @Override
    public void displayData() {
        String format = "| %-15s | %-20s | %-50s | %-10s | %-20s |\n";
        String separator = "+-----------------+----------------------+----------------------------------------------------+------------+----------------------+\n";

        // Print the header
        System.out.print(separator);
        System.out.format(format, "Address ID", "User ID", "Full Address", "Phone", "Receive Name");
        System.out.print(separator);
        System.out.format(format, this.addressId, this.userId, this.fullAddress, this.phone, this.receiveName);
    }

    private int inputAddressId(Scanner sc) {
        int max = 0;
        if (AddressService.addressList.isEmpty()){
            return 0;
        }
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getAddressId() > max) {
                max = AddressService.addressList.get(i).getAddressId();
            }
        }
        return max + 1;
    }

    public int inputUserId(Scanner sc) {
        System.out.println("Enter user id: ");
        do {
            String input = sc.nextLine();
            try {
                boolean isExist = false;
                //when you find a user -> add all information to address, phone, and receiveName
                for (int i =0; i< UserService.userList.size(); i++) {
                    if (UserService.userList.get(i).getUserId() == Integer.parseInt(input)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    return Integer.parseInt(input);
                }else {
                    System.err.println("Can't find user with id: " + input);
                }
            }catch (NumberFormatException e) {
                System.err.println("Invalid user id type. Try again.");
            }
        }while (true);
    }
    
    public String inputFullAddress(Scanner sc) {
        System.out.println("Enter full address: ");
        do {
            String input = sc.nextLine();
            if (!input.isEmpty()){
                return input;
            }else {
                System.err.println("Input is empty. Try again.");
            }
        }while (true);
    }

    public String inputPhone(Scanner sc) {
        System.out.println("Enter phone number: ");
        String regex = "0\\d{8}";
        do {
            String phone = sc.nextLine();
            if (phone.matches(regex)) {
                return phone;
            }else {
                System.err.println("Invalid phone number (0xxxxxxxx) (9 number). Try an other one!");
            }
        }while (true);
    }

    public String inputReceiveName(Scanner sc) {
        System.out.println("Enter receive name: ");
        do {
            String input = sc.nextLine();
            if (!input.isEmpty()){
                return input;
            }else {
                System.err.println("Input is empty. Try again.");
            }
        }while (true);
    }

    public int findIndexById(int idFind) {
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getAddressId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
