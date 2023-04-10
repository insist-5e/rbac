package com.test.web.service.Impl;

import com.test.web.dao.RoleMapper;
import com.test.web.dao.UserMapper;
import com.test.web.dao.UserRoleRelationMapper;
import com.test.web.entity.*;
import com.test.web.service.RoleService;
import com.test.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    UserRoleRelationMapper userRoleRelationMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleService roleService;

    public List<User> selectUserByRole(Role role){
        List<Role> trueRole = roleService.selectRoleByRole(role);
        if(trueRole.isEmpty()) return new ArrayList<>();
        UserRoleRelationExample userRoleRelationExample = new UserRoleRelationExample();
        UserRoleRelationExample.Criteria criteria = userRoleRelationExample.createCriteria();
        criteria.andRoleIdEqualTo(trueRole.get(0).getId());
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectByExample(userRoleRelationExample);
        List<Long> ids = new ArrayList<>();
        for(UserRoleRelation userRoleRelation : userRoleRelations){
            ids.add(userRoleRelation.getUserId());
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andIdIn(ids);
        return userMapper.selectByExample(userExample);
    }

    public int insertUser(User user){
        return userMapper.insert(user);
    }

    public List<User> selectUserByUser(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andNameEqualTo(user.getName());
        return userMapper.selectByExample(userExample);
    }

    public int deleteUserByRole(Role role){
        List<Role> trueRole = roleService.selectRoleByRole(role);
        if(trueRole.isEmpty()) return -1;
        UserRoleRelationExample userRoleRelationExample = new UserRoleRelationExample();
        UserRoleRelationExample.Criteria criteria = userRoleRelationExample.createCriteria();
        criteria.andRoleIdEqualTo(trueRole.get(0).getId());
        return userRoleRelationMapper.deleteByExample(userRoleRelationExample);
    }

    public int deleteUser(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(user.getName());
        int ans = roleService.deleteRoleByUser(user);
        if(ans == -1) return -1;
        return userMapper.deleteByExample(userExample);
    }
}
