package feature.impl;

import entity.Category;
import entity.Products;

public interface IProductFeature {
    void saveOrUpdate(Products products);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
