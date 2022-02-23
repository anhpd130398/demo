package com.mapper.controller;

import com.mapper.basereponse.BaseReponse;
import com.mapper.mappers.UserMapper;
import com.mapper.model.User;
import com.mapper.model.UserExample;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;


    @GetMapping("/get-all")
    public List<User> getAll() {
        UserExample userExample = new UserExample();
        return userMapper.selectByExample(userExample);
    }

    @PostMapping ("/select-all")
    public List<Map<String, Object>> get(@RequestParam("name") String name) {
        List<Map<String, Object>> list = userMapper.getAll(name);
        return list;
    }

    @PostMapping("/insert-user")
    public BaseReponse addUser(@RequestBody User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        userExample.createCriteria().andEmailEqualTo(user.getEmail());
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size() > 0) {
            return new BaseReponse(400, "email or name is exist");
        }
        userMapper.insert(user);
        return new BaseReponse(200, "insert success");
    }

    @GetMapping("/get-id")
    public User getPriId(@RequestParam("id") int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/update-user")
    public BaseReponse updateUser(@RequestBody User user) throws Exception {
        UserExample userExample = new UserExample();
        userExample.createCriteria().
                andIdEqualTo(user.getId());
        userMapper.updateByExampleSelective(user, userExample);
        return new BaseReponse(200, "update success");

    }

}
