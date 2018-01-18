package com.ls.converter;


import com.ls.model.Page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tandongmei on 2017/7/19.
 */
public class BaseConverterDTO {
    public static Page getPage(Integer pageNo, Integer pageSize) {
        if (pageNo != null && pageSize != null) {
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            return page;
        }
        return null;
    }

    public static int getCurrentRecord(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return"";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb= new StringBuffer();
        Pattern pattern= Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }
}
