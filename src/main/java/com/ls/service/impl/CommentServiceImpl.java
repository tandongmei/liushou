package com.ls.service.impl;

import com.ls.mapper.CommentMapper;
import com.ls.mapper.EventMapper;
import com.ls.mapper.UserMapper;
import com.ls.model.Comment;
import com.ls.model.Event;
import com.ls.model.User;
import com.ls.request.CommentQueryRequest;
import com.ls.service.ICommentService;
import com.ls.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tan.dongmei on 2018/1/25
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EventMapper eventMapper;

    //缓存得到该事件下的所有的回复 避免进行多次查询
    private List<Comment> allComments;

    public List<Comment> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<Comment> allComments) {
        this.allComments = allComments;
    }

    /**
     * 遍历所有的回复 按照楼层以及后代回复
     */
    @Override
    public List<Comment> getAllCommentList(Integer eventId) {
        //一次查询该事件下的所有的回复
        this.setAllComments(commentMapper.getCommentByEventId(eventId));
        List<Comment> resultList=new ArrayList<Comment>();//最终返回的结果

        List<Comment> topComments=this.getTopComment();//楼层回复
        for(Comment comment :topComments){
            //设置回复人
            comment.setReplayUser(userMapper.selectByPrimaryKey(comment.getReplayUserId()));
            //设置被回复人 就是发起事件的人
            Event event = eventMapper.selectByPrimaryKey(eventId);
            comment.setReplayCommentUser((userMapper.selectByPrimaryKey(event.getUserId())));
            // 设置returnTime
//            comment.setReturnTime(TimeUtil.natureTime(comment.getCreatedTime()));

            resultList.add(comment);
            //该楼层下的后代回复
            List<Comment> sun=this.getCommentByTop(comment.getReplayUserId(),comment.getCommentId());
            comment.setCommentList(sun);
        }
        return resultList;
    }

    @Override
    public int getCommentCount(Integer eventId) {
        int count = commentMapper.getAllCommentCount(eventId);

        return count;
    }

    /**
     * 查询该回复下的后代回复
     */
    public List<Comment> getCommentByTop(Integer userId,Integer parentId) {
        List<Comment> result = new ArrayList<>();
        //该回复下的子回复
        List<Comment> sunComments = getCommentByParentId(parentId);
        if (sunComments.size() > 0) {
            for (Comment comment : sunComments) {
                //设置回复人
                comment.setReplayUser(userMapper.selectByPrimaryKey(comment.getReplayUserId()));
                //设置被回复人
                comment.setReplayCommentUser(userMapper.selectByPrimaryKey(userId));
                // 设置returnTime
                comment.setReturnTime(TimeUtil.natureTime(comment.getCreatedTime()));

                result.add(comment);
                List<Comment> comments = getCommentByTop(comment.getReplayUserId(),comment.getCommentId());
                if (comments.size() > 0) {
                    result.addAll(comments);
                }
            }
        }
        return result;

    }

    /**
     * 得到该回复下所有子回复
     */
    public List<Comment> getCommentByParentId(Integer parentId){
        List<Comment> sunComments=new ArrayList<>();
        for(Comment comment:this.getAllComments()){
            if(comment.getParentId()==parentId){
                sunComments.add(comment);
            }
        }
        return sunComments;
    }

    /**
     * 查询事件下所有的楼层回复
     */
    public List<Comment> getTopComment(){
        List<Comment> result=new ArrayList<>();
        for(Comment comment:this.getAllComments()){
            if(0==comment.getParentId()){
                result.add(comment);
            }
        }
        return result;
    }




}
