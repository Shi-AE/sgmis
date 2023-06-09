package com.AE.sgmis.service;

import com.AE.sgmis.pojo.LoginMsg;
import com.AE.sgmis.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 登录服务
 */
public interface LoginService extends IService<User> {
    /**
     * 登录账号密码
     *
     * @return 用户id
     */
    User loginVerify(User inputUser);

    /**
     * 更新加密结果
     */
    void updateEncrypt(User user);
}
