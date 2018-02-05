package com.ls.service.impl;

import com.ls.mapper.CommentMapper;
import com.ls.mapper.UserMapper;
import com.ls.model.Comment;
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
}
