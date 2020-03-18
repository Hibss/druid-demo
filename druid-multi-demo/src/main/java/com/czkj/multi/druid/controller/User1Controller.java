package com.czkj.multi.druid.controller;

import com.czkj.multi.druid.entity.one.User;
import com.czkj.multi.druid.service.UserOneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author steven.sheng
 * @Date 2020/3/17/01711:06
 */
@RestController
@Slf4j
public class User1Controller {

    @Autowired
    private UserOneService userOneService;

    @GetMapping("user/one/{id}")
    public User getOne(@PathVariable("id") Integer id){
        User user = userOneService.getById(id);
        log.info("db one 查找:{},结果",id,user);
        return user;
    }
}
