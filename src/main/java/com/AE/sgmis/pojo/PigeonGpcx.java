package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_pigeon_Gpcx")
public class PigeonGpcx {
    /**
     * 自然主键
     */
    private Long id;
    /**
     * 鸽子id
     */
    private Long pid;
    /**
     * 鸽棚巢箱id
     */
    private Long gpcxId;
}
