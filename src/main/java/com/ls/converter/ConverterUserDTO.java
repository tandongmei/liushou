package com.ls.converter;

import com.ls.dto.UserDTO;
import com.ls.dto.UserUpdateDTO;
import com.ls.request.UserQueryRequest;
import com.alibaba.fastjson.JSON;
import com.ls.request.UserRequest;
import com.ls.util.MD5Util;
import org.springframework.beans.BeanUtils;

/**
 * Created by tan.dongmei on 2017/12/1
 */
public class ConverterUserDTO extends BaseConverterDTO{

    public static UserQueryRequest converterUserDTO(Integer pageNo,Integer pageSize,String sort,String dir,String filters){
        UserQueryRequest userQueryRequest = JSON.parseObject(filters, UserQueryRequest.class);
        if (pageNo != null && pageSize != null) {
            userQueryRequest.setPageNo(getCurrentRecord(pageNo, pageSize) - 1);
            userQueryRequest.setPageSize(pageSize);
        }
        sort = camel2Underline(sort);
//        String isLeftChild = camel2Underline(userQueryRequest.getIsLeftChild()+"");
//        userQueryRequest.setIsLeftChild(isLeftChild);
        userQueryRequest.setSort(sort);
        userQueryRequest.setDir(dir);
        return userQueryRequest;
    }


    public static UserRequest converterUserDTO(UserDTO userDTO){
        if (userDTO == null) {
            return null;
        }
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(userDTO, userRequest);
        userRequest.setPassword(MD5Util.md5Hex(userDTO.getPassword()));
        return userRequest;
    }

    public static UserRequest converterUserDTO(Integer userId, UserUpdateDTO userUpdateDTO){
        if (userUpdateDTO == null) {
            return null;
        }
        UserRequest userRequest = new UserRequest();
        BeanUtils.copyProperties(userUpdateDTO, userRequest);
        userRequest.setUserId(userId);
        return userRequest;
    }

}
