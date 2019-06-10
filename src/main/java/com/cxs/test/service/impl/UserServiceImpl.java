package com.cxs.test.service.impl;

import com.cxs.service.impl.AService;
import com.cxs.test.domain.User;
import com.cxs.test.mapper.UserMapper;
import com.cxs.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/22 16:23
 */
@Service
public class UserServiceImpl extends AService<User> implements UserService {

    @Autowired
    private UserMapper dao;

    @PostConstruct
    public void set() {
        super.dao = this.dao;
    }
}
