package com.ls.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tan.dongmei on 2017/12/18
 */
public class ValidUtil {
    public static boolean validateTel(String tel){
        if(StringUtils.isEmpty(tel)){
            return false;
        }
        Pattern p = Pattern.compile("^((14[5,7])|(17[0,1,6-8])|(13[0-9])|(15[0-3,5-9])|(18[0-3,5-9]))\\d{8}$");
        Matcher m = p.matcher(tel);
        return m.matches();
    }

    public static boolean validateEmail(String email){
        if(StringUtils.isEmpty(email)){
            return false;
        }
        Pattern p = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public static boolean validatePayNo(String payNo){
        if(StringUtils.isEmpty(payNo)){
            return false;
        }
        Pattern p1 = Pattern.compile("^((14[5,7])|(17[0,1,6-8])|(13[0-9])|(15[0-3,5-9])|(18[0-3,5-9]))\\d{8}$");
        Pattern p2 = Pattern.compile("^(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        Matcher m1 = p1.matcher(payNo);
        Matcher m2 = p2.matcher(payNo);
        return (m1.matches() || m2.matches());
    }
}
