package com.mapper.controller;

import com.mapper.basereponse.BaseReponse;
import com.mapper.mappers.UserMapper;
import com.mapper.model.User;
import com.mapper.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    UserMapper userMapper;


    @GetMapping("/get-all")
    public List<User> getAll() {
        UserExample userExample = new UserExample();
        return userMapper.selectByExample(userExample);
    }

    @PostMapping("/insert-user")
    public BaseReponse addUser(@RequestBody User user)  {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(user.getName());
        userExample.createCriteria().andEmailEqualTo(user.getEmail());
        List<User> list=userMapper.selectByExample(userExample);
        if (list.size()>0){
            return new BaseReponse(401,"email or name is exist");
        }
        userMapper.insert(user);
        return new BaseReponse(200,"insert success");
    }

    @GetMapping("/get-id")
    public User getPriId(@RequestParam("id") int id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
