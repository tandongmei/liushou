package com.ls.converter;

import com.ls.dto.UserDTO;
import com.ls.request.UserQueryRequest;
import com.alibaba.fastjson.JSON;
import com.ls.request.UserRequest;
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
        userQueryRequest.setSort(sort);
        userQueryRequest.setDir(dir);
        return userQueryRequest;
    }

}
