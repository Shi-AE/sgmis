package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_areas")
// todo 修改名字为area
public class Areas {
    @TableId(type = IdType.NONE)
    private Long id;
    private String value;
    private Long uid;
}
