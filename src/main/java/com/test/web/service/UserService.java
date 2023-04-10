package com.test.web.service;

import com.test.web.entity.Role;
import com.test.web.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectUserByRole(Role role);

    int insertUser(User user);

    int deleteUserByRole(Role role);

    List<User> selectUserByUser(User user);
    int deleteUser(User user);

}
