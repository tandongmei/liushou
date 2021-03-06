package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterUserDTO;
import com.ls.dto.UserDTO;
import com.ls.dto.UserUpdateDTO;
import com.ls.exception.ServiceException;
import com.ls.interceptor.Access;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.UserQueryRequest;
import com.ls.request.UserRequest;
import com.ls.service.ICommentService;
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

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ICommentService commentService;

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
    public RestfulResponse<User> createAdministrator(@RequestBody @Valid UserDTO userDTO, BindingResult result, HttpServletRequest request) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            if (result.hasErrors()) {
                List<ObjectError> allErrors = result.getAllErrors();
                restfulResponse.setCode(-1);
                restfulResponse.setMsg(allErrors.get(0).getDefaultMessage());
                return restfulResponse;
            }
            // 校验手机号格式
//            if (!ValidUtil.validateTel(userDTO.getTel())) {
//                restfulResponse.setCode(-1);
//                restfulResponse.setMsg("手机号格式错误");
//                return restfulResponse;
//            }
            // 校验邮箱格式
//            if (!ValidUtil.validateEmail(userDTO.getEmail())) {
//                restfulResponse.setCode(-1);
//                restfulResponse.setMsg("邮箱格式错误");
//                return restfulResponse;
//            }
            // 校验支付宝账号
//            if(!StringUtils.isEmpty(userDTO.getPayNo())){
//                if(!ValidUtil.validatePayNo(userDTO.getPayNo())){
//                    restfulResponse.setCode(-1);
//                    restfulResponse.setMsg("支付宝账号格式错误");
//                    return restfulResponse;
//                }
//            }
            UserRequest userRequest = ConverterUserDTO.converterUserDTO(userDTO);
            User user = userService.createUser(userRequest);
            restfulResponse.setData(user);
            // 注册成功后信息保存在session
            request.getSession().setAttribute("userInfo",user);
            System.out.println("注册，后台session："+request.getSession().getAttribute("userInfo").toString());
        }catch (ServiceException se) {
            restfulResponse.setCode(ResCodeEnum.USER_EXISTS.getCode());
            restfulResponse.setMsg(ResCodeEnum.USER_EXISTS.getMsg());
            logger.catching(se);
        } catch (Exception e) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public RestfulResponse<User> login(@RequestParam(name = "nickName") String nickName, @RequestParam(name = "password") String password, HttpServletRequest request){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            if(StringUtils.isEmpty(nickName) || StringUtils.isEmpty(password)){
                restfulResponse.setCode(-1);
                restfulResponse.setMsg("用户名或密码为空");
            }
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setNickName(nickName);
            User user = userService.getUser(userQueryRequest);
            if(!user.getPassword().equals( MD5Util.md5Hex(password))){
                restfulResponse.setCode(-2);
                restfulResponse.setMsg("密码错误");
            }
            //设置未读评论数和回复数
            user.setNoReadCommentCount(commentService.getNoReadCommentCount("0",user.getUserId()));
            user.setNoReadReplyCount(commentService.getNoReadCommentCount("1",user.getUserId()));
            restfulResponse.setData(user);
            // 登陆信息保存在session
            request.getSession().setAttribute("userInfo",user);
            System.out.println("登陆，后台session："+request.getSession().getAttribute("userInfo").toString());
        }catch (ServiceException se){
            restfulResponse.setCode(se.getCode());
            restfulResponse.setMsg(se.getMessage());
            logger.catching(se);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "修改资料")
    @Access
    @PutMapping(value = "/{id}")
    public RestfulResponse<User> update(@PathVariable(value = "id") Integer userId, @RequestBody @Valid UserUpdateDTO userUpdateDTO, BindingResult result,HttpServletRequest request){
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            if (result.hasErrors()) {
                List<ObjectError> allErrors = result.getAllErrors();
                restfulResponse.setCode(-1);
                restfulResponse.setMsg(allErrors.get(0).getDefaultMessage());
                return restfulResponse;
            }
            // 校验手机号格式
            if(!StringUtils.isEmpty(userUpdateDTO.getTel())){
                if (!ValidUtil.validateTel(userUpdateDTO.getTel())) {
                    restfulResponse.setCode(-1);
                    restfulResponse.setMsg("手机号格式错误");
                    return restfulResponse;
                }
            }
            // 校验邮箱格式
            if(!StringUtils.isEmpty(userUpdateDTO.getEmail())){
                if (!ValidUtil.validateEmail(userUpdateDTO.getEmail())) {
                    restfulResponse.setCode(-1);
                    restfulResponse.setMsg("邮箱格式错误");
                    return restfulResponse;
                }
            }
            // 校验支付宝账号
            if(!StringUtils.isEmpty(userUpdateDTO.getPayNo())){
                if(!ValidUtil.validatePayNo(userUpdateDTO.getPayNo())){
                    restfulResponse.setCode(-1);
                    restfulResponse.setMsg("支付宝账号格式错误");
                    return restfulResponse;
                }
            }
            UserRequest userRequest = ConverterUserDTO.converterUserDTO(userId,userUpdateDTO);
            User user = userService.updateUser(userRequest);
            restfulResponse.setData(user);
            // 修改成功后用户信息保存在session
            request.getSession().setAttribute("userInfo",user);
            System.out.println("修改，后台session："+request.getSession().getAttribute("userInfo").toString());
        }catch (ServiceException se) {
            restfulResponse.setCode(ResCodeEnum.USER_EXISTS.getCode());
            restfulResponse.setMsg(ResCodeEnum.USER_EXISTS.getMsg());
            logger.catching(se);
        } catch (Exception e) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "查询用户详情")
    @GetMapping(value = "/{userId}")
    public RestfulResponse<User> getEvent(@PathVariable Integer userId){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setUserId(userId);
            User user = userService.getUser(userQueryRequest);
            restfulResponse.setData(user);
        }catch (ServiceException se){
            restfulResponse.setCode(ResCodeEnum.USER_EMPTY.getCode());
            restfulResponse.setMsg(ResCodeEnum.USER_EMPTY.getMsg());
            logger.catching(se);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

}
