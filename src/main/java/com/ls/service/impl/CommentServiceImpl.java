package com.ls.service.impl;

import com.ls.mapper.CommentMapper;
import com.ls.mapper.EventMapper;
import com.ls.mapper.UserMapper;
import com.ls.model.Comment;
import com.ls.model.Event;
import com.ls.model.User;
import com.ls.request.CommentQueryRequest;
import com.ls.service.ICommentService;
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

    public List<Comment> getCommentList(CommentQueryRequest commentQueryRequest) {
        // 1.根据文章id查出所有相关的父评论
        List<Comment> commentList = commentMapper.findParentCommentsByEventId(commentQueryRequest); // 张三 event_id=1
        // 2.循环遍历父评论
        for(Comment comment : commentList){
            // 3.根据评论人id查找评论人信息
            User user = userMapper.selectByPrimaryKey(comment.getReplayUserId());
            comment.setReplayUser(user); // 张三
            // 4.实例化回复的集合
            List<Comment> replays = new ArrayList<>();
            comment.setCommentList(replays);
            buildCommentReplay(comment,replays); // 构建评论与回复的消息
        }
        return commentList;
    }

    private void buildCommentReplay(Comment comment, List<Comment> replays) {
         // 5.找出parent_id是父评论id的子评论集合
            CommentQueryRequest cqr = new CommentQueryRequest();
            cqr.setCommentId(comment.getCommentId()); // 张三 comment_id = 1
            List<Comment> replayList = commentMapper.findReplayCommentsByCommentId(cqr);
            replays.addAll(replayList);
            for(Comment c : replayList){
                // 设置回复人信息
                c.setReplayUser(userMapper.selectByPrimaryKey(c.getReplayUserId()));
                // 设置评论人信息
                c.setReplayCommentUser((userMapper.selectByPrimaryKey(comment.getReplayUserId())));
                buildCommentReplay(c,replays);
            }
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

            resultList.add(comment);
            //该楼层下的后代回复
            List<Comment> sun=this.getCommentByTop(comment.getReplayUserId(),comment.getCommentId());
            comment.setCommentList(sun);
        }
        return resultList;
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
