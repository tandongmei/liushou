package com.ls.controller;

import com.ls.common.RestfulResponse;
import com.ls.converter.ConverterCommentDTO;
import com.ls.dto.CommentDTO;
import com.ls.interceptor.Access;
import com.ls.mapper.UserMapper;
import com.ls.model.Comment;
import com.ls.model.Event;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import com.ls.request.CommentRequest;
import com.ls.request.UserQueryRequest;
import com.ls.service.ICommentService;
import com.ls.service.IEventService;
import com.ls.vo.CommentVo;
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
import javax.servlet.http.HttpSession;
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
    private IEventService eventService;

    @Autowired
    private UserMapper userMapper;


    @ApiOperation(value = "根据文章id查询该文章所有评论及其子评论")
    @GetMapping(value = "/{EventId}")
    public RestfulResponse<List<Comment>> findCommentList(@PathVariable Integer EventId, HttpSession session){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            Event event = eventService.getEvent(EventId);
            //得到子评论
            List<Comment> commentList = commentService.getAllCommentList(EventId);

            User user = (User) session.getAttribute("userInfo");
            CommentVo commentVo=new CommentVo();
            //修改登录用户该事件下的所有未读的评论为已读
            if(user!=null && user.getUserId()==event.getUserId()
                    && commentList.size()>0 && commentList.get(0)!=null && 0==commentList.get(0).getReadFlag()){
                commentService.batchUpdate(eventId);
                user.setNoReadCommentCount(commentService.getNoReadCommentCount("0",user.getUserId()));
                user.setNoReadReplyCount(commentService.getNoReadCommentCount("1",user.getUserId()));
                commentVo.setUser(user);
                session.setAttribute("userInfo",user);
            }
            int count = commentService.getCommentCount(EventId);
            commentVo.setCommentList(commentList);
            restfulResponse.setData(commentVo);
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
    @Access
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


    /**
     *
     * @param flag 0:评论 1：回复
     * @param session
     * @return
     */
    @ApiOperation(value = "查看未读评论或回复")
    @Access
    @GetMapping(value = "")
    public RestfulResponse readComment(String flag,HttpSession session){
        RestfulResponse restfulResponse = new RestfulResponse();
        try{
            //查看评论
            User user = (User) session.getAttribute("userInfo");
            //未读评论列表
            List<Comment> commentList=commentService.queryNoReadComment(flag,user.getUserId());
            //未读评论数量
            int count = commentService.getNoReadCommentCount(flag,user.getUserId());
            //批量更新已查看的评论
            commentService.batchUpdate(commentList);
            user.setNoReadCommentCount(commentService.getNoReadCommentCount("0",user.getUserId()));
            user.setNoReadReplyCount(commentService.getNoReadCommentCount("1",user.getUserId()));
            session.setAttribute("userInfo",user);
            CommentVo commentVo=new CommentVo();
            commentVo.setUser(user);
            commentVo.setCommentList(commentList)
            restfulResponse.setData(commentVo);
            restfulResponse.setTotalRecords(count);
        }catch (Exception e){
            restfulResponse.setCode(ResCodeEnum.SERVER_ERROR.getCode());
            restfulResponse.setMsg(ResCodeEnum.SERVER_ERROR.getMsg());
            logger.catching(e);
            return restfulResponse;
        }
        return restfulResponse;
    }


}
