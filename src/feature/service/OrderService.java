package feature.service;

import entity.Orders;
import feature.impl.IOrderFeature;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderFeature {
    public static List<Orders> ordersList = new ArrayList<Orders>();
    
    static {
        ordersList = IOFile.readFromFile(IOFile.PATH_ORDER);
    }
    
    public OrderService() {
        ordersList = IOFile.readFromFile(IOFile.PATH_ORDER);
    }

    @Override
    public void saveOrUpdate(Orders order) {
        int indexCheck = findIndexById(order.getOrderId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            ordersList.add(order);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            ordersList.set(indexCheck, order);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_ADDRESS,ordersList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            ordersList.remove(indexDelete);
            System.out.println("Delete successfully");
            IOFile.writeToFile(IOFile.PATH_ADDRESS,ordersList);
        } else {
            System.err.println("Can't find order with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < ordersList.size(); i++) {
            if (ordersList.get(i).getOrderId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
