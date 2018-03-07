package com.ls.converter;

import com.ls.request.CommentRequest;
import com.ls.dto.CommentDTO;
import com.ls.model.User; /**
 * Created by tan.dongmei on 2018/2/9
 */
public class ConverterCommentDTO {

    public static CommentRequest converterCommentDTO(CommentDTO commentDTO, User user) {
        CommentRequest commentRequest = new CommentRequest();
        if(commentDTO == null){
            return null;
        }
        commentRequest.setBeiReplyId(commentDTO.getBeiReplyId());
        commentRequest.setEventId(commentDTO.getEventId());
        commentRequest.setParentId(commentDTO.getParentId());
        commentRequest.setReplayContent(commentDTO.getReplayContent());
        commentRequest.setReplayUserId(user.getUserId());
        return  commentRequest;
    }
}
