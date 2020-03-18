package com.czkj.druid.controller;

import com.czkj.druid.dao.UserMapper;
import com.czkj.druid.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author steven.sheng
 * @Date 2020/3/16/01616:27
 */
@RestController
@Slf4j
public class ApiController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("api/get")
    public User getUserById(@RequestParam("id") Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        log.info("查找用户id:{} ,结果:{} ",id,user);
        return user;
    }
}
