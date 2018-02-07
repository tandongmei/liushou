package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.dto.CommentDTO;
import com.ls.model.Comment;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.CommentQueryRequest;
import com.ls.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tan.dongmei on 2018/1/25
 */
@Api(value = "comment", description = "评论接口")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    private static Logger logger = LogManager.getLogger(CommentController.class);

    @Autowired
    private ICommentService commentService;

    @ApiOperation(value = "根据文章id查询该文章所有评论及其子评论")
    @GetMapping(value = "/{EventId}")
    public RestfulResponse<List<Comment>> findCommentList(@PathVariable Integer EventId){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            CommentQueryRequest commentQueryRequest = new CommentQueryRequest();
            commentQueryRequest.setEventId(EventId);
            //List<Comment> commentList = commentService.getCommentList(commentQueryRequest);
            long startTime =System.currentTimeMillis();
            List<Comment> commentList = commentService.getAllCommentList(EventId);
            long endTime=System.currentTimeMillis();
            System.out.println("时间是:"+(endTime-startTime));
            restfulResponse.setData(commentList);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
            return restfulResponse;
        }
        return restfulResponse;
    }

    @ApiOperation(value = "添加评论或回复")
    @PutMapping(value = "")
    public RestfulResponse createComment(@RequestBody CommentDTO commentDTO, BindingResult result){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{

        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
            return restfulResponse;
        }
        return restfulResponse;
    }


}
