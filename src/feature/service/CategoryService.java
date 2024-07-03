package feature.service;

import entity.Category;
import feature.impl.ICategoryFeature;
import utils.IOFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryFeature {
    public static List<Category> categoryList = new ArrayList<>();

    public CategoryService() {
        categoryList = IOFile.readFromFile(IOFile.PATH_CATEGORY);
    }

    @Override
    public void saveOrUpdate(Category category) {
        int indexCheck = findIndexById(category.getCategoryId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            categoryList.add(category);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            categoryList.set(indexCheck, category);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_CATEGORY,categoryList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            boolean isExist = false;
            for (int i = 0; i < ProductService.productsList.size(); i++) {
                if (ProductService.productsList.get(i).getCategoryId() == idDelete) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                categoryList.remove(indexDelete);
                System.out.println("Delete successfully");
                IOFile.writeToFile(IOFile.PATH_CATEGORY,categoryList);
            }else {
                System.out.println("Can't delete category. This category already exits in product list. Try again");
            }
        } else {
            System.err.println("Can't find category with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
