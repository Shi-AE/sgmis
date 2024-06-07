package com.example.sgmis_java.domain.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
