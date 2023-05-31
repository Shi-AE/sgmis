package com.AE.sgmis.pojo;

import lombok.Data;

@Data
public class UpdateUserVo {
    private String account;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
