package com.ls.controller;

import com.google.gson.Gson;
import com.ls.common.RestfulResponse;
import com.ls.model.enm.ResCodeEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tan.dongmei on 2018/1/30
 */
@Api(value = "file",description = "文件上传接口")
@RestController
@RequestMapping(value = "/api/file")
public class FileController {

    private static Logger logger = LogManager.getLogger(FileController.class);

    @Value("${qiniu.accessKey}")
    private  String accessKey;

    @Value("${qiniu.secretKey}")
    private  String secretKey;

    @Value("${qiniu.bucket}")
    private  String bucket;

    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/upload")
    public RestfulResponse<String> upload(MultipartFile file) {
        RestfulResponse restfulResponse = new RestfulResponse();
        // 0.默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = file.getOriginalFilename();
        try {
            // 1.构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone0());
            // 2.创建UploadManager对象
            UploadManager uploadManager = new UploadManager(cfg);
            // 3.获得字节数组butes,通过accessKey，secretKey，bucket分别获得Auth,token对象
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            // 4.封装参数，上传
            Response response = uploadManager.put(uploadBytes, key, upToken);
            // 5.解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        }catch (QiniuException ex) {
//            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
//            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(ex);
        } catch (Exception e) {
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }
}
