package entity;

import feature.service.OrderService;
import feature.service.ProductService;
import feature.service.UserService;
import utils.IOData;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class Orders implements IOData, Serializable {
    private int orderId;
    private String serialNumber; //random UUID
    private int userId; //cant null
    private double totalPrice;
    private OrderStatus orderStatus;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date dateCreated;
    private Date dateReceived;

    public Orders() {
    }

    public Orders(int orderId, String serialNumber, int userId, double totalPrice, OrderStatus orderStatus, String note, String receiveName, String receiveAddress, String receivePhone, Date dateCreated, Date dateReceived) {
        this.orderId = orderId;
        this.serialNumber = serialNumber;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.note = note;
        this.receiveName = receiveName;
        this.receiveAddress = receiveAddress;
        this.receivePhone = receivePhone;
        this.dateCreated = dateCreated;
        this.dateReceived = dateReceived;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    @Override
    public void inputData() {
        //do nothing
    }

    @Override
    public void displayData() {
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // First line format
        String formatLine1 = "| %-20s | %-43s | %-20s | %-20s | %-20s |\n";
        String separatorLine1 = "+----------------------+---------------------------------------------+----------------------+----------------------+----------------------+\n";
        //Second line format
        String formatLine2 = "| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n";
        String separatorLine2 = "+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+\n";

        System.out.print(separatorLine1);
        System.out.printf(formatLine1, "Order ID", "Serial Number", "User ID", "Total Price", "Order Status");
        System.out.print(separatorLine1);
        System.out.printf(formatLine1, orderId, serialNumber, userId, vndFormat.format(totalPrice), orderStatus);
        System.out.print(separatorLine1);

        System.out.print(separatorLine2);
        System.out.printf(formatLine2, "Note", "Receive Name", "Receive Address", "Receive Phone", "Date Created", "Date Received");
        System.out.print(separatorLine2);
        System.out.printf(formatLine2, note, receiveName, receiveAddress, receivePhone, sdf.format(dateCreated), sdf.format(dateReceived));
        System.out.print(separatorLine2);
    }

    public void displayUserOrder(int userId){
        if (userId == this.userId){
            displayData();
        }
    }

    public int inputOrderId() {
        int max = 0;
        if (OrderService.ordersList.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < OrderService.ordersList.size(); i++) {
            if (OrderService.ordersList.get(i).getOrderId() >= max) {
                max = OrderService.ordersList.get(i).getOrderId();
            }
        }
        return max + 1;
    }

    public String inputSerialNumber() {
        // Tạo một UUID ngẫu nhiên
        UUID sku;
        String skuString = "";
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

    public int inputUserId(Scanner sc) {
        System.out.println("Please enter the user id: ");
        do {
            String userId = sc.nextLine();
            try {
                boolean isExist = false;
                for (User product : UserService.userList) {
                    if (product.getUserId() == Integer.parseInt(userId)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    return Integer.parseInt(userId);
                } else {
                    System.err.println("User not found. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("User id is not a number. Try again.");
            }
        } while (true);
    }

    //Auto Waiting. Change at update order by admin
    public OrderStatus inputOrderStatus() {
        return OrderStatus.valueOf("WAITING");
    }
}
