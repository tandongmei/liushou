package com.ls.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by tan.dongmei on 2018/1/31
 */
public class QiniuUtil {

    @Value("${qiniu.accessKey}")
    private static String accessKey;

    @Value("${qiniu.secretKey}")
    private static String secretKey;

    @Value("${qiniu.bucket}")
    private static String bucket;






}
