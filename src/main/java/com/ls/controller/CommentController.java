package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.model.enm.ResCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by tan.dongmei on 2018/1/25
 */
@Api(value = "comment", description = "评论接口")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @ApiOperation(value = "根据文章id查询该文章所以评论")
    @RequestMapping(value = "{/id}")
    public RestfulResponse<Map<String,Object>> findCommentList(@PathVariable Integer EventId){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{

        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            return restfulResponse;
        }
        return restfulResponse;
    }


}
