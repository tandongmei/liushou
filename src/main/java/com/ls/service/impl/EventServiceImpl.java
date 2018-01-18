package com.ls.service.impl;

import com.ls.exception.ServiceException;
import com.ls.mapper.EventMapper;
import com.ls.model.Event;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.EventQueryRequest;
import com.ls.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/11
 */
@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Map<Object, String>> findEventList(EventQueryRequest eventQueryRequest) {
        return eventMapper.findEventList(eventQueryRequest);
    }

    @Override
    public Event getEvent(EventQueryRequest eventQueryRequest) {
        Event event = eventMapper.selectByPrimaryKey(eventQueryRequest.getEventId());
        if(event == null){
            throw new ServiceException(ResCodeEnum.EVENT_EMPTY.getCode(),ResCodeEnum.EVENT_EMPTY.getMsg());
        }
        return event;
    }
}
