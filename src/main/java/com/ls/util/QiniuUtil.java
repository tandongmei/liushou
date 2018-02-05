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

<<<<<<< HEAD
    public static void main(String[] args) {
        QiniuUtil.uploadFile("image1.jpg","想和你旅行".getBytes());
    }
    /**
     * 从外部文件中初始化七牛存储相关的配置信息
     */
    static{
       InputStream inputStream =QiniuUtil.class.getClassLoader().getResourceAsStream("application.properties") ;
       Properties properties=new Properties();
       try {
            properties.load(inputStream);
       } catch (IOException e) {
            e.printStackTrace();
       }
       auth = Auth.create(properties.getProperty(CONFIG_AK), properties.getProperty(CONFIG_SK));
       Configuration cfg = new Configuration(Zone.zone0());//设置空间上传域名
       uploadManager = new UploadManager(cfg);
       bucketName=properties.getProperty(CONFIG_BUCKET);
       cdn = properties.getProperty(CONFIG_CDN);
    }
=======
    @Value("${qiniu.bucket}")
    private static String bucket;
>>>>>>> c278adeb7b6e38879323d13fb3e91c03861a8174






}
