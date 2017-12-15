package com.ls.mapper;

import com.ls.model.Host;

public interface HostMapper {
    int deleteByPrimaryKey(Integer hostid);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(Integer hostid);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);
}