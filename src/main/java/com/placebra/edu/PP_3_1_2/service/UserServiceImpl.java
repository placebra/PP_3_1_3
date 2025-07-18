package com.placebra.edu.PP_3_1_2.service;

import com.placebra.edu.PP_3_1_2.dao.RoleDao;
import com.placebra.edu.PP_3_1_2.dao.UserDao;
import com.placebra.edu.PP_3_1_2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void removeUserById(int id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void updateUserName(int id, String name) {
        userDao.updateUserName(id, name);
    }

    @Transactional
    @Override
    public void updateUserEmail(int id, String email) {
        userDao.updateUserEmail(id, email);
    }

    @Transactional
    @Override
    public void updateUserPhoneNumber(int id, String phoneNumber) {
        userDao.updateUserPhoneNumber(id, phoneNumber);
    }
}
