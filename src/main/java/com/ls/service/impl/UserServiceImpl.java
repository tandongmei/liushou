package com.ls.service.impl;

import com.ls.exception.ServiceException;
import com.ls.mapper.UserMapper;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.UserQueryRequest;
import com.ls.request.UserRequest;
import com.ls.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public User createUser(UserRequest userRequest) {
        // 判断用户名是否存在
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setNickName(userRequest.getNickName());
        User user = userMapper.getUser(userQueryRequest);
        if(user != null){
            // 用户已经存在
            throw new ServiceException(ResCodeEnum.USER_EXISTS.getCode(),ResCodeEnum.USER_EXISTS.getMsg());
        }
        user = new User();
        bindUser(userRequest,user);
        user.setCreatedTime(new Date());
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public User getUser(UserQueryRequest userQueryRequest) {
        User user = userMapper.getUser(userQueryRequest);
        if(user == null){
            throw new ServiceException(ResCodeEnum.USER_EMPTY.getCode(),ResCodeEnum.USER_EMPTY.getMsg());
        }
        return user;
    }

    private void bindUser(UserRequest userRequest,User user){
        BeanUtils.copyProperties(userRequest,user);
    }

}
