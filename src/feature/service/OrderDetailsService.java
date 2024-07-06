package feature.service;

import entity.OrderDetails;
import feature.impl.IOrderDetailsFeature;
import utils.IOFile;

import java.util.List;

public class OrderDetailsService implements IOrderDetailsFeature {
    public static List<OrderDetails> orderDetailsList;
    
    static {
        orderDetailsList = IOFile.readFromFile(IOFile.PATH_ORDER_DETAILS);
    }
    
    public OrderDetailsService() {
        orderDetailsList = IOFile.readFromFile(IOFile.PATH_ORDER_DETAILS);
    }
    
    @Override
    public void saveOrUpdate(OrderDetails orderDetails) {
        int indexCheck = findIndexById(orderDetails.getOrderId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            orderDetailsList.add(orderDetails);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            orderDetailsList.set(indexCheck, orderDetails);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_ORDER_DETAILS,orderDetailsList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            orderDetailsList.remove(indexDelete);
            System.out.println("Delete successfully");
            IOFile.writeToFile(IOFile.PATH_ORDER_DETAILS,orderDetailsList);
        } else {
            System.err.println("Can't find order detail with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < orderDetailsList.size(); i++) {
            if (orderDetailsList.get(i).getOrderId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
