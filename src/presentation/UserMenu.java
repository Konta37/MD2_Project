package presentation;

import entity.*;
import feature.service.*;
import utils.IOFile;

import java.util.*;

public class UserMenu {
    static AddressService addressService = new AddressService();
    static WishListService wishListService = new WishListService();
    static ShoppingCartService shoppingCartService = new ShoppingCartService();
    static OrderService orderService = new OrderService();
    static OrderDetailsService orderDetailsService = new OrderDetailsService();

    public static void userMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER MENU =====");
            System.out.println("1. Information");
            System.out.println("2. Wish List");
            System.out.println("3. Cart");
            System.out.println("4. Order");
            System.out.println("5. Back");
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
                    informationMenu(sc);
                    break;
                case 2:
                    wishListMenu(sc);
                    break;
                case 3:
                    cartMenu(sc);
                    break;
                case 4:
                    orderMenu(sc);
                    break;
                case 5:
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    //==========================Information Menu=======================

    public static void informationMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER INFORMATION MENU =====");
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
                    System.out.println("Exit User Information Menu");
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
            if (AddressService.addressList.get(i).getUserId() == id) {
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
        if (indexUpdate < 0) {
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
        } while (isLoop);
        if (numberOfActive > 0) {
            userUpdate.setUpdateDate(new Date());
        }
        IOFile.writeToFile(IOFile.PATH_USER, UserService.userList);
    }

    public static void showUserInforById(Scanner sc) {
        System.out.println("Enter User ID to show: ");
        int id = inputNumber(sc);
        boolean isExist = false;
        for (int i = 0; i < UserService.userList.size(); i++) {
            if (UserService.userList.get(i).getUserId() == id) {
                UserService.userList.get(i).displayData();
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.err.println("Can not find User ID to show");
        }
        System.out.println("Finish showing User Information");

    }

    //==================================WishList==================================
    public static void wishListMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER WISH LIST MENU =====");
            System.out.println("1. Show Wish List");
            System.out.println("2. Add Product To Wish List");
            System.out.println("3. Delete Product in Wish List By Product ID");
            System.out.println("4. Back");
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
                    showAllWishList(sc);
                    break;
                case 2:
                    addProductToWishList(sc);
                    break;
                case 3:
                    deleteProductInWishListByProductId(sc);
                    break;
                case 4:
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    //show wish list with user id
    public static void showAllWishList(Scanner sc) {
        System.out.println("Enter user id to show: ");
        int input = inputNumber(sc);
        if (WishListService.wishListArrayList.isEmpty()) {
            System.err.println("Empty Wish List empty List");
            return;
        }
        // method referance
        WishListService.wishListArrayList.forEach(wishList -> wishList.displayUserWishList(input));
    }

    public static void addProductToWishList(Scanner sc) {
        //show all product for user to add product to wish list
        ProductService.productsList.forEach(Products::displayData);
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);

        WishList wishList = new WishList();
        wishList.inputData();
        wishListService.saveOrUpdate(wishList);
    }

    public static void deleteProductInWishListByProductId(Scanner sc) {
        System.out.println("Enter User ID to delete Product in Wish List: ");
        int userId = inputNumber(sc);
        List<Integer> userWishList = new ArrayList<>();
        for (int i = 0; i < WishListService.wishListArrayList.size(); i++) {
            if (WishListService.wishListArrayList.get(i).getUserId() == userId) {
                userWishList.add(i);
                WishListService.wishListArrayList.get(i).displayData();
            }
        }
        System.out.println("Enter wish list id to delete (user : " + userId + ").");
        int wishListId = inputNumber(sc);
        if (userWishList.isEmpty()) {
            System.err.println("Can't find user Id.");
            return;
        }
        boolean isExist = false;
        for (Integer integer : userWishList) {
            if (integer == wishListId) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            wishListService.deleteById(wishListId);
        } else {
            System.err.println("Can't find wish list Id.");
        }
        System.out.println("Finish deleting Product in Wish List with user id : " + userId);
    }

