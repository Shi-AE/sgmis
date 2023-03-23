package com.AE.sgmis.service;

import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.User;

import java.util.List;

/**
 * 登录服务
 */
public interface LoginService {
    /**
     * 登录账号密码
     *
     * @return 用户id
     */
    Long loginVerify(User inputUser);

    /**
     * 更新加密结果
     */
    void updateEncrypt(User user);

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
