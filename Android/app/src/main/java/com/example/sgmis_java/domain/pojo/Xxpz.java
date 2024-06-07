package com.example.sgmis_java.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统选项配置
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Xxpz {
    private Long id;
    private String name;
    private String author;
    private String type;
    private Long gid;
}
