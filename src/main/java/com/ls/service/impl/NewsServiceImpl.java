package com.ls.service.impl;

import com.ls.mapper.NewsMapper;
import com.ls.mapper.UserMapper;
import com.ls.model.News;
import com.ls.model.User;
import com.ls.request.NewsQueryRequest;
import com.ls.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuhang
 * @Date:2018/3/4
 */
@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Map<Object, String>> findNewsList(NewsQueryRequest newsQueryRequest) {

        return newsMapper.findNewsList(newsQueryRequest);
    }

    @Override
    public int getTotalRecords(NewsQueryRequest newsQueryRequest) {
        return newsMapper.findNewsListCount(newsQueryRequest);
    }

    @Override
    public News getNews(Integer newsId) {
        News news = newsMapper.selectByPrimaryKey(newsId);
        User user = userMapper.selectByPrimaryKey(news.getUserId());
        news.setUserName(user.getNickName());
        news.setHeadImg(user.getHeadImg());
        return news;
    }
}
