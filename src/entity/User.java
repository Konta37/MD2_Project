package entity;

import feature.service.UserService;
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
    private Byte isDelete; //Account is deleted 1-active 0-be deleted

    public User() {
    }

    public User(int userId, String userName, String email, String fullName, boolean status, String password, String avatar, String phone, String address, Date createDate, Date updateDate, Byte isDelete) {
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

    public Byte isDelete() {
        return isDelete;
    }

    public void setDelete(Byte delete) {
        isDelete = delete;
    }


    @Override
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        this.userId = inputUserId();
        this.userName = inputUserName(sc);
        this.email = inputEmail(sc);
        this.fullName = inputFullName(sc);
        this.status = inputStatus(sc);
        this.password = inputPassword(sc);
        this.avatar = inputAvatar(sc);
        this.phone = inputPhone(sc);
        this.address = inputAddress(sc);
        this.createDate = inputCreateDate(sc);
        this.updateDate = inputUpdateDate(sc);
        this.isDelete = inputDelete(sc);
    }

    @Override
    public void displayData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String isDel = "";
        if (isDelete == 1) {
            isDel = "Active";
        }else if (isDelete == 0) {
            isDel = "Deleted";
        }

        String format = "| %-15s | %-20s | %-30s | %-20s | %-10s | %-20s | %-20s | %-13s | %-30s | %-10s | %-10s | %-10s |\n";
        String separator = "+-----------------+----------------------+--------------------------------+----------------------+------------+----------------------+----------------------+---------------+--------------------------------+------------+------------+------------+\n";

        System.out.print(separator);
        System.out.format(format, "User ID", "User Name", "Email", "Full Name", "Status", "Password", "Avatar", "Phone", "Address", "Create Date", "Update Date", "Is Deleted");
        System.out.print(separator);
        System.out.format(format, userId, userName, email, fullName, status, password, avatar, phone, address,
                (createDate != null ? dateFormat.format(createDate) : "N/A"),
                (updateDate != null ? dateFormat.format(updateDate) : "N/A"),
                (isDel));
        System.out.print(separator);
    }

    private int inputUserId() {
        int max = 0;
        if (UserService.userList.isEmpty()){
            return 0;
        }
        for (int i = 0; i < UserService.userList.size(); i++) {
            if (UserService.userList.get(i).getUserId() > max) {
                max = UserService.userList.get(i).getUserId();
            }
        }
        return max + 1;
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
                System.err.println("Invalid user name (6-100 characters). Try an other one!");
            }
        }while (true);
    }

    public String inputEmail(Scanner sc) {
        System.out.println("Enter email: ");
        String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        do {
            String email = sc.nextLine();
            if (email.matches(regex)) {
                boolean isExist = false;
                for (int i = 0; i< UserService.userList.size(); i++){
                    if (UserService.userList.get(i).getEmail().equals(email)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return email;
                }else {
                    System.err.println("Email already exist. Try an other one!");
                }
                return email;
            }else {
                System.err.println("Invalid email address (xxx@gmail.com). Try an other one!");
            }
        }while (true);
    }

    public String inputFullName(Scanner sc) {
        System.out.println("Enter full name: ");
        do {
            String fullName = sc.nextLine();
            if(!fullName.isEmpty()){
                return fullName;
            }else {
                System.err.println("Invalid full name (can not empty). Try an other one!");
            }
        }while (true);
    }

    public boolean inputStatus(Scanner sc) {
        System.out.println("Enter status: ");
        do {
            String status = sc.nextLine();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            }else {
                System.err.println("Invalid status value (true/false). Try an other one!");
            }
        }while (true);
    }

    public String inputPassword(Scanner sc) {
        System.out.println("Enter password: ");
        do {
            String pass = sc.nextLine();
            if(pass.length()>=5 && pass.length()<=20){
                return pass;
            }else {
                System.err.println("Invalid password (6-20 characters). Try an other one!");
            }
        }while (true);
    }

    public String inputAvatar(Scanner sc) {
        System.out.println("Enter avatar: ");
        return sc.nextLine();
    }

    public String inputPhone(Scanner sc) {
        System.out.println("Enter phone number: ");
        String regex = "0\\d{8}";
        do {
            String phone = sc.nextLine();
            if (phone.matches(regex)) {
                boolean isExist = false;
                for (int i = 0; i< UserService.userList.size(); i++) {
                    if (UserService.userList.get(i).getPhone().equalsIgnoreCase(phone)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return phone;
                }else {
                    System.err.println("Phone number already exist. Try an other one!");
                }
                return phone;
            }else {
                System.err.println("Invalid phone number (0xxxxxxxx) (9 number). Try an other one!");
            }
        }while (true);
    }

    public String inputAddress(Scanner sc) {
        System.out.println("Enter address: ");
        return sc.nextLine();
    }

    public Date inputCreateDate(Scanner sc) {
        return new Date();
    }

    public Date inputUpdateDate(Scanner sc) {
        return new Date();
    }

    public Byte inputDelete(Scanner sc) {
        return 1;
    }
}
