package feature.service;

import entity.User;
import feature.impl.IUserFeature;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserFeature {
   public static List<User> userList = new ArrayList<User>();

   static {
      userList = IOFile.readFromFile(IOFile.PATH_USER);
   }

   public UserService() {
      userList = IOFile.readFromFile(IOFile.PATH_USER);
   }

   @Override
   public void saveOrUpdate(User user) {
      int indexCheck = findIndexById(user.getUserId());
      if (indexCheck < 0) {
         // chức năng thêm mới
         userList.add(user);
         System.out.println("Add successfully");
      } else {
         // chức năng cập nhật
         userList.set(indexCheck, user);
         System.out.println("Update successfully");
      }
      IOFile.writeToFile(IOFile.PATH_USER,userList);
   }

   @Override
   public void deleteById(int idDelete) {
      int indexDelete = findIndexById(idDelete);
      if (indexDelete >= 0) {
         userList.remove(indexDelete);
         System.out.println("Delete successfully");
         IOFile.writeToFile(IOFile.PATH_ADDRESS,userList);
      } else {
         System.err.println("Can't find user with id = " + idDelete);
      }
   }

   @Override
   public int findIndexById(int idFind) {
      for (int i = 0; i < userList.size(); i++) {
         if (userList.get(i).getUserId() == idFind) {
            return i;
         }
      }
      return -1;
   }
}
