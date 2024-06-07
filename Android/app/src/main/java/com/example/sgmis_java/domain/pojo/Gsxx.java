package com.example.sgmis_java.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gsxx {
    private Long id;
    private String name;
    private String location;
    private String address;
    private Integer years;
    private String bloodline;
    private String performance;
    private Long gid;
}
