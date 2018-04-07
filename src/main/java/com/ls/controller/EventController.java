package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterEventDTO;
import com.ls.dto.EventDTO;
import com.ls.exception.ServiceException;
import com.ls.interceptor.Access;
import com.ls.model.Event;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.EventQueryRequest;
import com.ls.request.EventRequest;
import com.ls.service.IEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by tan.dongmei on 2017/12/11
 */
@Api(value = "event",description = "用户事件接口")
@RestController
@RequestMapping(value = "/api/event")
public class EventController {

    private static Logger logger = LogManager.getLogger(EventController.class);

    @Autowired
    private IEventService eventService;

    @ApiOperation(value = "用户事件列表" )
    @GetMapping(value = "")
    public RestfulResponse<List<Map<String,Object>>> findEvent(
            @ApiParam(name = "filters",value = "查询条件") @RequestParam(value = "filters",required = false) String filters,
            @ApiParam(name = "pageNo",value = "当前页") @RequestParam(value = "pageNo",required = false) Integer pageNo,
            @ApiParam(name = "pageSize",value = "每页条数") @RequestParam(value = "pageSize",required = false) Integer pageSize,
            @ApiParam(name = "sort",value = "排序字段") @RequestParam(value = "sort",defaultValue = "createdTime") String sort,
            @ApiParam(name = "dir",value = "升序或降序") @RequestParam(value = "dir",defaultValue = "desc") String dir){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            EventQueryRequest eventQueryRequest = ConverterEventDTO.converterEventDTO(filters,pageNo,pageSize,sort,dir);
            List<Map<String,Object>> eventList = eventService.findEventList(eventQueryRequest);
            int totalRecords = eventService.getTotalRecords(eventQueryRequest);
            restfulResponse.setData(eventList);
            restfulResponse.setTotalRecords(totalRecords);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "查询事件详情")
    @GetMapping(value = "/{eventId}")
    public RestfulResponse<Event> getEvent(@PathVariable Integer eventId){
           RestfulResponse restfulResponse = new RestfulResponse();
           try{
               Event event = eventService.getEvent(eventId);
               restfulResponse.setData(event);
           }catch (ServiceException se){
               restfulResponse.setCode(ResCodeEnum.EVENT_EMPTY.getCode());
               restfulResponse.setMsg(ResCodeEnum.EVENT_EMPTY.getMsg());
               logger.catching(se);
           }catch (Exception e){
               restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
               restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
               logger.catching(e);
           }
           return restfulResponse;
    }

    @ApiOperation(value = "新增事件")
    @Access
    @PutMapping(value = "")
    public RestfulResponse create(@RequestBody @Validated EventDTO eventDTO, BindingResult result, HttpServletRequest request){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            if(result.hasErrors()){
                List<ObjectError> list = result.getAllErrors();
                restfulResponse.setCode(-1);
                restfulResponse.setMsg(list.get(0).getDefaultMessage());
                return restfulResponse;
            }
            EventRequest eventRequest = ConverterEventDTO.converterEventDTO(eventDTO);
            // 从后台session获取用户信息，和前台session比较
            User u = (User) request.getSession().getAttribute("userInfo");
            if(u == null){
                restfulResponse.setCode(ResCodeEnum.SESSION_TIME_OUT.getCode());
                restfulResponse.setMsg(ResCodeEnum.SESSION_TIME_OUT.getMsg());
                return restfulResponse;
            }
            eventService.create(eventRequest,u.getUserId());
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
        }
        return restfulResponse;
    }

    @ApiOperation(value = "获取热评")
    @GetMapping(value = "/host")
    public RestfulResponse<List<Map<Object,String>>>  getHostEvent(){
        RestfulResponse<List<Map<Object,String>>>  restfulResponse = new RestfulResponse<>();
        List<Map<Object,String>> hostList = eventService.getHostList();
        restfulResponse.setData(hostList);
        return restfulResponse;
    }

}
