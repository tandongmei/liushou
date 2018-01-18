package com.ls.converter;

import com.alibaba.fastjson.JSON;
import com.ls.request.EventQueryRequest;

/**
 * Created by tan.dongmei on 2017/12/11
 */
public class ConverterEventDTO extends BaseConverterDTO {
    public static EventQueryRequest converterEventDTO(String filters, Integer pageNo, Integer pageSize, String sort, String dir) {
         EventQueryRequest eventQueryRequest = JSON.parseObject(filters,EventQueryRequest.class);
         if(pageNo != null && pageSize!= null){
             eventQueryRequest.setPageNo(getCurrentRecord(pageNo,pageSize));
             eventQueryRequest.setPageSize(pageSize);
         }
         // sort在这转换
        sort = camel2Underline(sort);
         eventQueryRequest.setSort(sort);
         eventQueryRequest.setDir(dir);
         return eventQueryRequest;
    }
}
