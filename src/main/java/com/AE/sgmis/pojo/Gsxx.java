package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_gsxx")
public class Gsxx {
    private Long id;
    private String name;
    private String location;
    private Integer years;
    private String bloodLine;
    private String performance;
    //todo 取消uid的区分
    private Long uid;
}
