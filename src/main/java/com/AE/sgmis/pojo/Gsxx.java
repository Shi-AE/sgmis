package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_gsxx")
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
