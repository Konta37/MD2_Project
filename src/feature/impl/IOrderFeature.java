package feature.impl;

import entity.Address;
import entity.Orders;

public interface IOrderFeature {
    void saveOrUpdate(Orders order);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
