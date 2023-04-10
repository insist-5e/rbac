package com.test.web.service;

import com.test.web.entity.Right;
import com.test.web.entity.Role;
import com.test.web.entity.User;

import java.util.List;

public interface RightService {
    int insertRight(Right right);

    int insertRightByRole(Role role, Right right);

    int deleteRightByRole(Role role);

    List<Right> selectRightByRole(Role role);

    List<Right> selectRightByUser(User user);

    int deleteRight(Right right);

    List<Right> selectRightByRight(Right right);
}
