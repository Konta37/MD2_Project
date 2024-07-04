package entity;

import feature.service.ProductService;
import feature.service.RoleService;
import feature.service.UserService;
import feature.service.WishListService;
import utils.IOData;

import java.io.Serializable;
import java.util.Scanner;

public class WishList implements IOData, Serializable {
    private int wishListId;
    private int userId;
    private int productId;

    public WishList() {
    }

    public WishList(int wishListId, int userId, int productId) {
        this.wishListId = wishListId;
        this.userId = userId;
        this.productId = productId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        this.wishListId = inputWishListId();
        this.userId = inputUserId(scanner);
        this.productId = inputProductId(scanner);
    }

    @Override
    public void displayData() {
        String format = "| %-15s | %-15s | %-15s |\n";
        String separator = "+-----------------+-----------------+-----------------+\n";

        System.out.print(separator);
        System.out.format(format, "Wish List ID", "User ID", "Product ID");
        System.out.print(separator);
        System.out.format(format, wishListId, userId, productId);
        System.out.print(separator);
    }

    private int inputWishListId(){
        int max = 0;
        if (WishListService.wishListArrayList.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < WishListService.wishListArrayList.size(); i++) {
            if (WishListService.wishListArrayList.get(i).getWishListId() > max) {
                max = WishListService.wishListArrayList.get(i).getWishListId();
            }
        }
        return max + 1;
    }

    public int inputUserId(Scanner sc){
        System.out.println("Enter user id: ");
        do {
            String input =sc.nextLine();
            try {
                boolean isExist = false;
                for (int i = 0; i < UserService.userList.size(); i++) {
                    if (UserService.userList.get(i).getUserId() == Integer.parseInt(input)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    return Integer.parseInt(input);
                }else {
                    System.err.println("Can't find user id: " + input);
                }
            }catch (NumberFormatException e){
                System.err.println("Invalid user id type (number). Try again.");
            }
        }while (true);
    }

    public int inputProductId(Scanner sc){
        System.out.println("Enter product id: ");
        do {
            String input =sc.nextLine();
            try {
                boolean isExist = false;
                for (int i = 0; i < ProductService.productsList.size(); i++) {
                    if (ProductService.productsList.get(i).getProductId() == Integer.parseInt(input)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    return Integer.parseInt(input);
                }else {
                    System.err.println("Can't find product id: " + input);
                }
            }catch (NumberFormatException e){
                System.err.println("Invalid product id type (number). Try again.");
            }
        }while (true);
    }
}
