package com.example.sgmis_java.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private String shortName;
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
