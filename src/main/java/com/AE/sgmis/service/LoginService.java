package com.AE.sgmis.service;

import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.User;

import java.util.List;

/**
 * 登录服务
 */
public interface LoginService {
    /**
     * 登录验证
     */
    void loginVerify(User inputUser);

    /**
     * 添加登录信息
     */
    void addLoginMsg(LoginMsg loginMsg);

    /**
     * 获取多条登录信息
     * @return 查询结果
     */
    List<LoginMsg> getLoginMsgList();
}
