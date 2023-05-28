package com.AE.sgmis.interceptor;

import com.AE.sgmis.exceptions.AccessException;
import com.AE.sgmis.pojo.User;
import com.AE.sgmis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取 id，admin
        Map<?, ?> info = (Map<?, ?>) request.getAttribute("info");
        Long id = (Long) info.get("id");
        Boolean admin = (Boolean) info.get("admin");
        //请求头管理员验证
        if (!admin) {
            throw new AccessException("您没有管理员权限，请联系管理员获得权限");
        }
        //数据库管理员验证
        User user = userService.getById(id);
        if (!user.getAdmin()) {
            throw new AccessException("请求头信息异常");
        }
        return true;
    }
}
