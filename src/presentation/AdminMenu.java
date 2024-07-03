package presentation;

import entity.Category;
import feature.service.CategoryService;
import utils.IOFile;

import java.util.Scanner;

public class AdminMenu {
    static CategoryService categoryService = new CategoryService();
    public static void adminMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== ADMIN MENU =====");
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
                    addNewProducts(sc);
                    break;
                case 7:
                    showAllProducts(sc);
                    break;
                case 8:
                    System.out.println("Exit admin menu.");
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
                    cateUpdate.setStatus(cateUpdate.inputCategoryStatus(sc));
                    break;
                case 4:
                    isLoop = false;
                    break;
                default:
                    System.err.println("Please enter a valid choice. Try again.");
            }
        } while (isLoop);
        IOFile.writeToFile(IOFile.PATH_CATEGORY,CategoryService.categoryList);
    }
    public static void deleteCategoryById(Scanner sc) {
        System.out.println("Enter Category ID to delete: ");
        int idDelete = inputNumber(sc);
        categoryService.deleteById(idDelete);
        System.out.println("Finish delete");
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
    public static void addNewProducts(Scanner sc) {}
    public static void showAllProducts(Scanner sc) {}

    public static int inputNumber(Scanner scanner) {
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số");
            }
        } while (true);
    }
}
