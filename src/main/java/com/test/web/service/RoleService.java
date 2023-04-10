package com.test.web.service;

import com.test.web.entity.Right;
import com.test.web.entity.Role;
import com.test.web.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> selectRoleByUser(User user);

    List<Role> selectRoleByRight(Right right);

    int insertRole(Role role);

    int insertRoleByUser(User user, Role role);

    int deleteRoleByUser(User user);

    List<Role> selectRoleByRole(Role role);

    int deleteRoleByRight(Right right);
    int deleteRole(Role role);
}
