package feature.impl;

import entity.Products;
import entity.ShoppingCart;

public interface IShoppingCart {
    void saveOrUpdate(ShoppingCart shoppingCart);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
