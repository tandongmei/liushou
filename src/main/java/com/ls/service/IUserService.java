package com.ls.service;

import com.ls.request.UserQueryRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public interface IUserService {

    List<Map<String,Object>> findUser(UserQueryRequest userQueryRequest);

}
