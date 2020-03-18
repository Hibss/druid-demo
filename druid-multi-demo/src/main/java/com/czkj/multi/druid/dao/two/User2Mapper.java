package com.czkj.multi.druid.dao.two;

import com.czkj.multi.druid.entity.two.User;

public interface User2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}