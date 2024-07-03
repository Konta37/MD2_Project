package entity;

import feature.service.CategoryService;
import feature.service.ProductService;
import utils.IOData;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Products implements IOData {
    private int productId;
    private String sku;
    private String productName;
    private String description;
    private double unitPrice; //price
    private int stockQuantity; //>=0
    private String productImage;
    private int categoryId; //take from Category
    private Date dateCreated;
    private Date dateUpdated;

    public Products() {
    }

    public Products(int productId, String sku, String productName, String description, double unitPrice, int stockQuantity, String productImage, int categoryId, Date dateCreated, Date dateUpdated) {
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.productImage = productImage;
        this.categoryId = categoryId;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.productId = inputProductId();
        this.sku = inputSku();
        this.productName = inputProductName(sc);
        this.description = inputDescription(sc);
        this.unitPrice = inputUnitPrice(sc);
        this.stockQuantity = inputStockQuantity(sc);
        this.productImage = inputImage(sc);
        this.categoryId = inputCateId(sc);
        this.dateCreated = inputDateCreated();
        this.dateUpdated = inputDateUpdated();
    }

    @Override
    public void displayData() {
        System.out.printf("%-12s %-12s %-20s %-10s %-12s %-15s\n", "Product ID", "SKU", "Product Name", "Price", "Stock Qty", "Category ID");
        System.out.printf("%-12d %-12s %-20s %-10.2f %-12d %-15d\n", productId, sku, productName, unitPrice, stockQuantity, categoryId);

        System.out.printf("%-12s %-12s %-20s %-10s\n", "Description", "Product Image", "Date Created", "Date Updated");
        System.out.printf("%-12s %-12s %-20s %-10s\n", description, productImage, dateCreated.toString(), dateUpdated.toString());
    }

    private int inputProductId() {
        int max = -1;
        if (!ProductService.productsList.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < ProductService.productsList.size(); i++) {
            if (ProductService.productsList.get(i).getProductId() >= max) {
                max = ProductService.productsList.get(i).getProductId();
            }
        }
        if (max >= 0) {
            return max + 1;
        } else {
            return 0;
        }
    }

    public String inputSku() {
        // Tạo một UUID ngẫu nhiên
        UUID sku;
        String skuString="";
        boolean isExist = false;
        do {
            // Tạo một UUID ngẫu nhiên
            sku = UUID.randomUUID();
            skuString = sku.toString();
            isExist = false;

            // Kiểm tra xem SKU đã tồn tại chưa
            for (Products product : ProductService.productsList) {
                if (product.getSku().equals(skuString)) {
                    isExist = true;
                    break;
                }
            }
        } while (isExist); // Nếu SKU tồn tại, tiếp tục tạo UUID mới

        return skuString;
    }

    public String inputProductName(Scanner sc) {
        System.out.println("Enter product name: ");
        do {
            String input = sc.nextLine();
            if (!input.isEmpty()) {
                boolean isExist = false;
                for (Products product : ProductService.productsList) {
                    if (product.getProductName().equalsIgnoreCase(input)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return input;
                } else {
                    System.err.println("Duplicate product name! Try again!");
                }
            } else {
                System.err.println("Invalid input (Cannot be empty). Try again!");
            }
        } while (true);
    }

    public String inputDescription(Scanner sc) {
        System.out.println("Enter product description: ");
        return sc.nextLine();
    }

    public double inputUnitPrice(Scanner sc){
        System.out.println("Enter unit price: ");
        do {
            String input = sc.nextLine();
            try {
                if (Double.parseDouble(input) >0) {
                    return Double.parseDouble(input);
                } else {
                    System.err.println("Input must be > 0. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input value is wrong. Try again.");
            }
        }while (true);
    }

    public int inputStockQuantity(Scanner sc){
        System.out.println("Enter stock quantity: ");
        do {
            String input = sc.nextLine();
            try {
                if (Integer.parseInt(input) >= 0) {
                    return Integer.parseInt(input);
                } else {
                    System.err.println("Input must be >= 0. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input value is wrong. Try again.");
            }
        }while (true);
    }

    public String inputImage(Scanner sc){
        System.out.println("Enter product image: ");
        return sc.nextLine();
    }

    public int inputCateId(Scanner sc){
        System.out.println("Enter category id to add: ");
        do {
            String input = sc.nextLine();
            try {
                boolean isExits = false;
                for (int i = 0; i< CategoryService.categoryList.size();i++){
                    if (CategoryService.categoryList.get(i).getCategoryId() == Integer.parseInt(input)){
                        isExits = true;
                        break;
                    }
                }
                if (isExits){
                    return Integer.parseInt(input);
                }else {
                    System.err.println("Can't find product with that id. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input value is wrong. Try again.");
            }
        }while (true);
    }

    public Date inputDateCreated(){
        return new Date();
    }

    public Date inputDateUpdated(){
        return new Date();
    }
}
