package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.model.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tan.dongmei on 2017/12/1
 */
@Api(value = "user",description = "用户接口")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    public RestfulResponse<List<User>> findUser(){

        return null;

    }
}
