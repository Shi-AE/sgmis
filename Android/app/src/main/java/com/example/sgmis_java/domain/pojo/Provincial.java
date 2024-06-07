package com.example.sgmis_java.domain.pojo;

import androidx.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provincial {
    private Long id;
    private String value;

    @NonNull
    @Override
    public String toString() {
        return value;
    }
}
