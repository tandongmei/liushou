package com.ls.controller;

import com.ls.common.RestfulResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by tan.dongmei on 2018/1/30
 */
@Api(value = "file",description = "文件上传接口")
@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    private static Logger logger = LogManager.getLogger(FileController.class);

    @ApiOperation(value = "图片上传")
    @RequestMapping(value = "")
    public RestfulResponse<String> upload(){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{

        }catch (Exception e){

        }
        return restfulResponse;
    }
}
