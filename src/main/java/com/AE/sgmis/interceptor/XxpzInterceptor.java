package com.AE.sgmis.interceptor;

import com.AE.sgmis.exception.MaliciousSqlInjection;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;


/**
 * 防止选项配置api的sql注入
 */
@Component
public class XxpzInterceptor implements HandlerInterceptor, InitializingBean {
    @Value("${xxpz.tableNames}")
    private String[] names;

    private List<String> tableNames;

    /**
     * 初始化后注入List提高效率
     */
    @Override
    public void afterPropertiesSet() {
        tableNames = Arrays.stream(names).toList();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String servletPath = request.getServletPath();
        String[] path = servletPath.split("/");
        String tableName = path[3];
        if (!tableNames.contains(tableName)) {
            throw new MaliciousSqlInjection("发现恶意sql注入");
            //todo 禁用ip一段时间
        }
        return true;
    }
}
