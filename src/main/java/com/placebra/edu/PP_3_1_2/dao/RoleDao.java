package com.placebra.edu.PP_3_1_2.dao;

import com.placebra.edu.PP_3_1_2.entity.Role;

import java.util.List;

public interface RoleDao {

    public Role getUserRole();
    public Role getAdminRole();
    public List<Role> getAllRoles();
}
