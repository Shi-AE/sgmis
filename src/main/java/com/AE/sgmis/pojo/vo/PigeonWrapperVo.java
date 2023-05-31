package com.AE.sgmis.pojo.vo;

import com.AE.sgmis.pojo.Pigeon;
import com.AE.sgmis.pojo.PigeonInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PigeonWrapperVo {
    private Pigeon pigeon;
    private PigeonInfo pigeonInfo;
    /**
     * 子代id
     */
    private Long oid;
}
