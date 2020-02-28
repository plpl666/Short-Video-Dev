package com.imooc.controller.interceptor;

import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class MiniInterceptor implements HandlerInterceptor{

    @Autowired
    private RedisOperator redis;

    private static final String USER_REDIS_SESSION = "user-redis-session";

    /**
     * 拦截请求,在controller调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        String userToken = request.getHeader("userToken");
        if (StringUtils.isNoneBlank(userId) && StringUtils.isNoneBlank(userToken)) {
            String uniqueToken = redis.get(USER_REDIS_SESSION + ":" + userId);
            if (StringUtils.isEmpty(uniqueToken) && isBlank(uniqueToken)) {
                returnErrorResponse(response,new IMoocJSONResult().errorTokenMsg("请登录..."));
                return false;
            } else {
                if (!uniqueToken.equals(userToken)) {
                    //账号在其他手机登陆
                    returnErrorResponse(response,new IMoocJSONResult().errorTokenMsg("账号在其他地方登陆..."));
                    return false;
                }
            }
        } else {
            returnErrorResponse(response,new IMoocJSONResult().errorTokenMsg("请登录..."));
            return false;
        }
        return true;
    }

    private void returnErrorResponse(HttpServletResponse response, IMoocJSONResult result) throws IOException {
        OutputStream os = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");
        os = response.getOutputStream();
        os.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
        os.flush();
        os.close();
    }

    /**
     * 请求controller之后,渲染视图之前
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     *  请求controller之后,渲染视图之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
