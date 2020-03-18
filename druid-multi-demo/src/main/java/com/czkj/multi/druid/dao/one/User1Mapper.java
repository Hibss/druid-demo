package com.czkj.multi.druid.dao.one;

import com.czkj.multi.druid.entity.one.User;

public interface User1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}