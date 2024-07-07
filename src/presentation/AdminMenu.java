package presentation;

import entity.*;
import feature.service.*;
import utils.IOFile;

import java.util.Date;
import java.util.Scanner;

public class AdminMenu {
    static CategoryService categoryService = new CategoryService();
    static ProductService productService = new ProductService();
    static RoleService roleService = new RoleService();
    static UserService userService = new UserService();

    public static void adminMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== ADMIN MENU =====");
            System.out.println("1. Category Menu");
            System.out.println("2. Product Menu");
            System.out.println("3. User Menu");
            System.out.println("4. Order Menu");
            System.out.println("5. Show all role");
            System.out.println("6. Back");
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
                    categoriesMenu(sc);
                    break;
                case 2:
                    productMenu(sc);
                    break;
                case 3:
                    userMenu(sc);
                    break;
                case 4:
                    orderMenu(sc);
                    break;
                case 5:
                    showAllRoles();
                    break;
                case 6:
                    System.out.println("Exit admin menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);

    }

    public static void categoriesMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== CATEGORY MENU =====");
            System.out.println("1. Add number of Categories");
            System.out.println("2. Show all Categories");
            System.out.println("3. Edit Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Search Category by ID");
            System.out.println("6. Back");
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
                    addNewCategories(sc);
                    break;
                case 2:
                    showAllCategories(sc);
                    break;
                case 3:
                    editCategoryById(sc);
                    break;
                case 4:
                    deleteCategoryById(sc);
                    break;
                case 5:
                    searchCategoriesById(sc);
                    break;
                case 6:
                    System.out.println("Exit category menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }



    public static void addNewCategories(Scanner sc) {
        System.out.println("Enter number of Categories you want to add: ");
        int number = inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Category cate = new Category();
            cate.inputData();
            categoryService.saveOrUpdate(cate);
        }
    }

    public static void showAllCategories(Scanner sc) {
        if (CategoryService.categoryList.isEmpty()) {
            System.err.println("Empty Category List");
            return;
        }
        // method referance
        String format = "| %-12s | %-20s | %-30s | %-10s |\n";
        String separator = "+--------------+----------------------+--------------------------------+------------+\n";

        System.out.print(separator);
        System.out.format(format, "Category ID", "Category Name", "Description", "Status");
        CategoryService.categoryList.forEach(Category::displayData);
        System.out.print(separator);
    }

    public static void editCategoryById(Scanner sc) {
        System.out.println("Enter Category ID to edit: ");
        int idUpdate = inputNumber(sc);
        int indexUpdate = categoryService.findIndexById(idUpdate);
        if (indexUpdate < 0) {
            System.err.println("Can not find id to update " + idUpdate);
            return;
        }
        Category cateUpdate = CategoryService.categoryList.get(indexUpdate);
        boolean isLoop = true;
        do {
            System.out.println("1. Update Category Name");
            System.out.println("2. Update Category Description");
            System.out.println("3. Update Category Status");
            System.out.println("4. Back");
            System.out.println("Your choice: ");
            int choice = inputNumber(sc);
            switch (choice) {
                case 1:
                    cateUpdate.setCategoryName(cateUpdate.inputCategoryName(sc));
                    break;
                case 2:
                    cateUpdate.setDescription(cateUpdate.inputCategoryDescription(sc));
                    break;
                case 3:
                    System.out.println("Your action may make your product with this category be disable");
                    cateUpdate.setStatus(cateUpdate.inputCategoryStatus(sc));
                    break;
                case 4:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Please enter a valid choice. Try again.");
            }
        } while (isLoop);
        IOFile.writeToFile(IOFile.PATH_CATEGORY, CategoryService.categoryList);
    }

    public static void deleteCategoryById(Scanner sc) {
        System.out.println("Enter Category ID to delete: ");
        int idDelete = inputNumber(sc);
        categoryService.deleteById(idDelete);
    }

