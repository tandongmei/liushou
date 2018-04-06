package com.ls.interceptor;

import com.ls.exception.ServiceException;
import com.ls.model.User;
import com.ls.model.enm.ResCodeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by tan.dongmei on 2018/2/9
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Access access = method.getAnnotation(Access.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            System.out.println("没有加注解，直接放过");
            return true;
        }
        // 若加了权限注解，则进行判断是否有权限
        HttpSession session = request.getSession(false);
        if(session == null){
            // session已过期
            System.out.println("session过期，不放过");
            //response.sendError(ResCodeEnum.SESSION_TIME_OUT.getCode(),ResCodeEnum.SESSION_TIME_OUT.getMsg());
            //return false;
            throw new ServiceException(ResCodeEnum.SESSION_TIME_OUT.getCode(),ResCodeEnum.SESSION_TIME_OUT.getMsg());
        }else{
            User user = (User) session.getAttribute("userInfo");
            if(user == null){
                // 说明未登录
                System.out.println("未登录，不放过");
                //response.sendError(ResCodeEnum.USER_NOT_LIGIN.getCode(),ResCodeEnum.USER_NOT_LIGIN.getMsg());
                //return false;
                throw new ServiceException(ResCodeEnum.USER_NOT_LIGIN.getCode(),ResCodeEnum.USER_NOT_LIGIN.getMsg());
            }
        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
