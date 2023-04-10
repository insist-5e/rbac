package com.test.web.service.Impl;

import com.test.web.dao.RoleMapper;
import com.test.web.dao.RoleRightRelationMapper;
import com.test.web.dao.UserMapper;
import com.test.web.dao.UserRoleRelationMapper;
import com.test.web.entity.*;
import com.test.web.service.RightService;
import com.test.web.service.RoleService;
import com.test.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserRoleRelationMapper userRoleRelationMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;
    @Resource
    RightService rightService;
    @Resource
    RoleRightRelationMapper roleRightRelationMapper;
    public List<Role> selectRoleByUser(User user){
        List<User> trueUser = userService.selectUserByUser(user);
        if(trueUser.isEmpty()) return new ArrayList<>();
        UserRoleRelationExample userRoleRelationExample = new UserRoleRelationExample();
        UserRoleRelationExample.Criteria criteria = userRoleRelationExample.createCriteria();
        criteria.andUserIdEqualTo(trueUser.get(0).getId());
        List<UserRoleRelation> userRoleRelations = userRoleRelationMapper.selectByExample(userRoleRelationExample);
        List<Long> ids = new ArrayList<>();
        for(UserRoleRelation userRoleRelation : userRoleRelations){
            ids.add(userRoleRelation.getRoleId());
        }
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria roleCriteria = roleExample.createCriteria();
        roleCriteria.andIdIn(ids);
        return roleMapper.selectByExample(roleExample);
    }

    public int deleteRoleByUser(User user){
        List<User> trueUser = userService.selectUserByUser(user);
        if(trueUser.isEmpty()) return -1;
        UserRoleRelationExample userRoleRelationExample = new UserRoleRelationExample();
        UserRoleRelationExample.Criteria criteria = userRoleRelationExample.createCriteria();
        criteria.andUserIdEqualTo(trueUser.get(0).getId());
        return userRoleRelationMapper.deleteByExample(userRoleRelationExample);
    }
    public List<Role> selectRoleByRight(Right right){
        List<Right> trueRight = rightService.selectRightByRight(right);
        if(trueRight.isEmpty()) return new ArrayList<>();
        RoleRightRelationExample roleRightRelationExample = new RoleRightRelationExample();
        RoleRightRelationExample.Criteria criteria = roleRightRelationExample.createCriteria();
        criteria.andRightIdEqualTo(trueRight.get(0).getId());
        List<RoleRightRelation> roleRightRelations = roleRightRelationMapper.selectByExample(roleRightRelationExample);
        List<Long> ids = new ArrayList<>();
        for(RoleRightRelation roleRightRelation : roleRightRelations){
            ids.add(roleRightRelation.getRoleId());
        }
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria roleCriteria = roleExample.createCriteria();
        roleCriteria.andIdIn(ids);
        return roleMapper.selectByExample(roleExample);
    }

    public List<Role> selectRoleByRole(Role role){
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria roleCriteria = roleExample.createCriteria();
        roleCriteria.andNameEqualTo(role.getName());
        return roleMapper.selectByExample(roleExample);
    }
    public int insertRole(Role role){
        return roleMapper.insert(role);
    }

    public int insertRoleByUser(User user, Role role){
        List<User> trueUser = userService.selectUserByUser(user);
        List<Role> trueRole = selectRoleByRole(role);
        if(trueRole.isEmpty() || trueUser.isEmpty()) return -1;
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        userRoleRelation.setUserId(trueUser.get(0).getId());
        userRoleRelation.setRoleId(trueRole.get(0).getId());
        return userRoleRelationMapper.insert(userRoleRelation);
    }

    public int deleteRoleByRight(Right right){
        List<Right> trueRight = rightService.selectRightByRight(right);
        if(trueRight.isEmpty()) return -1;
        RoleRightRelationExample roleRightRelationExample = new RoleRightRelationExample();
        RoleRightRelationExample.Criteria criteria = roleRightRelationExample.createCriteria();
        criteria.andRightIdEqualTo(trueRight.get(0).getId());
        return roleRightRelationMapper.deleteByExample(roleRightRelationExample);
    }

    public int deleteRole(Role role){
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andNameEqualTo(role.getName());
        int ans1 = userService.deleteUserByRole(role);
        int ans2 = rightService.deleteRightByRole(role);
        if(ans1 + ans2 == -2) return -1;
        return roleMapper.deleteByExample(roleExample);
    }
}
