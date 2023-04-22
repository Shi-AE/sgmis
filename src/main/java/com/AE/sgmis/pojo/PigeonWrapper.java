package com.AE.sgmis.pojo;

import lombok.Data;

@Data
public class PigeonWrapper {
    private Pigeon pigeon;
    private PigeonInfo pigeonInfo;
    /**
     * 子代id
     */
    private Long oid;
}
