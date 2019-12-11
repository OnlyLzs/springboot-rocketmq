package com.person.test.demo_producer.service;

import com.person.test.demo_producer.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    List<User> queryAllByDBTwo();

    String updateUserOne(User user);

    String updateUserTwo(User user);
}
