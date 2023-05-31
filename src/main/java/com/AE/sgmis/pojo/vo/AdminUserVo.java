package com.AE.sgmis.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserVo {
    private Long id;
    private String account;
    private Long gid;
    private Boolean admin;
    /**
     * 在线状态（0为离线，1为离开，2为活跃）
     */
    private Integer state;
}
