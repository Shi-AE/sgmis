package com.example.sgmis_java.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
