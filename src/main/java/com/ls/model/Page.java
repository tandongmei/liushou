package com.ls.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tan.dongmei on 2017/12/4 0004.
 */
@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 8408552003707688907L;
    // required page num, start from 1
    protected int pageNo = 1;
    // page size, default is 10
    protected int pageSize = 10;
    // if count total num, default is false
    protected boolean countTotalRecords = false;

    // result
    protected List<T> result;
    // total record num
    protected int totalRecords;
}
