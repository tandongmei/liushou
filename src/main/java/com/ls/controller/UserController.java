package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterUserDTO;
import com.ls.exception.ServiceException;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.UserQueryRequest;
import com.ls.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/1
 */
@Api(value = "user",description = "用户接口")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户列表")
    @GetMapping(value = "")
    public RestfulResponse<List<User>> findUser(
            @ApiParam(name = "filters", value = "查询条件") @RequestParam(value = "filters") String filters,
            @ApiParam(name = "pageNo", value = "起始页") @RequestParam(name = "pageNo", required = false) Integer pageNo,
            @ApiParam(name = "pageSize", value = "每页条数") @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @ApiParam(name = "sort", value = "排序字段") @RequestParam(value = "sort", defaultValue = "createdTime") String sort,
            @ApiParam(name = "dir", value = "升序或降序") @RequestParam(value = "dir", defaultValue = "desc") String dir){
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            UserQueryRequest userQueryRequest = ConverterUserDTO.converterUserDTO(pageNo,pageSize,sort,dir,filters);
            List<Map<String,Object>> userList = userService.findUser(userQueryRequest);
            restfulResponse.setData(userList);
        }catch (ServiceException se){
            restfulResponse.setCode(ResCodeEnum.USER_EMPTY.getCode());
            logger.catching(se);
            restfulResponse.setMsg(ResCodeEnum.USER_EMPTY.getMsg());
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;

    }
}
