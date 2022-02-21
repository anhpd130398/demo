package com.mapper.controller;

import com.mapper.mappers.UserMapper;
import com.mapper.model.User;
import com.mapper.model.UserExample;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    UserMapper userMapper;


    @GetMapping("/get-all")
    public List<User> getAll(){
        UserExample userExample=new UserExample();
        return userMapper.selectByExample(userExample);
    }

}
