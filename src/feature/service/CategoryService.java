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
            categoryList.remove(indexDelete);
            System.out.println("Đã xóa thành công");
            IOFile.writeToFile(IOFile.PATH_CATEGORY,categoryList);
        } else {
            System.err.println("Không tồn tại id = " + idDelete);
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
