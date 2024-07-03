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

    }

    @Override
    public void displayData() {

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
                        this.fullAddress = UserService.userList.get(i).getAddress();
                        this.phone = UserService.userList.get(i).getPhone();
                        this.receiveName = UserService.userList.get(i).getFullName();
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
    


//    public String inputPhone(Scanner sc) {
//
//    }

    public int findIndexById(int idFind) {
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getAddressId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
