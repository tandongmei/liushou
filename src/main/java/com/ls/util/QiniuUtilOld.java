package com.ls.util;

/**
 * @author:yuhang
 * @Date:2018/1/22
 */

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class QiniuUtilOld {

    private static final String CONFIG_BUCKET="qiniu.bucket";

    private static final String CONFIG_AK="qiniu.accesskey";
    private static final String CONFIG_SK="qiniu.secretkey";
    private static final String CONFIG_CDN="qiniu.cdns";

    private static final Auth auth;
    private static final UploadManager uploadManager;

    private static final String bucketName;
    private static final String cdn;

    /**
     * 从外部文件中初始化七牛存储相关的配置信息
     */
    static{
       InputStream inputStream =QiniuUtilOld.class.getClassLoader().getResourceAsStream("application.properties") ;
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

    public static String getToken(){
        String upToken = auth.uploadToken(bucketName);//上传资源的token
        return upToken;
    }

    public static String getKey(String fileName){
        String key = fileName+ UUID.randomUUID().toString();//上传资源的token
        return key;
    }


    /**
     * 文件上传
     * @param buf
     * @return
     */
    public static String uploadFile(String fileName,byte[] buf){
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        //指定了key
        String key = getKey(fileName);
        DefaultPutRet putRet = null;
        try {
            Response response = uploadManager.put(buf, key, getToken());
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return  putRet.key;
    }

    /**
     * 得到图片的链接
     * @param key
     * @return
     */
    public static String getUrl(String key){
        return "http://"+cdn+key;
    }

    /**
     * 得到各种大小图片的链接
     * @param key
     * @param modelSize 图片大小
     * @return
     */
    public static String getModelUrl(String key,String modelSize){
        return "http://"+cdn+key+"?"+modelSize;
    }
}
