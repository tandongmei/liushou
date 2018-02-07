package com.ls.service;

import com.ls.model.Comment;
import com.ls.request.CommentQueryRequest;

import java.util.List;

/**
 * Created by tan.dongmei on 2018/1/25
 */
public interface ICommentService {

    List<Comment> getCommentList(CommentQueryRequest commentQueryRequest);

    /**
     * 得到该事件下的所有回复 按照顺序输出
     * @param eventId
     * @return
     */
    List<Comment> getAllCommentList(Integer eventId);
}
