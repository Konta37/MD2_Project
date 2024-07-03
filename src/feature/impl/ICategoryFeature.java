package feature.impl;

import entity.Category;

public interface ICategoryFeature {
    void saveOrUpdate(Category category);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