    public static void searchCategoriesById(Scanner sc) {
        System.out.println("Enter Category ID to search: ");
        int idSearch = inputNumber(sc);
        for (int i = 0; i < CategoryService.categoryList.size(); i++) {
            if (CategoryService.categoryList.get(i).getCategoryId() == idSearch) {
                CategoryService.categoryList.get(i).displayData();
                break;
            }
        }
        String separator = "+--------------+----------------------+--------------------------------+------------+\n";

        System.out.print(separator);
        System.out.println("Finish search Category by ID");
    }


    //=============================================Product=============================================
    public static void productMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== PRODUCT MENU =====");
            System.out.println("1. Add number of Products");
            System.out.println("2. Show all Products");
            System.out.println("3. Edit Products");
            System.out.println("4. Delete Products");
            System.out.println("5. Search Products by ID");
            System.out.println("6. Search Products by Name");
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
                    addNewProducts(sc);
                    break;
                case 2:
                    showAllProducts(sc);
                    break;
                case 3:
                    updateProductById(sc);
                    break;
                case 4:
                    deleteProductById(sc);
                    break;
                case 5:
                    searchProductById(sc);
                    break;
                case 6:
                    searchProductByName(sc);
                    break;
                case 7:
                    System.out.println("Exit product menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void addNewProducts(Scanner sc) {
        System.out.println("Enter number of Products you want to add: ");
        int number = inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Products products = new Products();
            products.inputData();
            productService.saveOrUpdate(products);
        }
    }

    public static void showAllProducts(Scanner sc) {
        if (ProductService.productsList.isEmpty()) {
            System.err.println("Empty Product List");
            return;
        }
        // method referance
        String format = "| %-12s | %-40s | %-20s | %-20s | %-12s |\n";
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";
        ProductService.productsList.forEach(Products::displayData);
        System.out.print(separator);
    }

    public static void searchProductById(Scanner sc) {
        System.out.println("Enter Product ID to search: ");
        int idSearch = inputNumber(sc);
        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductId() == idSearch) {
                ProductService.productsList.get(i).displayData();
                break;
            }
        }
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";
        System.out.print(separator);
        System.out.println("Finish search Product by ID");
    }

    public static void searchProductByName(Scanner sc) {
        System.out.println("Enter Product Name to search: ");
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";
        String productName = sc.nextLine();
        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductName().contains(productName)) {
                ProductService.productsList.get(i).displayData();
            }
        }
        System.out.print(separator);
        System.out.println("Finish search Product by Name");
    }

    public static void updateProductById(Scanner sc) {
        System.out.println("Enter Product ID to update: ");
        int idUpdate = inputNumber(sc);
        int indexUpdate = productService.findIndexById(idUpdate);
        if (indexUpdate < 0) {
            System.err.println("Can not find id to update " + idUpdate);
            return;
        }
        Products productUpdate = ProductService.productsList.get(indexUpdate);
        boolean isLoop = true;
        do {
            System.out.println("1. Update Product Name");
            System.out.println("2. Update Product Description");
            System.out.println("3. Update Product Unit Price");
            System.out.println("4. Update Product Stock Quantity");
            System.out.println("5. Update Product Image");
            System.out.println("6. Update Category Id in Product");
            System.out.println("7. Back");
            System.out.println("Your choice: ");
            int choice = inputNumber(sc);
            switch (choice) {
                case 1:
                    productUpdate.setProductName(productUpdate.inputProductName(sc));
                    break;
                case 2:
                    productUpdate.setDescription(productUpdate.inputDescription(sc));
                    break;
                case 3:
                    productUpdate.setUnitPrice(productUpdate.inputUnitPrice(sc));
                    break;
                case 4:
                    productUpdate.setStockQuantity(productUpdate.inputStockQuantity(sc));
                    break;
                case 5:
                    productUpdate.setProductImage(productUpdate.inputImage(sc));
                    break;
                case 6:
                    productUpdate.setCategoryId(productUpdate.inputCateId(sc));
                    break;
                case 7:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Please enter a valid choice. Try again.");
            }
        } while (isLoop);
        //when you update -> date update need to change
        productUpdate.setDateUpdated(new Date());
        //finish update -> write to file
        IOFile.writeToFile(IOFile.PATH_PRODUCT, ProductService.productsList);
    }

    public static void deleteProductById(Scanner sc) {
        System.out.println("Enter Product ID to delete: ");
        int idDelete = inputNumber(sc);
        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductId() == idDelete) {
                ProductService.productsList.remove(i);
                break;
            }
        }
        System.out.println("Finish delete Product by ID");
    }


    //=============================================User=============================================
    public static void userMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== USER MENU =====");
            System.out.println("1. Add number of User");
            System.out.println("2. Show all User");
            System.out.println("3. Edit User (?)");
            System.out.println("4. Delete User (?)");
            System.out.println("5. Search User by Name");
            System.out.println("6. Back");
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
                    addNewUser(sc);
                    break;
                case 2:
                    showAllUsers();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    searchUserByName(sc);
                    break;
                case 6:
                    System.out.println("Exit user menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void showAllUsers() {
        if (UserService.userList.isEmpty()) {
            System.err.println("Empty User List");
            return;
        }
        // method reference
        UserService.userList.forEach(User::displayData);
    }

    public static void showAllRoles() {
        for (Role role : RoleService.roleList) {
            role.displayData();
        }
    }

    public static void addNewUser(Scanner sc) {
        System.out.println("Enter number of Users you want to add: ");
        int number = inputNumber(sc);
        for (int i = 0; i < number; i++) {
            User user = new User();
            user.inputData();
            userService.saveOrUpdate(user);
        }
    }

    public static void searchUserByName(Scanner sc) {
        System.out.println("Enter User Name to search: ");
        String input = sc.nextLine();
        int count=0;
        for (int i = 0; i < UserService.userList.size(); i++) {
            if (UserService.userList.get(i).getFullName().toLowerCase().contains(input.toLowerCase())) {
                UserService.userList.get(i).displayData();
                count++;
            }
        }
        System.out.println("There are " + count + " users with the name " + input + ".");
    }

    //in case you want to add
    public static void addRole(Scanner sc){
        System.out.println("Enter number of Role you want to add: ");
        int number = inputNumber(sc);
        for (int i = 0; i < number; i++) {
            Role role = new Role();
            role.inputData();
            roleService.saveOrUpdate(role);
        }

    }


    //=================================Order===========================
    public static void orderMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== ORDER MENU =====");
            System.out.println("1. Show All Orders");
            System.out.println("2. Show Order Information by id");
            System.out.println("3. Show Orders by Status");
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
                    showAllOrders();
                    break;
                case 2:
                    showOrderInformation(sc);
                    break;
                case 3:
                    showOrderByStatus(sc);
                    break;
                case 4:
                    System.out.println("Exit user menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }


    public static void showAllOrders(){
        for (int i =0; i< OrderService.ordersList.size(); i++){
            OrderService.ordersList.get(i).displayData();
        }
        System.out.println("Finish show all Orders");
    }

    public static void showOrderInformation(Scanner sc){
        OrderService.ordersList.forEach(Orders::displayData);
        System.out.println("Enter Order ID to show: ");
        int input =inputNumber(sc);

        for (int i = 0; i < OrderDetailsService.orderDetailsList.size(); i++) {
            if (OrderDetailsService.orderDetailsList.get(i).getOrderId() == input) {
                OrderDetailsService.orderDetailsList.get(i).displayData();
            }
        }

        System.out.println("Finish showing Order Details with Order ID: " + input + ".");
    }

    public static void showOrderByStatus(Scanner sc){
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
            if (OrderService.ordersList.get(i).getOrderStatus() == OrderStatus.valueOf(status)){
                OrderService.ordersList.get(i).displayData();
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
