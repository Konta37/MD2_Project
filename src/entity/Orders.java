package entity;

import feature.service.OrderService;
import feature.service.ProductService;
import feature.service.UserService;
import utils.IOData;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Orders implements IOData {
    private int orderId;
    private String serialNumber; //random UUID
    private int userId; //cant null
    private double totalPrice;

    private enum status {
        WAITING,
        CONFIRM,
        DELIVERY,
        SUCCESS,
        DENIED,
    }

    private String note;
    private String receiveName;
    private String receivePhone;
    private Date dateCreated;
    private Date dateReceived;

    public Orders() {
    }

    public Orders(int orderId, String serialNumber, int userId, double totalPrice, String note, String receiveName, String receivePhone, Date dateCreated, Date dateReceived) {
        this.orderId = orderId;
        this.serialNumber = serialNumber;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.note = note;
        this.receiveName = receiveName;
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
        Scanner sc = new Scanner(System.in);
    }

    @Override
    public void displayData() {

    }

    private int inputOrderId() {
        int max = 0;
        if (!OrderService.ordersList.isEmpty()) {
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

//    public double inputTotalPrice(Scanner sc){
//        System.out.println("Please enter the total price: ");
//        do{
//            String totalPrice = sc.nextLine();
//            try {
//                if
//            }catch (NumberFormatException e){
//                System.err.println("Total price is not a number. Try again.");
//            }
//        }while (true);
//    }
}
