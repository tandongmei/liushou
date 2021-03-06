package com.ls.mapper;

import com.ls.model.Event;
import com.ls.request.EventQueryRequest;

import java.util.List;
import java.util.Map;

public interface EventMapper {
    int deleteByPrimaryKey(Integer eventId);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer eventId);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    List<Map<String,Object>> findEventList(EventQueryRequest eventQueryRequest);

    int findEventListCount(EventQueryRequest eventQueryRequest);

    Event getEvent(Integer eventId);

    List<Map<Object,String>> getHostList();
}