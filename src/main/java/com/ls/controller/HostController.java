package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.mapper.HostMapper;
import com.ls.model.Host;
import com.ls.model.enm.ResCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author:tandongmei
 * @Date:2018/3/11
 */
@Api(value = "news",description = "新闻版块接口")
@RestController
@RequestMapping(value = "/api/host")
public class HostController {

    private static Logger logger = LogManager.getLogger(NewsController.class);

    @Autowired
    private HostMapper hostMapper;

    @ApiOperation(value = "新闻版块列表" )
    @GetMapping(value = "")
    public RestfulResponse<List<Map<Object,String>>> findHost(){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            List<Host> list = hostMapper.selectList();
            restfulResponse.setData(list);
        }catch (Exception e){
            logger.catching(e);
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
        }
        return restfulResponse;
    }
}
