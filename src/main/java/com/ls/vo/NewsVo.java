package com.ls.vo;

import com.ls.model.Host;
import com.ls.model.News;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author:yuhang
 * @Date:2018/3/4
 */
@Data
public class NewsVo {
    private List<Host> hostList;
    private List<Map<Object,String>> newsList;
    private Integer totalRecords;
}
