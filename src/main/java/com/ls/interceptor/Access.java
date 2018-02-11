package com.ls.interceptor;

import java.lang.annotation.*;

/**
 * Created by tan.dongmei on 2018/2/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
}
