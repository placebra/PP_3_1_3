package com.placebra.edu.PP_3_1_2.service;

import com.placebra.edu.PP_3_1_2.dao.RoleDao;
import com.placebra.edu.PP_3_1_2.dao.UserDao;
import com.placebra.edu.PP_3_1_2.entity.Role;
import com.placebra.edu.PP_3_1_2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private RoleDao roleDao;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional //Поиск юзера по email
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional
    public void removeUserById(int id) {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(String firstName, String lastName, int age, String email, String password, String role) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        if (role.equals("Admin")) {
            user.setRoles(roleDao.getAllRoles());
        } else if (role.equals("User")) {
            user.setRoles(List.of(roleDao.getUserRole()));
        }

        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUserInfo(int id, String firstName, String lastName, int age, String email, String role, String password) {

        if (!password.isEmpty()) {
            password = passwordEncoder.encode(password);
        }

        userDao.updateUserInfo(id, firstName, lastName, age, email, role, password);
    }

}
