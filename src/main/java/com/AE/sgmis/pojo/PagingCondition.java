package com.AE.sgmis.pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PagingCondition {
    private Integer content;
    private String tip;
    private String ringNumber;
    private List<LocalDateTime> timeRange;
    private String author;
    private Integer current;
    private Integer pageSize;
}
