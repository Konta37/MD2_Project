package feature.service;

import entity.User;
import feature.impl.IUserFeature;
import utils.IOFile;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserFeature {
   public static List<User> userList = new ArrayList<User>();

   public UserService() {
      userList = IOFile.readFromFile(IOFile.PATH_USER);
   }

   @Override
   public void saveOrUpdate(User user) {

   }

   @Override
   public void deleteById(int idDelete) {

   }

   @Override
   public int findIndexById(int idFind) {
      return 0;
   }
}
