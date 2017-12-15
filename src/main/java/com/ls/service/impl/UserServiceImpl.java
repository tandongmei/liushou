package com.ls.service.impl;

import com.ls.mapper.UserMapper;
import com.ls.model.User;
import com.ls.request.UserQueryRequest;
import com.ls.request.UserRequest;
import com.ls.service.IUserService;
import org.springframework.beans.BeanUtils;
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
    private UserMapper userMapper;


    @Override
    public List<Map<String, Object>> findUserList(UserQueryRequest userQueryRequest) {
        return userMapper.findUserList(userQueryRequest);
    }

    @Override
    public void createUser(UserRequest userRequest) {
        User user = new User();
        bindUser(userRequest,user);
        userMapper.insertSelective(user);
    }

    private void bindUser(UserRequest userRequest,User user){
        BeanUtils.copyProperties(userRequest,user);
    }

}
