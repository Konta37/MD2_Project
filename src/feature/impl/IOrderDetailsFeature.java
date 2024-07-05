package feature.impl;

import entity.OrderDetails;

public interface IOrderDetailsFeature {
    void saveOrUpdate(OrderDetails orderDetails);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
