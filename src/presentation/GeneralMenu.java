package presentation;

import entity.Products;
import feature.service.CategoryService;
import feature.service.ProductService;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GeneralMenu {
    public static void generalMenu(Scanner sc){
        do {
            System.out.println("➢ ===== GENERAL MENU =====");
            System.out.println("1. Show information of product by Id");
            System.out.println("2. Show list of products by Category");
            System.out.println("3. Show list new products");
            System.out.println("4. Show list products is bought");
            System.out.println("5. Search product by name or description");
            System.out.println("6. ???");
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
                    showInformationProductById(sc);
                    break;
                case 2:
                    showProductsByCategory(sc);
                    break;
                case 3:
                    showListNewProducts(sc);
                    break;
                case 4:
                    showListProductCanBuy(sc);
                    break;
                case 5:
                    searchProductByNameOrDescription(sc);
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Exit General Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (true);
    }
    
    public static void showInformationProductById(Scanner sc){
        System.out.println("List product");
        System.out.println("Enter your id to show information");
        int id = inputNumber(sc);
        for (int i = 0 ; i< ProductService.productsList.size(); i++){
            if (ProductService.productsList.get(i).getProductId() == id){
                ProductService.productsList.get(i).displayData();
                break;
            }
        }
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);
    }
    
    public static void showProductsByCategory(Scanner sc){
        for (int i = 0; i< CategoryService.categoryList.size(); i++){
            CategoryService.categoryList.get(i).displayData();
        }
        System.out.println("Enter category id to show products with that category: ");
        int input = inputNumber(sc);
        int count = 0;
        for (int i = 0; i < ProductService.productsList.size(); i++){
            if (ProductService.productsList.get(i).getCategoryId() == input){
                ProductService.productsList.get(i).displayData();
                count++;
            }
        }
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);
        System.out.println("There are " + count + " products with that category id " + input +".");
    }
    
    public static void showListNewProducts(Scanner sc){
        // Sort products by dateCreated in descending order
        ProductService.productsList.sort(new Comparator<Products>() {
            @Override
            public int compare(Products p1, Products p2) {
                return p2.getDateCreated().compareTo(p1.getDateCreated());
            }
        });

        // Display the sorted products
        System.out.println("Newest Products:");
        for (Products product : ProductService.productsList) {
            product.displayData();
            // Print other product details as needed
        }
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);
    }
    
    public static void showListProductCanBuy(Scanner sc){
        for (int i = 0; i < ProductService.productsList.size(); i++){
            if (ProductService.productsList.get(i).getStockQuantity() > 0){
                ProductService.productsList.get(i).displayData();
            }
        }
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);
        System.out.println("Finish show list products can buy");
    }

    public static void searchProductByNameOrDescription(Scanner sc){
        System.out.println("Enter something to find: ");
        String input = sc.nextLine();
        int count = 0;
        for (int i = 0; i< ProductService.productsList.size(); i++){
            if (ProductService.productsList.get(i).getProductName().toLowerCase().contains(input) ||
                    ProductService.productsList.get(i).getDescription().toLowerCase().contains(input)){
                ProductService.productsList.get(i).displayData();
                count++;
            }
        }
        String separator = "+--------------+------------------------------------------+----------------------+----------------------+--------------+\n";

        System.out.printf(separator);
        System.out.println("There are " + count + " products with that " + input + ".");
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
