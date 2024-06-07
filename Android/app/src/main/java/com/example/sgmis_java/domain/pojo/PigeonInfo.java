package com.example.sgmis_java.domain.pojo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PigeonInfo {
    /**
     * 自然主键
     */
    private Long id;
    /**
     * 详细描述
     */
    private String detail;
    /**
     * 鸽子来源
     */
    private String source;
    /**
     * 副足环
     */
    private String subRingNumber;
    /**
     * 入库时间
     */
    private LocalDate createTime;
    /**
     * 鸽子id
     */
    private Long pid;
}
