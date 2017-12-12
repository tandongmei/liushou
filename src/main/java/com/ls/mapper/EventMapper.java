package com.ls.mapper;

import com.ls.model.Event;

public interface EventMapper {
    int deleteByPrimaryKey(Integer eventid);

    int insert(Event record);

    int insertSelective(Event record);

    Event selectByPrimaryKey(Integer eventid);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);
}