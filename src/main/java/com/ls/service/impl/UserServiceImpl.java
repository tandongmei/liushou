package com.ls.service.impl;

import com.ls.dao.IUserDao;
import com.ls.mapper.UserMapper;
import com.ls.request.UserQueryRequest;
import com.ls.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/1
 */
@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<String, Object>> findUser(UserQueryRequest userQueryRequest) {
        return userMapper.findUser(userQueryRequest);
    }
}
