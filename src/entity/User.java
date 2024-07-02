package entity;

import feature.UserService;
import utils.IOData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class User implements IOData {
    private int userId;
    private String userName;
    private String email;
    private String fullName;
    private boolean status;
    private String password;
    private String avatar;
    private String phone;
    private String address;
    private Date createDate;
    private Date updateDate;
    private boolean isDelete; //Account is deleted true-active false-be deleted

    public User() {
    }

    public User(int userId, String userName, String email, String fullName, boolean status, String password, String avatar, String phone, String address, Date createDate, Date updateDate, boolean isDelete) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.status = status;
        this.password = password;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.isDelete = isDelete;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }


    @Override
    public void inputData() {

    }

    @Override
    public void displayData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    }

    private int inputUserId() {
        int max = -1;
        for (int i = 0; i < UserService.userList.size(); i++) {
            if (UserService.userList.get(i).getUserId() > max) {
                max = UserService.userList.get(i).getUserId();
            }
        }
        if (max == -1) {
            return 0;
        } else {
            return max + 1;
        }
    }

    public String inputUserName(Scanner sc) {
        System.out.println("Enter user name: ");
        do {
            String userName = sc.nextLine();
            if (userName.length()>=6 && userName.length()<=100) {
                boolean isExist = false;
                for (int i = 0; i < UserService.userList.size(); i++) {
                    if (UserService.userList.get(i).getUserName().equalsIgnoreCase(userName)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return userName;
                }else {
                    System.err.println("Username already exist. Try an other one!");
                }
            }else {
                System.err.println("Invalid user name (6-100 characters)");
            }
        }while (true);
    }

//    public String inputEmail(Scanner sc) {
//        System.out.println("Enter email: ");
//        String regex =
//    }
}
