package feature.service;

import entity.Products;
import feature.impl.IProductFeature;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductFeature {
    public static List<Products> productsList = new ArrayList<Products>();

    static {
        productsList = IOFile.readFromFile(IOFile.PATH_PRODUCT);
    }
    
    public ProductService() {
        productsList = IOFile.readFromFile(IOFile.PATH_PRODUCT);
    }

    @Override
    public void saveOrUpdate(Products products) {
        int indexCheck = findIndexById(products.getProductId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            productsList.add(products);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            productsList.set(indexCheck, products);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_PRODUCT,productsList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            productsList.remove(indexDelete);
            System.out.println("Delete successfully");
            IOFile.writeToFile(IOFile.PATH_PRODUCT,productsList);
        } else {
            System.err.println("Can't find product with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getProductId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
