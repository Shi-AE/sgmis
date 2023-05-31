package com.AE.sgmis.interceptor;

import com.AE.sgmis.exceptions.AccessException;
import com.AE.sgmis.util.BlacklistUtil;
import com.AE.sgmis.util.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BlackInterceptor implements HandlerInterceptor {

    @Autowired
    private IpUtil ipUtil;
    @Autowired
    private BlacklistUtil blacklistUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AccessException {
        //获取实际ip，避免使用token中的ip
        String ip = ipUtil.getIp(request);

        //通过ip获取黑名单中的信息
        //如果为空代表黑名单中没有此ip
        Object info = blacklistUtil.getForbiddenInfo(ip);
        if (info != null) {
            //此ip被禁用，返回禁用信息
            throw new AccessException(info.toString());
        }

        return true;
    }
}
