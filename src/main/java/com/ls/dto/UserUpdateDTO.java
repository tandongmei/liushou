package com.ls.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tan.dongmei on 2018/1/29
 */
@Data
@ApiModel(value = "用户对象",description = "userDTO")
public class UserUpdateDTO {

    @ApiModelProperty(value = "用户昵称", required = true)
    @Size(max = 20,message = "用户昵称长度不能超过20")
    @NotNull(message = "用户昵称为空")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否是留守儿童")
    private Integer isLeftChild;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "支付宝账号")
    private String payNo;
}
