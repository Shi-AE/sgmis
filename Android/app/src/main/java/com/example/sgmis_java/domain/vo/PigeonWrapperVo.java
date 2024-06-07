package com.example.sgmis_java.domain.vo;


import com.example.sgmis_java.domain.pojo.Pigeon;
import com.example.sgmis_java.domain.pojo.PigeonInfo;

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
