package com.ls.request;

import lombok.Data;


/**
 * Created by tan.dongmei on 2018/2/9
 */
@Data
public class CommentRequest {

    private Integer commentId;

    private Integer eventId;

    private Integer replayUserId;

    private String replayContent;

    private Integer parentId;

    private Integer beiReplyId;//被回复人
}
