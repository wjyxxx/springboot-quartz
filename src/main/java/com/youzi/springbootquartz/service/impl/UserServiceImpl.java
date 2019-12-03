package com.youzi.springbootquartz.service.impl;

import com.youzi.springbootquartz.bean.User;
import com.youzi.springbootquartz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public int insertUser(User user) {
        System.out.println("user : " + user);
        return 1;
    }
}
