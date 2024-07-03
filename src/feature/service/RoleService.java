package feature.service;

import entity.Products;
import entity.Role;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class RoleService {
    public static List<Role> roleList = new ArrayList<Role>();
    public RoleService() {
        roleList = IOFile.readFromFile(IOFile.PATH_ROLE);
    }

    public void saveOrUpdate(Role roles) {
        int indexCheck = findIndexById(roles.getRoleId());
        if (indexCheck < 0) {
            // chức năng thêm mới
            roleList.add(roles);
            System.out.println("Add successfully");
        } else {
            // chức năng cập nhật
            roleList.set(indexCheck, roles);
            System.out.println("Update successfully");
        }
        IOFile.writeToFile(IOFile.PATH_ROLE,roleList);
    }
    public int findIndexById(int idFind) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getRoleId() == idFind) {
                return i;
            }
        }
        return -1;
    }
}