    //==================================Cart=======================================
    public static void cartMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER CART MENU =====");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Show All Products in Cart with User Id");
            System.out.println("3. Change Quantity Product in Cart");
            System.out.println("4. Delete Product from Cart By Product ID");
            System.out.println("5. Delete All Products in Cart");
            System.out.println("6. Choose id Cart to buy.");
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
                    addProductToCart(sc);
                    break;
                case 2:
                    showAllCartByUserId(sc);
                    break;
                case 3:
                    changeQuantityProductInCart(sc);
                    break;
                case 4:
                    deleteProductInCart(sc);
                    break;
                case 5:
                    deleteAllProductInUserCart(sc);
                    break;
                case 6:
                    confirmToAddToOrder(sc);
                    break;
                case 7:
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void addProductToCart(Scanner sc) {
        //show all product for user to add product to wish list
        ProductService.productsList.forEach(Products::displayForCart);
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";
        System.out.print(separator);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.inputData();
        shoppingCartService.saveOrUpdate(shoppingCart);

        ShoppingCartService.shoppingCartList.removeIf(cart -> cart.getProductId() == -1);
    }

    public static void showAllCartByUserId(Scanner sc) {
        System.out.println("Enter user id to show: ");
        int input = inputNumber(sc);
        if (ShoppingCartService.shoppingCartList.isEmpty()) {
            System.err.println("Empty Shopping Cart empty List");
            return;
        }
        // method reference
        ShoppingCartService.shoppingCartList.forEach(shoppingCart -> shoppingCart.displayUserShoppingCart(input));
        System.out.println("Finish showing cart.");
    }

    public static void changeQuantityProductInCart(Scanner sc) {
        System.out.println("Enter user id to show: ");
        int userId = inputNumber(sc);
        if (ShoppingCartService.shoppingCartList.isEmpty()) {
            System.err.println("Empty Shopping Cart empty List");
            return;
        }
        // method reference
        ShoppingCartService.shoppingCartList.forEach(shoppingCart -> shoppingCart.displayUserShoppingCart(userId));

        System.out.println("Enter cart id to change quantity: ");
        int cartId = inputNumber(sc);

        for (ShoppingCart shoppingCart : ShoppingCartService.shoppingCartList) {
            if (shoppingCart.getShoppingCartID() == cartId) {
                shoppingCart.setOrderQuantity(shoppingCart.inputNewOrderQuantity(sc));
                break;
            }
        }

    }

    public static void deleteProductInCart(Scanner sc) {
        System.out.println("Enter cart id to delete: ");
        int cartId = inputNumber(sc);
        shoppingCartService.deleteById(cartId);
    }

    public static void deleteAllProductInUserCart(Scanner sc) {
        System.out.println("Enter user id to delete all user cart: ");
        int userId = inputNumber(sc);
        boolean isExist = false;
        for (ShoppingCart shoppingCart : ShoppingCartService.shoppingCartList) {
            if (shoppingCart.getUserId() == userId) {
                isExist = true;
                break;
            }
        }
        if (isExist) {
            System.out.println("Do you want to delete all user cart by product id : " + userId);
            do {
                String isDelete = sc.nextLine();
                if (isDelete.equalsIgnoreCase("yes")) {
                    // Use removeIf to remove all items belonging to the specified user
                    ShoppingCartService.shoppingCartList.removeIf(cart -> cart.getUserId() == userId);
                    System.out.println("Finish deleting all user cart with user id : " + userId);
                    return;
                } else if (isDelete.equalsIgnoreCase("no")) {
                    System.out.println("Exit funtion.");
                    return;
                } else {
                    System.err.println("Invalid input (yes/no). Try again.");
                }
            } while (true);
        }


    }

    public static void confirmToAddToOrder(Scanner sc) {
        System.out.println("Enter user id to show cart: ");
        int userId = inputNumber(sc);

        // method reference
        ShoppingCartService.shoppingCartList.forEach(shoppingCart -> shoppingCart.displayUserShoppingCart(userId));
        do {
            System.out.println("➢ ===== USER ORDER OPTION =====");
            System.out.println("1. Choose lots of Shopping Cart Id to Buy");
            System.out.println("2. Buy all.");
            System.out.println("3. Back");
            int choose = inputNumber(sc);
            switch (choose) {
                case 1:
                    chooseShoppingCartToBuy(sc, userId);
                    break;
                case 2:
                    buyAllShoppingCart(sc, userId);
                    break;
                case 3:
                    System.out.println("Exit User Order Option");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void chooseShoppingCartToBuy(Scanner sc, int userId) {
        System.out.println("Chua lam gi");
    }

    public static void buyAllShoppingCart(Scanner sc, int userId) {
        // Create a new Date object
        Date currentDate = new Date();

        // Create a Calendar object and set it to the current date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Add 4 days to the current date
        calendar.add(Calendar.DAY_OF_YEAR, 4);

        // Get the updated date
        Date newDate = calendar.getTime();

        //ask user to enter note
        System.out.println("Enter note: ");
        String note = sc.nextLine();

        int indexUserAddressEnter = indexUserAddressEnter(userId,sc);

        //first check user id has anything in cart
        //second check user id has any address
        double totalPrice = 0;
        if (checkUserIdInShoppingCart(userId) && checkUserIdInAddressList(userId) && indexUserAddress(userId) >= 0) {
            Orders orders = new Orders();

            Orders newOrders = new Orders(
                    orders.inputOrderId(),
                    orders.inputSerialNumber(),
                    userId,
                    0, // Total price initialized to 0
                    orders.inputOrderStatus(),
                    note, // Note initialized to empty string
                    AddressService.addressList.get(indexUserAddressEnter).getReceiveName(),
                    AddressService.addressList.get(indexUserAddressEnter).getFullAddress(),
                    AddressService.addressList.get(indexUserAddressEnter).getPhone(),
                    currentDate,
                    newDate
            );
            //add every cart id to order detail. When you add you also count add every unit price * quantity to total
            for (ShoppingCart shoppingCart : ShoppingCartService.shoppingCartList) {
                if (shoppingCart.getUserId() == userId) {
                    boolean isAvailable = true;
                    //change product quantity in stock
                    for (int i = 0; i < ProductService.productsList.size(); i++) {
                        if (ProductService.productsList.get(i).getProductId() == shoppingCart.getProductId()) {
                            if (ProductService.productsList.get(i).getStockQuantity() - shoppingCart.getOrderQuantity() >= 0) {
                                ProductService.productsList.get(i).setStockQuantity(
                                        ProductService.productsList.get(i).getStockQuantity() - shoppingCart.getOrderQuantity()
                                );
                                IOFile.writeToFile(IOFile.PATH_PRODUCT, ProductService.productsList);
                            } else {
                                isAvailable = false;
                                System.err.println("Product id: " + shoppingCart.getProductId() + " doesn't have enough in stock for you to buy. This product will be disable.");
                                ;
                            }
                        }
                    }

                    //if product has enough quantity for user to buy -> dd to order detail.
                    if (isAvailable) {
                        OrderDetails orderDetails = new OrderDetails(
                                newOrders.getOrderId(),
                                shoppingCart.getProductId(),
                                nameProduct(shoppingCart.getProductId()),
                                unitPriceProduct(shoppingCart.getProductId()),
                                shoppingCart.getOrderQuantity()
                        );

                        //counting total
                        totalPrice = totalPrice + orderDetails.getUnitPrice() * orderDetails.getOrderQuantity();

                        //saving orderDetail to List and file
                        orderDetailsService.saveOrUpdate(orderDetails);
                    }

                }
            }
            //add total after calculator to newOrder
            newOrders.setTotalPrice(totalPrice);

            //after count total we add order to list and add to file
            orderService.saveOrUpdate(newOrders);

            //after save done -> delete every cart with that user id
            ShoppingCartService.shoppingCartList.removeIf(cart -> cart.getUserId() == userId);
            IOFile.writeToFile(IOFile.PATH_CART, ShoppingCartService.shoppingCartList);

            System.out.println("Finish buy all products in cart.");
        } else {
            System.err.println("User with id " + userId + " does not have any shopping cart.");
            System.err.println("Or User with id " + userId + " does not have any address to buy it.");
        }
    }

    //check userId has anything in shopping cart
    public static boolean checkUserIdInShoppingCart(int userId) {
        for (int i = 0; i < ShoppingCartService.shoppingCartList.size(); i++) {
            if (ShoppingCartService.shoppingCartList.get(i).getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    //check user id has anything in cart
    public static boolean checkUserIdInAddressList(int userId) {
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    public static int indexUserAddressEnter(int userId, Scanner sc) {
        //ask user to enter your address
        System.out.println("Show address");
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getUserId() == userId) {
                AddressService.addressList.get(i).displayData();
            }
        }
        System.out.println("Enter your address id: ");
        int addressId = inputNumber(sc);
        //check if both userId and addressId same in list
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getUserId() == userId && AddressService.addressList.get(i).getAddressId() == addressId) {
                return i;
            }
        }
        return -1;
    }

    //auto add first address of user
    public static int indexUserAddress(int userId) {
        for (int i = 0; i < AddressService.addressList.size(); i++) {
            if (AddressService.addressList.get(i).getUserId() == userId) {
                return i;
            }
        }
        return -1;
    }

    //add Name
    public static String nameProduct(int productId) {
        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductId() == productId) {
                return ProductService.productsList.get(i).getProductName();
            }
        }
        return "";
    }

    public static double unitPriceProduct(int productId) {
        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductId() == productId) {
                return ProductService.productsList.get(i).getUnitPrice();
            }
        }
        return 0;
    }

    //==================================Order======================================
    public static void orderMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER ORDER MENU =====");
            System.out.println("1. Showing All Orders by User id");
            System.out.println("2. Showing Orders by Status");
            System.out.println("3. Cancel Order with id User");
            System.out.println("4. Back");
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
                    showAllOrderByUserId(sc);
                    break;
                case 2:
                    showOrdersByStatusWithUserId(sc);
                    break;
                case 3:
                    cancelOrderWithUserId(sc);
                    break;
                case 4:
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void showAllOrderByUserId(Scanner sc) {
        System.out.println("Enter user id to show: ");
        int input = inputNumber(sc);
        if (OrderService.ordersList.isEmpty()) {
            System.err.println("Empty Order List");
            return;
        }
        // method reference
        OrderService.ordersList.forEach(order -> order.displayUserOrder(input));
        System.out.println("Finish Order List.");
    }

    public static void showOrdersByStatusWithUserId(Scanner sc) {
        System.out.println("Enter user id to show: ");
        int input = inputNumber(sc);
        if (OrderService.ordersList.isEmpty()) {
            System.err.println("Empty Order List");
            return;
        }


        do {
            System.out.println("➢ ===== SHOW ORDER BY STATUS MENU =====");
            System.out.println("1. Showing Waiting Orders");
            System.out.println("2. Showing Confirm Orders ");
            System.out.println("3. Showing Delivery Orders ");
            System.out.println("4. Showing Success Orders ");
            System.out.println("5. Showing Cancel Orders ");
            System.out.println("6. Showing Denied Orders ");
            System.out.println("7. Back");
            System.out.println("Your choice: ");
            String status = "";
            int choose;
            try {
                choose = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Try again.");
                continue;
            }
            switch (choose) {
                case 1:
                    status = "WAITING";
                    showOrdersByStatus(status);
                    break;
                case 2:
                    status = "CONFIRM";
                    showOrdersByStatus(status);
                    break;
                case 3:
                    status = "DELIVERY";
                    showOrdersByStatus(status);
                    break;
                case 4:
                    status = "SUCCESS";
                    showOrdersByStatus(status);
                    break;
                case 5:
                    status = "CANCEL";
                    showOrdersByStatus(status);
                    break;
                case 6:
                    status = "DENIED";
                    showOrdersByStatus(status);
                    break;
                case 7:
                    System.out.println("Exit User Menu");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);


    }

    public static void showOrdersByStatus(String status) {
        for (int i = 0; i < OrderService.ordersList.size(); i++) {
            if (OrderService.ordersList.get(i).getOrderStatus() == OrderStatus.valueOf(status)) {
                OrderService.ordersList.get(i).displayData();
            }
        }
    }

    public static void cancelOrderWithUserId(Scanner sc) {
        System.out.println("Enter user id to show order: ");
        int input = inputNumber(sc);
        if (OrderService.ordersList.isEmpty()) {
            System.err.println("Empty Order List");
            return;
        }
        // method reference
        OrderService.ordersList.forEach(order -> order.displayUserOrder(input));
        System.out.println("Enter order id to cancel");
        int orderId = inputNumber(sc);
        //Check if order id is WAITING status or not. If not -> cant change
        for (int i = 0; i < OrderService.ordersList.size(); i++){
            if (OrderService.ordersList.get(i).getOrderId() == orderId && OrderService.ordersList.get(i).getOrderStatus() != OrderStatus.WAITING){
                System.err.println("This order id: " + orderId + " is not for you to change status (status is not Waiting).");
                return;
            }
        }
        //check if userid and orderId is the same input -> change status
        for (int i = 0; i < OrderService.ordersList.size(); i++) {
            if (OrderService.ordersList.get(i).getOrderId() == orderId && OrderService.ordersList.get(i).getUserId() == input) {
                //change status of order
                OrderService.ordersList.get(i).setOrderStatus(OrderStatus.CANCEL);
                System.out.println("Finish Cancel Order with id " + orderId + " and user id " + input);

                //change quantity of product
                for (int j = 0; j < OrderDetailsService.orderDetailsList.size(); j++) {
                    if (OrderDetailsService.orderDetailsList.get(i).getOrderId() == orderId) {

                        //find product to change quantity
                        //boolean check if in case admin had deleted that product
                        boolean isExist = false;
                        for (Products product : ProductService.productsList) {
                            if (product.getProductId() == OrderDetailsService.orderDetailsList.get(i).getProductId()) {
                                product.setStockQuantity(product.getStockQuantity() + OrderDetailsService.orderDetailsList.get(i).getOrderQuantity());
                                isExist = true;

                                System.out.println("Finish add more quantity product: " + product.getProductId());
                                IOFile.writeToFile(IOFile.PATH_PRODUCT,ProductService.productsList);
                                break;
                            }
                        }
                        if (!isExist) {
                            System.err.println("Product " +OrderDetailsService.orderDetailsList.get(i).getProductId() + " does not exist");
                        }

                    }
                }

                //write orderService file
                IOFile.writeToFile(IOFile.PATH_ORDER,OrderService.ordersList);
                break;
            }
        }
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
