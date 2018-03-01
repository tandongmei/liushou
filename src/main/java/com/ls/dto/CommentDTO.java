package com.ls.dto;

import com.ls.model.Comment;
import com.ls.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by tan.dongmei on 2018/2/6
 */
@Data
public class CommentDTO {

//    private Integer commentId;

    @ApiModelProperty(value = "事件id")
    @NotNull(message = "事件ID为空")
    private Integer eventId;

    @ApiModelProperty(value = "当前登陆用户昵称")
    @NotNull(message = "当前登陆用户昵称为空")
    private String replayUserNickName;  // 当前登陆用户昵称

    @ApiModelProperty(value = "评论的内容")
    @NotNull(message = "评论的内容为空")
    private String replayContent;  // 评论的内容

    @ApiModelProperty(value = "父亲id")
    @NotNull(message = "父亲id为空")
    private Integer parentId;

//    private Date createdTime;

//    private Integer parentId;

//    private User replayUser; // 评论者,当前登陆用户，从后台获取，保证后台session和前台session生命周期一致

//    private User replayCommentUser; // 回复评论的人，回复谁

//    private List<Comment> commentList; // 子评论
}
