package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_login_msg")
public class LoginMsg {
    /**
     * 自然主键
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 登录ip
     */
    private String ip;
    /**
     * 登录时间
     */
    private LocalDateTime time;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 设备
     */
    private String device;
    /**
     * 组id
     */
    private Long gid;
}
