package com.czkj.multi.druid.service;

import com.czkj.multi.druid.dao.one.User1Mapper;
import com.czkj.multi.druid.entity.one.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author steven.sheng
 * @Date 2020/3/17/01711:05
 */
@Service
public class UserOneService {

    @Autowired
    private User1Mapper userMapper;


    public User getById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
