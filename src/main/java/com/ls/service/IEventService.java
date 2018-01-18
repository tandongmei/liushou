package com.ls.service;

import com.ls.model.Event;
import com.ls.request.EventQueryRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/11
 */
public interface IEventService {

    List<Map<Object,String>> findEventList(EventQueryRequest eventQueryRequest);

    Event getEvent(EventQueryRequest eventQueryRequest);
}
