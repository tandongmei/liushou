package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterCommentDTO;
import com.ls.dto.CommentDTO;
import com.ls.mapper.UserMapper;
import com.ls.model.Comment;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.CommentRequest;
import com.ls.request.UserQueryRequest;
import com.ls.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserMapper userMapper;


    @ApiOperation(value = "根据文章id查询该文章所有评论及其子评论")
    @GetMapping(value = "/{EventId}")
    public RestfulResponse<List<Comment>> findCommentList(@PathVariable Integer EventId){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            List<Comment> commentList = commentService.getAllCommentList(EventId);
            int count = commentService.getCommentCount(EventId);
            restfulResponse.setData(commentList);
            restfulResponse.setTotalRecords(count);
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
    public RestfulResponse createComment(@RequestBody @Validated CommentDTO commentDTO, BindingResult result, HttpServletRequest request){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            if(result.hasErrors()){
                List<ObjectError> list = result.getAllErrors();
                restfulResponse.setCode(-1);
                restfulResponse.setMsg(list.get(0).getDefaultMessage());
                return restfulResponse;
            }
            String nickName = commentDTO.getReplayUserNickName();
            UserQueryRequest userQueryRequest = new UserQueryRequest();
            userQueryRequest.setNickName(commentDTO.getReplayUserNickName());
            User user = userMapper.getUser(userQueryRequest);
            // 从后台session获取用户信息，和前台session比较
            User u = (User) request.getSession().getAttribute("userInfo");
            if(u == null){
                restfulResponse.setCode(ResCodeEnum.SESSION_TIME_OUT.getCode());
                restfulResponse.setMsg(ResCodeEnum.SESSION_TIME_OUT.getMsg());
                return restfulResponse;
            }
            if(u != null && user != null){
                if(u.getUserId().equals(user.getUserId())){
                    CommentRequest commentRequest = ConverterCommentDTO.converterCommentDTO(commentDTO,user);
                    commentService.createComment(commentRequest);
                }
            }
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
            return restfulResponse;
        }
        return restfulResponse;
    }


}
