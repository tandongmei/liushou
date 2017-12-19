package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterUserDTO;
import com.ls.dto.UserDTO;
import com.ls.exception.ServiceException;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.UserQueryRequest;
import com.ls.request.UserRequest;
import com.ls.service.IUserService;
import com.ls.util.MD5Util;
import com.ls.util.ValidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
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
    public RestfulResponse<List<Map<String,Object>>> findUser(
            @ApiParam(name = "filters", value = "查询条件") @RequestParam(value = "filters") String filters,
            @ApiParam(name = "pageNo", value = "起始页") @RequestParam(name = "pageNo", required = false) Integer pageNo,
            @ApiParam(name = "pageSize", value = "每页条数") @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @ApiParam(name = "sort", value = "排序字段") @RequestParam(value = "sort", defaultValue = "createdTime") String sort,
            @ApiParam(name = "dir", value = "升序或降序") @RequestParam(value = "dir", defaultValue = "desc") String dir){
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            UserQueryRequest userQueryRequest = ConverterUserDTO.converterUserDTO(pageNo,pageSize,sort,dir,filters);
            List<Map<String,Object>> userList = userService.findUserList(userQueryRequest);
            restfulResponse.setData(userList);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "")
    public RestfulResponse createAdministrator(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            if (result.hasErrors()) {
                List<ObjectError> allErrors = result.getAllErrors();
                restfulResponse.setCode(-1);
                restfulResponse.setMsg(allErrors.get(0).getDefaultMessage());
                return restfulResponse;
            }
            // 校验手机号格式
            if (!ValidUtil.validateTel(userDTO.getTel())) {
                restfulResponse.setCode(-1);
                restfulResponse.setMsg("手机号格式错误");
                return restfulResponse;
            }
            // 校验邮箱格式
            if (!ValidUtil.validateEmail(userDTO.getEmail())) {
                restfulResponse.setCode(-1);
                restfulResponse.setMsg("邮箱格式错误");
                return restfulResponse;
            }
            // 校验支付宝账号
            if(!StringUtils.isEmpty(userDTO.getPayNo())){
                if(!ValidUtil.validatePayNo(userDTO.getPayNo())){
                    restfulResponse.setCode(-1);
                    restfulResponse.setMsg("支付宝账号格式错误");
                    return restfulResponse;
                }
            }
            UserRequest userRequest = ConverterUserDTO.converterUserDTO(userDTO);
            userService.createUser(userRequest);
        } catch (Exception e) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public RestfulResponse login(@RequestParam(name = "nickName") String nickName,@RequestParam(name = "password") String password){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            if(StringUtils.isEmpty(nickName) || StringUtils.isEmpty(password)){
                restfulResponse.setCode(-1);
                restfulResponse.setMsg("用户名或密码为空");
            }
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setNickName(nickName);
//            userQueryRequest.setPassword(MD5Util.md5Hex(password));
            User user = userService.getUser(userQueryRequest);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }
}
