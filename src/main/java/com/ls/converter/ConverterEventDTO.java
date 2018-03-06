package com.ls.converter;

import com.alibaba.fastjson.JSON;
import com.ls.dto.EventDTO;
import com.ls.request.EventQueryRequest;
import com.ls.request.EventRequest;
import com.ls.request.NewsQueryRequest;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.BeanUtils;

/**
 * Created by tan.dongmei on 2017/12/11
 */
public class ConverterEventDTO extends BaseConverterDTO {
    public static EventQueryRequest converterEventDTO(String filters, Integer pageNo, Integer pageSize, String sort, String dir) {

        EventQueryRequest eventQueryRequest = new EventQueryRequest();
        if(filters != null && filters.length()>0){
            eventQueryRequest = JSON.parseObject(filters,EventQueryRequest.class);
        }

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

    public static NewsQueryRequest converterNewsDTO(Integer hostId, Integer pageNo, Integer pageSize, String sort, String dir) {
        NewsQueryRequest newsQueryRequest = new NewsQueryRequest();
        newsQueryRequest.setHostId(hostId);
        if(pageNo != null && pageSize!= null){
            newsQueryRequest.setPageNo(getCurrentRecord(pageNo,pageSize));
            newsQueryRequest.setPageSize(pageSize);
        }
        // sort在这转换
        sort = camel2Underline(sort);
        newsQueryRequest.setSort(sort);
        newsQueryRequest.setDir(dir);
        return newsQueryRequest;
    }


    public static EventRequest converterEventDTO(EventDTO eventDTO) {
        EventRequest eventRequest = new EventRequest();
        if(eventDTO == null){
            return null;
        }
        BeanUtils.copyProperties(eventDTO,eventRequest);
        return eventRequest;
    }
}
