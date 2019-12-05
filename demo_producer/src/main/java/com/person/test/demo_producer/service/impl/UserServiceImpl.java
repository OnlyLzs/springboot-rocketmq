package com.person.test.demo_producer.service.impl;

import com.person.test.demo_producer.annotation.CurDataSource;
import com.person.test.demo_producer.enums.DataSourceEnum;
import com.person.test.demo_producer.mapper.UserMapper;
import com.person.test.demo_producer.pojo.User;
import com.person.test.demo_producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    @Resource
    private UserMapper userMapper;

    @Override
    @CurDataSource(name = DataSourceEnum.DBOne)
    public List<User> queryAll() {
        return userMapper.selectAll();
    }

    @Override
    @CurDataSource(name = DataSourceEnum.DBTwo)
    public List<User> queryAllByDBTwo() {
        return userMapper.selectAll();
    }
}
