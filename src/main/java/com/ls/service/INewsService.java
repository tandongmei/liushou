package com.ls.service;

import com.ls.model.News;
import com.ls.request.NewsQueryRequest;

import java.util.List;
import java.util.Map;

/**
 * @author:yuhang
 * @Date:2018/3/4
 */
public interface INewsService {
    List<Map<Object,String>> findNewsList(NewsQueryRequest newsQueryRequest);

    int getTotalRecords(NewsQueryRequest newsQueryRequest);

    News getNews(Integer newsId);
}
