package feature.impl;

import entity.Address;
import entity.Category;

public interface IAddressFeature {
    void saveOrUpdate(Address address);

    void deleteById(int idDelete);

    int findIndexById(int idFind);
}
