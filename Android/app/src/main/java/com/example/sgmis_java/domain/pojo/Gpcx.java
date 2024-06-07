package com.example.sgmis_java.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
