package com.ls.service.impl;

import com.ls.exception.ServiceException;
import com.ls.mapper.UserMapper;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.model.enm.SysCodeEnum;
import com.ls.request.UserQueryRequest;
import com.ls.request.UserRequest;
import com.ls.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        user.setGender(SysCodeEnum.USER_GENDER_MAN.getMsg()); // 设置性别默认男
        user.setHeadImg(SysCodeEnum.USER_HEAD.getMsg()); // 设置默认头像
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

    @Override
    public User updateUser(UserRequest userRequest) {
        // 判断用户名是否存在
        User user = userMapper.selectByPrimaryKey(userRequest.getUserId());
        if(!user.getNickName().equals(userRequest.getNickName())){ // 说明用户昵称被修改
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setNickName(userRequest.getNickName());
            User u = userMapper.getUser(userQueryRequest);
            if(u != null){
                // 用户已经存在
                throw new ServiceException(ResCodeEnum.USER_EXISTS.getCode(),ResCodeEnum.USER_EXISTS.getMsg());
            }
        }
        bindUser(userRequest,user);
        userMapper.updateByPrimaryKey(user);
        return user;
    }

    private void bindUser(UserRequest userRequest,User user){
        if(!StringUtils.isEmpty(userRequest.getNickName())){
            user.setNickName(userRequest.getNickName());
        }
        if(!StringUtils.isEmpty(userRequest.getPassword())){
            user.setPassword(userRequest.getPassword());
        }
        if(!StringUtils.isEmpty(userRequest.getAge())){
            user.setAge(userRequest.getAge());
        }
        if(!StringUtils.isEmpty(userRequest.getEmail())){
            user.setEmail(userRequest.getEmail());
        }
        if(!StringUtils.isEmpty(userRequest.getTel())){
            user.setTel(userRequest.getTel());
        }
        if(!StringUtils.isEmpty(userRequest.getGender())){
            user.setGender(userRequest.getGender());
        }
        if(!StringUtils.isEmpty(userRequest.getHeadImg())){
            user.setHeadImg(userRequest.getHeadImg());
        }
        if(!StringUtils.isEmpty(userRequest.getPayNo())){
            user.setPayNo(userRequest.getPayNo());
        }
        if(!StringUtils.isEmpty(userRequest.getIsLeftChild())){
            user.setIsLeftChild(userRequest.getIsLeftChild());
        }
        if(!StringUtils.isEmpty(userRequest.getName())){
            user.setName(userRequest.getName());
        }
    }

}
