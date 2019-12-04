package com.person.test.demo_producer.service.impl;

import com.person.test.demo_producer.mapper.UserMapper;
import com.person.test.demo_producer.pojo.User;
import com.person.test.demo_producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {

        return userMapper.selectAll();
    }
}
