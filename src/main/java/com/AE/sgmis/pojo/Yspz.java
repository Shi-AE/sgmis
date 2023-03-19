package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_yspz")
public class Yspz {
    private Long id;
    private String ys;
    private String author;
}
