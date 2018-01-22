package com.ls.request;

import lombok.Data;

/**
 * Created by tan.dongmei on 2017/12/4 0004.
 */
@Data
public class BaseQueryRequest {

    protected int pageNo = 1;

    protected int pageSize = 10;

    protected String sort;

    protected String dir;
}
