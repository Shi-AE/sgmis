package com.AE.sgmis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_login_msg")
public class LoginMsg {
    private Long id;
    private String user;
    private String ip;
    private Date date;
}
