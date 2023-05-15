package com.AE.sgmis.service;

import com.AE.sgmis.pojo.LoginMsg;
import com.baomidou.mybatisplus.extension.service.IService;

public interface LoginMsgService extends IService<LoginMsg> {

    /**
     * 定时清理登录日志信息
     * 并返回删除条数
     */
    int removeForCountTask();
}