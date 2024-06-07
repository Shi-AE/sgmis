package com.example.sgmis_java.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OplogLineDTO {
    private Integer count;
    private LocalDate time;
    private Integer content;
}
