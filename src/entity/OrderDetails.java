package entity;

import utils.IOData;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class OrderDetails implements IOData, Serializable {
    private int orderDetailId;
    private int productId;
    private String productName;
    private double unitPrice;
    private int orderQuantity; //>0

    public OrderDetails() {
    }

    public OrderDetails(int orderDetailId, int productId, String productName, double unitPrice, int orderQuantity) {
        this.orderDetailId = orderDetailId;
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.orderQuantity = orderQuantity;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public void inputData() {

    }

    @Override
    public void displayData() {
        // Currency formatter for VND
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String format = "| %-15s | %-15s | %-30s | %-15s | %-15s |\n";
        String separator = "+-----------------+-----------------+-------------------------------+-----------------+-----------------+\n";
        System.out.print(separator);
        System.out.printf(format, "Order ID", "Product ID", "Name", "Unit Price", "Order Quantity");
        System.out.print(separator);
        System.out.printf(format, orderDetailId, productId, productName, vndFormat.format(unitPrice), orderQuantity);
        System.out.print(separator);
    }
}
