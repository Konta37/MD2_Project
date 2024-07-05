package feature.service;

import entity.Address;
import entity.ShoppingCart;
import feature.impl.IShoppingCart;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService implements IShoppingCart {

    public static List<ShoppingCart> shoppingCartList = new ArrayList<>();

    static {
        shoppingCartList = IOFile.readFromFile(IOFile.PATH_CART);
    }

    public ShoppingCartService() {
        shoppingCartList = IOFile.readFromFile(IOFile.PATH_CART);
    }


    @Override
    public void saveOrUpdate(ShoppingCart shoppingCart) {
        int indexCheck = findIndexById(shoppingCart.getShoppingCartID());
        if (indexCheck < 0) {
            // chức năng thêm mới
            shoppingCartList.add(shoppingCart);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            shoppingCartList.set(indexCheck, shoppingCart);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_CART,shoppingCartList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            shoppingCartList.remove(indexDelete);
            System.out.println("Delete successfully");
            IOFile.writeToFile(IOFile.PATH_ADDRESS,shoppingCartList);
        } else {
            System.err.println("Can't find shopping cart with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < shoppingCartList.size(); i++) {
            if (shoppingCartList.get(i).getShoppingCartID() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
