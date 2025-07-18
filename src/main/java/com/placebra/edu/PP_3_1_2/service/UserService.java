package com.placebra.edu.PP_3_1_2.service;
import com.placebra.edu.PP_3_1_2.entity.User;

import java.util.List;

public interface UserService {

    public void saveUser(User user);
    public User findUserByUsername(String username);
    public List<User> getAllUsers();
    public void removeUserById(int id);
    public User getUserById(int id);

    public void updateUserName(int id, String name);
    public void updateUserEmail(int id, String email);
    public void updateUserPhoneNumber(int id, String phoneNumber);

}
