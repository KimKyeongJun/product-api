package com.example.api.controller.brand.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BrandModifyRequest {

    @NotNull(message = "브랜드명은 필수 입력값입니다.")
    private String name;

    @NotNull(message = "브랜드 아이디는 필수 입력값입니다.")
    Long brandId;

}
