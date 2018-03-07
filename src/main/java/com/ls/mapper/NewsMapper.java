package com.ls.mapper;

import com.ls.model.News;
import com.ls.request.NewsQueryRequest;

import java.util.List;
import java.util.Map;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer newsId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<Map<Object,String>> findNewsList(NewsQueryRequest newsQueryRequest);

    int findNewsListCount(NewsQueryRequest newsQueryRequest);
}