package com.ls.service.impl;

import com.ls.model.Comment;
import com.ls.request.CommentQueryRequest;
import com.ls.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tan.dongmei on 2018/1/25
 */
@Service
public class CommentServiceImpl implements ICommentService {

   
    public List<Comment> getCommentList(CommentQueryRequest commentQueryRequest) {
        return null;
    }
}
