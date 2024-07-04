package feature.impl;

import entity.Orders;
import entity.WishList;

public interface IWishListFeature {
    void saveOrUpdate(WishList wishList);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
