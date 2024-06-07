package com.example.sgmis_java.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingConditionVo {
    private Integer content;
    private String tip;
    private String ringNumber;
    private List<LocalDateTime> timeRange;
    private String author;
    private Integer current;
    private Integer pageSize;
}
