package com.ls.mapper;

import com.ls.model.Comment;
import com.ls.request.CommentQueryRequest;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> findParentCommentsByEventId(CommentQueryRequest commentQueryRequest);

    List<Comment> findReplayCommentsByCommentId(CommentQueryRequest cqr);

    List<Comment> getCommentByEventId(Integer eventId);

    List<Comment> getAllComment();

    int getAllCommentCount(Integer eventId);
}