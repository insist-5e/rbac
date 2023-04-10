package com.test.web.dao;

import com.test.web.entity.RoleRightRelation;
import com.test.web.entity.RoleRightRelationExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RoleRightRelationMapper {
    long countByExample(RoleRightRelationExample example);

    int deleteByExample(RoleRightRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoleRightRelation record);

    int insertSelective(RoleRightRelation record);

    List<RoleRightRelation> selectByExample(RoleRightRelationExample example);

    RoleRightRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoleRightRelation record, @Param("example") RoleRightRelationExample example);

    int updateByExample(@Param("record") RoleRightRelation record, @Param("example") RoleRightRelationExample example);

    int updateByPrimaryKeySelective(RoleRightRelation record);

    int updateByPrimaryKey(RoleRightRelation record);
}