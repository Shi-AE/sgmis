package com.example.sgmis_java.domain.dto;

import com.example.sgmis_java.domain.pojo.Oplog;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {
    private Integer pages;
    private Integer current;
    private List<Oplog> records;
}
