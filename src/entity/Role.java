package entity;

import feature.service.ProductService;
import feature.service.RoleService;
import utils.IOData;

import java.io.Serializable;
import java.util.Scanner;

public class Role implements IOData,Serializable {
    private int roleId; //Auto
    private RoleName roleName;

    public Role() {
    }

    public Role(int roleId, RoleName roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }


    @Override
    public void inputData() {
        Scanner scanner = new Scanner(System.in);
        this.roleId =inputRoleId();
        this.roleName = inputRoleName(scanner);
    }

    @Override
    public void displayData() {
        System.out.println("Role ID: " + this.roleId + ", Role Name: " + this.roleName);
    }

    public int inputRoleId(){
        int max = 0;
        if (RoleService.roleList.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < RoleService.roleList.size(); i++) {
            if (RoleService.roleList.get(i).getRoleId() > max) {
                max = RoleService.roleList.get(i).getRoleId();
            }
        }
        return max + 1;
    }
    public RoleName inputRoleName(Scanner sc) {
        RoleName roleName = null;
        while (roleName == null) {
            System.out.print("Enter Role Name (ADMIN, USER, MANAGER): ");
            try {
                roleName = RoleName.valueOf(sc.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role name. Please enter a valid role name.");
            }
        }
        return roleName;
    }

}
