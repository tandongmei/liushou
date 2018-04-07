package com.ls.service;

import com.ls.model.Event;
import com.ls.request.EventQueryRequest;
import com.ls.request.EventRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/11
 */
public interface IEventService {

    List<Map<String,Object>> findEventList(EventQueryRequest eventQueryRequest);

    Event getEvent(Integer eventId);

    void create(EventRequest eventRequest,Integer userId);

    int getTotalRecords(EventQueryRequest eventQueryRequest);

    List<Map<Object,String>> getHostList();
}
