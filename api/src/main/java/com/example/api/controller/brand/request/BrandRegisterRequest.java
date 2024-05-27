package com.example.api.controller.brand.request;


import com.example.api.entity.brand.Brand;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BrandRegisterRequest {

    @NotNull(message = "브랜드명은 필수 입력값입니다.")
    private String name;

    public Brand toEntity() {
        return Brand.builder()
                .name(name)
                .build();
    }
}
