package com.person.test.demo_producer.service.impl;

import com.person.test.demo_producer.annotation.CurDataSource;
import com.person.test.demo_producer.enums.DataSourceEnum;
import com.person.test.demo_producer.mapper.UserMapper;
import com.person.test.demo_producer.pojo.User;
import com.person.test.demo_producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public String updateUserOne(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        int i = 1/0;
        if(result != 0) {
            return "ok update user db1";
        }
        return "fall";
    }

    @Override
    @Transactional
    @CurDataSource(name = DataSourceEnum.DBTwo)
    public String updateUserTwo(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        int i = 1/0;
        if(result != 0) {
            return "ok update user db2";
        }
        return "fall";
    }
}
