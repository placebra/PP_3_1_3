package com.placebra.edu.PP_3_1_2.service;

import com.placebra.edu.PP_3_1_2.dao.RoleDao;
import com.placebra.edu.PP_3_1_2.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getUserRole() {
        return roleDao.getUserRole();
    }

    @Transactional
    @Override
    public Role getAdminRole() {
        return roleDao.getAdminRole();
    }

}
