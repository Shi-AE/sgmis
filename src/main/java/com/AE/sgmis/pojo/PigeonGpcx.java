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
@TableName("t_pigeon_gpcx")
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
