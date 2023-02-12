package com.AE.sgmis.service;

import com.AE.sgmis.pojo.User;

/**
 * 登录服务
 */
public interface LoginService {
    /**
     * 登录验证
     */
    void loginVerify(User inputUser);
}
