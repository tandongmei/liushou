package com.ls.service;

import com.ls.request.CommentRequest;
import com.ls.model.Comment;

import java.util.List;

/**
 * Created by tan.dongmei on 2018/1/25
 */
public interface ICommentService {

    /**
     * 得到该事件下的所有回复 按照顺序输出
     * @param eventId
     * @return
     */
    List<Comment> getAllCommentList(Integer eventId);

    int getCommentCount(Integer eventId);

    Comment getComment(Integer commentId);

    void createComment(CommentRequest commentRequest);

    List<Comment> queryNoReadComment(String flag,Integer userId);

    int getNoReadCommentCount(String flag, Integer userId);

    void batchUpdate(List<Comment> commentList);


    List<Comment> getMyComment(Integer eventId, Integer userId);
}
