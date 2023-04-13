package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_xtspz")
public class Xtspz {
    /**
     * 自然主键
     */
    private Long id;
    /**
     * logo图片的地址
     */
    private String logoUrl;
    /**
     * 鸽舍全称
     */
    private String name;
    /**
     * 鸽舍简称
     */
    private String ShortName;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系邮箱
     */
    private String mail;
    /**
     * 鸽舍网址
     */
    private String url;
    /**
     * 具体地址
     */
    private String address;
    /**
     * 组id
     */
    private Long gid;
}
