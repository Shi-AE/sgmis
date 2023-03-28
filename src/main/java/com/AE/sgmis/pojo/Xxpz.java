package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 系统选项配置
 */
@Data
@TableName("t_xxpz")
public class Xxpz {
    private Long id;
    private String name;
    private String author;
    private String type;
    private Long gid;
}
