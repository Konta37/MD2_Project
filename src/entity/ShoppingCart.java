package entity;

import feature.service.ProductService;
import feature.service.ShoppingCartService;
import feature.service.UserService;
import utils.IOData;

import java.io.Serializable;
import java.util.Scanner;

public class ShoppingCart implements IOData, Serializable {
    private int shoppingCartID;
    private int productId;
    private int userId;
    private int orderQuantity; //>0

    public ShoppingCart() {
    }

    public ShoppingCart(int shoppingCartID, int productId, int userId, int orderQuantity) {
        this.shoppingCartID = shoppingCartID;
        this.productId = productId;
        this.userId = userId;
        this.orderQuantity = orderQuantity;
    }

    public int getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(int shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        this.userId = inputUserID(scanner);
        this.productId = inputProductID(scanner);
        this.shoppingCartID = inputShoppingCartID();
        this.orderQuantity = inputOrderQuantity(scanner);
    }

    @Override
    public void displayData() {
        String format = "| %-20s | %-15s | %-15s | %-15s |\n";
        String separator = "+----------------------+-----------------+-----------------+-----------------+\n";
        System.out.print(separator);
        System.out.format(format, "Shopping Cart ID", "Product ID", "User ID", "Order Quantity");
        System.out.print(separator);
        System.out.format(format, shoppingCartID, productId, userId, orderQuantity);
        System.out.print(separator);
    }
    //show wish list with user id
    public void displayUserShoppingCart(int userId){
        if (userId == this.userId) {
            displayData();
        }
    }

    private int inputShoppingCartID() {
        int max = 0;
        if (ShoppingCartService.shoppingCartList.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < ShoppingCartService.shoppingCartList.size(); i++) {
            if (ShoppingCartService.shoppingCartList.get(i).getShoppingCartID() > max) {
                max = ShoppingCartService.shoppingCartList.get(i).getShoppingCartID();
            }
        }
        return max + 1;
    }

    public int inputProductID(Scanner sc) {
        System.out.println("Enter product id: ");
        do {
            String input = sc.nextLine();
            try {
                boolean isExist = false;
                for (int i = 0; i < ProductService.productsList.size(); i++) {
                    if (ProductService.productsList.get(i).getProductId() == Integer.parseInt(input)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    //in case when product id already in cart -> add more quantity
                    for (ShoppingCart shoppingCart : ShoppingCartService.shoppingCartList) {
                        if (shoppingCart.getUserId()==this.userId && shoppingCart.getProductId()==Integer.parseInt(input)) {
                            shoppingCart.setOrderQuantity(shoppingCart.inputQuantityInCaseProductInCart(sc, Integer.parseInt(input)));
                            return -1;
                        }
                    }
                    return Integer.parseInt(input);
                } else {
                    System.err.println("Can't find product id: " + input);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid product id type (number). Try again.");
            }
        } while (true);
    }

    public int inputUserID(Scanner sc) {
        System.out.println("Enter user id: ");
        do {
            String input = sc.nextLine();
            try {
                boolean isExist = false;
                for (int i = 0; i < UserService.userList.size(); i++) {
                    if (UserService.userList.get(i).getUserId() == Integer.parseInt(input)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    return Integer.parseInt(input);
                } else {
                    System.err.println("Can't find user id: " + input);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid user id type (number). Try again.");
            }
        } while (true);
    }

    //check input is smaller than quantity stock of product
    public int inputOrderQuantity(Scanner sc) {
        //case product id already in cart
        if (this.productId == -1){
            return 0;
        }
        System.out.println("Enter order quantity: ");
        do {
            String input = sc.nextLine();
            try {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= productQuantity(this.productId)) {
                    return Integer.parseInt(input);
                } else {
                    System.err.println("Input can't > 0 and <= " + productQuantity(this.productId)  +". Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid order quantity type (number). Try again.");
            }
        } while (true);
    }


    //check input is smaller than quantity stock of product
    public int inputNewOrderQuantity(Scanner sc) {
        System.out.println("Enter new quantity:");
        do {
            String input = sc.nextLine();
            try {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= productQuantity(this.productId)) {
                    return Integer.parseInt(input);
                } else {
                    System.err.println("Input can't > 0 and <= " + productQuantity(this.productId)  +". Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid order quantity type (number). Try again.");
            }
        } while (true);
    }

    //case product id already in cart -> plus more quantity
    public int inputQuantityInCaseProductInCart(Scanner sc, int productId){
        System.out.println("Enter quantity: ");
        do {
            String input = sc.nextLine();
            try {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) + productQuantityInCaseUserInCart(productId)<= productQuantity(this.productId)) {
                    return Integer.parseInt(input) + productQuantityInCaseUserInCart(productId);
                }else {
                    System.err.println("Because the product already in cart so enter input plus " + productQuantityInCaseUserInCart(productId) + " smaller than " + productQuantity(this.productId) +" and try again.");
                }
            }catch (NumberFormatException e){
                System.err.println("Invalid quantity type (number). Try again.");
            }
        }while (true);
    }

    //take value quantity with product id
    private int productQuantity(int productID) {
        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductId() == productID) {
                return ProductService.productsList.get(i).getStockQuantity();
            }
        }
        return 0;
    }

    //return current quantity of product in cart
    private int productQuantityInCaseUserInCart(int productId){
        for (int i = 0; i < ShoppingCartService.shoppingCartList.size(); i++) {
            if (ShoppingCartService.shoppingCartList.get(i).getProductId() == productId && ShoppingCartService.shoppingCartList.get(i).getUserId()==this.userId){
                return ShoppingCartService.shoppingCartList.get(i).getOrderQuantity();
            }
        }
        return 0;
    }
}
