package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("t_pigeon_info")
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
