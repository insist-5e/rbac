package com.test.web.service.Impl;

import com.test.web.dao.RightMapper;
import com.test.web.dao.RoleRightRelationMapper;
import com.test.web.entity.*;
import com.test.web.service.RightService;
import com.test.web.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RightServiceImpl implements RightService {
    @Resource
    RightMapper rightMapper;
    @Resource
    RoleService roleService;
    @Resource
    RoleRightRelationMapper roleRightRelationMapper;
    public int insertRight(Right right){
        return rightMapper.insert(right);
    }

    public int insertRightByRole(Role role, Right right){
        List<Right> trueRight = selectRightByRight(right);
        List<Role> trueRole = roleService.selectRoleByRole(role);
        if(trueRight.isEmpty() || trueRole.isEmpty()) return -1;
        RoleRightRelation roleRightRelation = new RoleRightRelation();
        roleRightRelation.setRightId(trueRight.get(0).getId());
        roleRightRelation.setRoleId(trueRole.get(0).getId());
        return roleRightRelationMapper.insert(roleRightRelation);
    }

    public int deleteRightByRole(Role role){
        List<Role> trueRole = roleService.selectRoleByRole(role);
        if(trueRole.isEmpty()) return -1;
        RoleRightRelationExample roleRightRelationExample = new RoleRightRelationExample();
        RoleRightRelationExample.Criteria criteria = roleRightRelationExample.createCriteria();
        criteria.andRoleIdEqualTo(trueRole.get(0).getId());
        return roleRightRelationMapper.deleteByExample(roleRightRelationExample);
    }

    public List<Right> selectRightByRole(Role role){
        List<Role> trueRole = roleService.selectRoleByRole(role);
        if(trueRole.isEmpty()) return new ArrayList<>();
        RoleRightRelationExample roleRightRelationExample = new RoleRightRelationExample();
        RoleRightRelationExample.Criteria criteria = roleRightRelationExample.createCriteria();
        criteria.andRoleIdEqualTo(trueRole.get(0).getId());
        List<RoleRightRelation> roleRightRelations = roleRightRelationMapper.selectByExample(roleRightRelationExample);
        List<Long> ids = new ArrayList<>();
        for(RoleRightRelation roleRightRelation : roleRightRelations){
            ids.add(roleRightRelation.getRightId());
        }
        RightExample rightExample = new RightExample();
        RightExample.Criteria rightCriteria = rightExample.createCriteria();
        rightCriteria.andIdIn(ids);
        return rightMapper.selectByExample(rightExample);
    }

    public List<Right> selectRightByUser(User user){
        Set<Right> set = new HashSet<>();
        List<Role> roles = roleService.selectRoleByUser(user);
        for(Role role:roles){
            set.addAll(selectRightByRole(role));
        }
        List<Right> ans = new ArrayList<>();
        ans.addAll(set);
        return ans;
    }

    public List<Right> selectRightByRight(Right right){
        RightExample rightExample = new RightExample();
        RightExample.Criteria rightCriteria = rightExample.createCriteria();
        rightCriteria.andNameEqualTo(right.getName());
        return rightMapper.selectByExample(rightExample);
    }
    public int deleteRight(Right right){
        RightExample rightExample = new RightExample();
        RightExample.Criteria criteria = rightExample.createCriteria();
        criteria.andNameEqualTo(right.getName());
        int ans = roleService.deleteRoleByRight(right);
        if(ans == -1) return -1;
        return rightMapper.deleteByExample(rightExample);
    }
}
