package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.interceptor.Access;
import com.ls.model.enm.ResCodeEnum;
import com.ls.util.QiniuUtil;
import com.ls.util.ThumbModel;
import com.qiniu.common.QiniuException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by tan.dongmei on 2018/1/30
 */
@Api(value = "file",description = "文件上传接口")
@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    private static Logger logger = LogManager.getLogger(FileController.class);

    @ApiOperation(value = "图片上传")
    @Access
    @PostMapping(value = "")
    public RestfulResponse<String> upload(MultipartFile file) {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            String key = QiniuUtil.uploadFile(file.getOriginalFilename(),file.getBytes());
            // 获取图片服务器地址
            String url = QiniuUtil.getUrl(key);
            String url2 = QiniuUtil.getModelUrl(key, ThumbModel.getThum(1024));
            System.out.println("url:"+url);
            System.out.println("url2:"+url2);

        }catch (QiniuException ex) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(ex);
        } catch (Exception e) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "获取token值")
    @Access
    @GetMapping(value = "")
    public RestfulResponse getToken() {
        RestfulResponse restfulResponse = new RestfulResponse();
        try {
            String token = QiniuUtil.getToken();
            restfulResponse.setData(token);
        } catch (Exception e) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }
}
