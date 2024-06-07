package com.example.sgmis_java.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavItemDTO {
    private int icon;
    private String name;
    private Class<?> navTo;
}
