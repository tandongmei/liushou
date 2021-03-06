package com.ls.service.impl;

import com.ls.exception.ServiceException;
import com.ls.mapper.EventMapper;
import com.ls.model.Event;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.EventQueryRequest;
import com.ls.request.EventRequest;
import com.ls.service.IEventService;
import com.ls.util.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Map<String,Object>> findEventList(EventQueryRequest eventQueryRequest) {
        List<Map<String,Object>> list = eventMapper.findEventList(eventQueryRequest);
        return list;
    }

    @Override
    public Event getEvent(Integer eventId) {
        Event event = eventMapper.getEvent(eventId);
        if(event == null){
            throw new ServiceException(ResCodeEnum.EVENT_EMPTY.getCode(),ResCodeEnum.EVENT_EMPTY.getMsg());
        }
        event.setReturnTime(TimeUtil.dateToStr(event.getCreatedTime()));
        return event;
    }

    @Override
    public void create(EventRequest eventRequest,Integer userId) {
        Event event = bindEvent(eventRequest);
        event.setUserId(userId); // 当前登录人
        event.setFlag(1);
//        event.setHostId(1);
        event.setCreatedTime(new Date());
        eventMapper.insertSelective(event);
    }

    @Override
    public int getTotalRecords(EventQueryRequest eventQueryRequest) {
        return eventMapper.findEventListCount(eventQueryRequest);
    }

    @Override
    public List<Map<Object, String>> getHostList() {
        return eventMapper.getHostList();
    }

    private Event bindEvent(EventRequest eventRequest){
        Event event = new Event();
        BeanUtils.copyProperties(eventRequest,event);
        return event;
    }
}
