package com.ls.mapper;

import com.ls.model.Host;

import java.util.List;

public interface HostMapper {
    int deleteByPrimaryKey(Integer hostId);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(Integer hostId);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);

    List<Host> selectList();
}