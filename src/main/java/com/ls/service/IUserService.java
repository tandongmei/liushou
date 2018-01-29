package com.ls.service;

import com.ls.model.User;
import com.ls.request.UserQueryRequest;
import com.ls.request.UserRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public interface IUserService {

    List<Map<String,Object>> findUserList(UserQueryRequest userQueryRequest);

    User createUser(UserRequest userRequest);

    User getUser(UserQueryRequest userQueryRequest);

    User updateUser(UserRequest userRequest);

}
