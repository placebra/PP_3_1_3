package com.placebra.edu.PP_3_1_2.service;
import com.placebra.edu.PP_3_1_2.entity.User;

import java.util.List;

public interface UserService {

    public User findUserByEmail(String email);
    public List<User> findAllUsers();
    public void removeUserById(int id);
    public void saveUser(User user);
    public void updateUserInfo(int id, String firstName, String lastName, int age, String email, String role);

}
