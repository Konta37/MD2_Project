package entity;

import feature.ProductService;
import utils.IOData;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Products implements IOData {
    private int productId;
    private UUID sku;
    private String productName;
    private String description;
    private double unitPrice; //price
    private int stockQuantity; //>=0
    private String productImage;
    private Date dateCreated;
    private Date dateUpdated;

    public Products() {
    }

    public Products(int productId, UUID sku, String productName, String description, double unitPrice, int stockQuantity, String productImage, Date dateCreated, Date dateUpdated) {
        this.productId = productId;
        this.sku = sku;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.productImage = productImage;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public UUID getSku() {
        return sku;
    }

    public void setSku(UUID sku) {
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

    }

    @Override
    public void displayData() {

    }

    private int inputProductId(){
        int max=-1;
        if (!ProductService.productsList.isEmpty()){
            return 0;
        }

        for(int i=0;i<ProductService.productsList.size();i++){
            if (ProductService.productsList.get(i).getProductId()>=max){
                max=ProductService.productsList.get(i).getProductId();
            }
        }
        if(max>=0){
            return max+1;
        }else {
            return 0;
        }
    }

    public UUID inputSku(){
        // Tạo một UUID ngẫu nhiên
        UUID sku;
        boolean isExist=false;
        do {
            // Tạo một UUID ngẫu nhiên
            sku = UUID.randomUUID();
            isExist = false;

            // Kiểm tra xem SKU đã tồn tại chưa
            for (Products product : ProductService.productsList) {
                if (product.getSku().equals(sku)) {
                    isExist = true;
                    break;
                }
            }
        } while (isExist); // Nếu SKU tồn tại, tiếp tục tạo UUID mới

        return sku;
    }

    public String inputProductName(Scanner sc){
        System.out.println("Enter product name: ");
        do {
            String input = sc.nextLine();
            if (!input.isEmpty()){
                boolean isExist=false;
                for (Products product : ProductService.productsList) {
                    if (product.getProductName().equalsIgnoreCase(input)){
                        isExist=true;
                        break;
                    }
                }
                if (!isExist){
                    return input;
                }else {
                    System.err.println("Duplicate product name! Try again!");
                }
            }else {
                System.err.println("Invalid input (Cannot be empty). Try again!");
            }
        }while (true);
    }
}
