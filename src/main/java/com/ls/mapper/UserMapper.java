package com.ls.mapper;

import com.ls.model.User;
import com.ls.request.UserQueryRequest;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<Map<String,Object>> findUser(UserQueryRequest userQueryRequest);
}