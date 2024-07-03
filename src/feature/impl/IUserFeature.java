package feature.impl;

import entity.Products;
import entity.User;

public interface IUserFeature {
    void saveOrUpdate(User user);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
