package feature.service;

import entity.Address;
import entity.Category;
import feature.impl.IAddressFeature;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class AddressService implements IAddressFeature {

    public static List<Address> addressList = new ArrayList<>();

    static {
        addressList = IOFile.readFromFile(IOFile.PATH_ADDRESS);
    }

    public AddressService() {
        addressList = IOFile.readFromFile(IOFile.PATH_ADDRESS);
    }
    
    @Override
    public void saveOrUpdate(Address address) {
        int indexCheck = findIndexById(address.getAddressId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            addressList.add(address);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            addressList.set(indexCheck, address);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_ADDRESS,addressList);
    }

    @Override
    public void deleteById(int idDelete) {
        int indexDelete = findIndexById(idDelete);
        if (indexDelete >= 0) {
            addressList.remove(indexDelete);
            System.out.println("Delete successfully");
            IOFile.writeToFile(IOFile.PATH_ADDRESS,addressList);
        } else {
            System.err.println("Can't find address with id = " + idDelete);
        }
    }

    @Override
    public int findIndexById(int idFind) {
        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).getAddressId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
