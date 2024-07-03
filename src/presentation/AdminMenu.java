package presentation;

import entity.Category;
import entity.Products;
import entity.Role;
import entity.RoleName;
import feature.service.CategoryService;
import feature.service.ProductService;
import feature.service.RoleService;
import utils.IOFile;

import java.util.Scanner;

public class AdminMenu {
    static CategoryService categoryService = new CategoryService();
    static ProductService productService = new ProductService();
    static RoleService roleService = new RoleService();

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

                    break;
                case 4:

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
                    System.out.println("Exit user menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }

    public static void orderMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== ORDER MENU =====");
            System.out.println("1. ");
            System.out.println("2. ");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
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
                    System.out.println("Exit user menu.");
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
            System.out.println("4. Quay lại");
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

    public static void showAllUsers() {}

    public static void showAllRoles() {
        for (Role role : RoleService.roleList) {
            role.displayData();
        }
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
