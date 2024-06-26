package com.example.sgmis_java.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserVo {
    private String account;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
