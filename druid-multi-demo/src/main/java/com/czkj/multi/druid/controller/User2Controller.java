package com.czkj.multi.druid.controller;

import com.czkj.multi.druid.entity.two.User;
import com.czkj.multi.druid.service.UserOneService;
import com.czkj.multi.druid.service.UserTwoService;
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
public class User2Controller {

    @Autowired
    private UserTwoService userTwoService;


    @GetMapping("user/two/{id}")
    public User getTwo(@PathVariable("id") Integer id){
        User user = userTwoService.getById(id);
        log.info("db two 查找:{},结果",id,user);
        return user;
    }
}
