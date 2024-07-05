package feature.service;

import entity.Address;
import entity.WishList;
import feature.impl.IWishListFeature;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class WishListService implements IWishListFeature {
    public static List<WishList> wishListArrayList = new ArrayList<>();

    static {
        wishListArrayList = IOFile.readFromFile(IOFile.PATH_WISHLIST);
    }

    public WishListService() {
        wishListArrayList = IOFile.readFromFile(IOFile.PATH_WISHLIST);
    }

    @Override
    public void saveOrUpdate(WishList wishList) {
        int indexCheck = findIndexById(wishList.getWishListId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            wishListArrayList.add(wishList);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            wishListArrayList.set(indexCheck, wishList);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_WISHLIST,wishListArrayList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            wishListArrayList.remove(indexDelete);
            System.out.println("Delete successfully");
            IOFile.writeToFile(IOFile.PATH_WISHLIST,wishListArrayList);
        } else {
            System.err.println("Can't find wishList with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < wishListArrayList.size(); i++) {
            if (wishListArrayList.get(i).getWishListId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
