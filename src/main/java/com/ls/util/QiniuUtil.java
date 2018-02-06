package com.ls.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by tan.dongmei on 2018/1/31
 */
public class QiniuUtil {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;


}
