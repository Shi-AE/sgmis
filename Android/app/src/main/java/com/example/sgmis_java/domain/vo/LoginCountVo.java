package com.example.sgmis_java.domain.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginCountVo {
    private String account;
    private Integer count;
    private LocalDate time;
}
