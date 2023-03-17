package com.AE.sgmis.pojo;

import lombok.Data;

@Data
public class UpdateUserInfo {
    private String account;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
