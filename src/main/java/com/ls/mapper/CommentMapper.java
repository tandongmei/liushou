package com.ls.mapper;

import com.ls.model.Comment;
import com.ls.request.CommentQueryRequest;
import org.apache.ibatis.annotations.Param;

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

    //void batchUpdate(Integer eventId);

    List<Comment> getNoReadComment(@Param("flag") String flag,@Param("userId") Integer userId);

    int getNoReadCommentCount(@Param("flag") String flag, @Param("userId") Integer userId);

    void batchUpdateList(List<Comment> list);

    //int batchUserUpdate(@Param("userId") Integer userId,@Param("commentList") List<Comment> commentList);

    //void readComment(@Param("userId") Integer userId, @Param("commentId") Integer commentId);

    List<Comment> getMyComment(@Param("eventId") Integer eventId,@Param("userId") Integer userId);
}