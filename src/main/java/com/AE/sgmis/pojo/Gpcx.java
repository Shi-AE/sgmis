package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_gpcx")
public class Gpcx {
    /**
     * 自然主键
     */
    private Long id;
    /**
     * 鸽棚名称
     */
    private String name;
    /**
     * 巢箱数量
     */
    private Integer cxNumber;
    /**
     * 鸽子数量
     */
    private Integer pigeonPopulation;
    /**
     * 组id
     */
    private Long gid;
}
