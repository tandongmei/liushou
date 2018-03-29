package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterEventDTO;
import com.ls.mapper.HostMapper;
import com.ls.model.News;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.EventQueryRequest;
import com.ls.request.NewsQueryRequest;
import com.ls.service.INewsService;
import com.ls.vo.NewsVo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author:tandongmei
 * @Date:2018/3/4
 * 公益新闻模块
 */
@Api(value = "news",description = "公益新闻接口")
@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    private static Logger logger = LogManager.getLogger(NewsController.class);


    @Autowired
    private INewsService newsService;

    @ApiOperation(value = "用户事件列表" )
    @GetMapping(value = "")
    public RestfulResponse<List<Map<Object,String>>> findNews(
            @ApiParam(name = "hostId",value = "查询条件") @RequestParam(value = "hostId",required = false) Integer hostId,
            @ApiParam(name = "pageNo",value = "当前页") @RequestParam(value = "pageNo",required = false) Integer pageNo,
            @ApiParam(name = "pageSize",value = "每页条数") @RequestParam(value = "pageSize",required = false) Integer pageSize,
            @ApiParam(name = "sort",value = "排序字段") @RequestParam(value = "sort",defaultValue = "createdTime") String sort,
            @ApiParam(name = "dir",value = "升序或降序") @RequestParam(value = "dir",defaultValue = "desc") String dir){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
//            NewsVo newsVo=new NewsVo();
//            newsVo.setHostList(hostMapper.selectList());
            NewsQueryRequest newsQueryRequest = ConverterEventDTO.converterNewsDTO(hostId,pageNo,pageSize,sort,dir);
            List<Map<Object,String>> newsList = newsService.findNewsList(newsQueryRequest);
            int totalRecords = newsService.getTotalRecords(newsQueryRequest);//总记录数？？
//            newsVo.setNewsList(newsList);
//            newsVo.setTotalRecords(totalRecords);
//            restfulResponse.setData(newsVo);
            restfulResponse.setData(newsList);
            restfulResponse.setTotalRecords(totalRecords);
        }catch (Exception e){
            logger.catching(e);
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());

        }
        return restfulResponse;
    }

    @ApiOperation(value = "查询新闻")
    @GetMapping(value = "/{newsId}")
    public RestfulResponse<News> getNews(@PathVariable Integer newsId){
        RestfulResponse<News> restfulResponse = new RestfulResponse<>();
        try{
            News news = newsService.getNews(newsId);
            restfulResponse.setData(news);
        }catch (Exception e){
            logger.catching(e);
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
        }
        return restfulResponse;
    }



}
