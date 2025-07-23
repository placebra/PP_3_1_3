package com.placebra.edu.PP_3_1_2.service;

import com.placebra.edu.PP_3_1_2.entity.Role;

import java.util.List;

public interface RoleService {

    public Role getUserRole();
    public Role getAdminRole();
    public List<Role> getAllRoles();

}
