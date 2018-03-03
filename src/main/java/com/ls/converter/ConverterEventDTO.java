package com.ls.converter;

import com.alibaba.fastjson.JSON;
import com.ls.dto.EventDTO;
import com.ls.request.EventQueryRequest;
import com.ls.request.EventRequest;
import org.springframework.beans.BeanUtils;

/**
 * Created by tan.dongmei on 2017/12/11
 */
public class ConverterEventDTO extends BaseConverterDTO {
    public static EventQueryRequest converterEventDTO(String filters, Integer pageNo, Integer pageSize, String sort, String dir) {
        EventQueryRequest eventQueryRequest = null;
        if(filters != null){
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

    public static EventRequest converterEventDTO(EventDTO eventDTO) {
        EventRequest eventRequest = new EventRequest();
        if(eventDTO == null){
            return null;
        }
        BeanUtils.copyProperties(eventDTO,eventRequest);
        return eventRequest;
    }
}
