package com.ls.request;

import lombok.Data;

/**
 * @author:yuhang
 * @Date:2018/3/4
 */
@Data
public class NewsQueryRequest extends  BaseQueryRequest {
    private Integer hostId;
}
