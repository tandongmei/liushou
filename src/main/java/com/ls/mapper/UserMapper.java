package com.ls.mapper;

import com.ls.request.UserQueryRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/11
 */
public interface UserMapper {

    List<Map<String,Object>> findUser(UserQueryRequest userQueryRequest);
}
