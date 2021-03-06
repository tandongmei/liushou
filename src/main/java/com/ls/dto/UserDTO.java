package com.ls.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by tan.dongmei on 2017/12/1
 */
@Data
@ApiModel(value = "用户对象",description = "userDTO")
public class UserDTO {

    @ApiModelProperty(value = "用户昵称", required = true)
    @Size(max = 20,message = "用户昵称长度不能超过20")
    @NotNull(message = "用户昵称为空")
    private String nickName;

    @ApiModelProperty(value = "用户密码", required = true)
    @Size(max = 20,message = "用户密码长度不能超过20")
    @NotNull(message = "用户密码为空")
    private String password;

//    @ApiModelProperty(value = "真实姓名", required = true)
//    @NotNull(message = "真实姓名为空")
//    private String name;
//
//    @ApiModelProperty(value = "年龄", required = true)
//    @NotNull(message = "年龄为空")
//    private Integer age;
//
//    @ApiModelProperty(value = "性别", required = true)
//    @NotNull(message = "性别为空")
//    private String gender;
//
//    @ApiModelProperty(value = "电话", required = true)
//    @NotNull(message = "电话为空")
//    private String tel;
//
//    @ApiModelProperty(value = "邮箱", required = true)
//    @NotNull(message = "邮箱为空")
//    private String email;
//
    @ApiModelProperty(value = "是否是留守儿童", required = true)
    @NotNull(message = "是否是留守儿童必填")
    private Integer isLeftChild;
//
//    @ApiModelProperty(value = "头像", required = true)
//    private String headImg;
//
//    @ApiModelProperty(value = "支付宝账号")
//    private String payNo;


}
