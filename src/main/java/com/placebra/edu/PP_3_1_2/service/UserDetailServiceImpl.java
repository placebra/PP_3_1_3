package com.placebra.edu.PP_3_1_2.service;

import com.placebra.edu.PP_3_1_2.dao.UserDao;
import com.placebra.edu.PP_3_1_2.dto.CustomUserDetails;
import com.placebra.edu.PP_3_1_2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserDao userDao; // Подставится наша реализация UserDao

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Not found user: " + email);
        }
        return new CustomUserDetails(user);
    }
}
