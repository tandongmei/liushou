package com.ls.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by tan.dongmei on 2017/12/11
 */
@Data
public class EventDTO {
//    private Integer eventId;

//    private Integer userId;

    @ApiModelProperty(value = "事件标题")
    @Size(max = 20,message = "用户昵称长度不能超过20")
    @NotNull(message = "事件标题为空")
    private String title;

    @ApiModelProperty(value = "事件内容")
    @NotNull(message = "事件内容为空")
    private String content;

    @ApiModelProperty(value = "事件附属图")
    private String eventImg;

//    private Integer flag;

//    private Integer hostId;

//    private Date createdTime;

}
