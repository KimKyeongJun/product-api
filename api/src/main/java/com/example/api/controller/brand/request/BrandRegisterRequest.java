package com.example.api.controller.brand.request;


import com.example.api.entity.brand.Brand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandRegisterRequest {

    @NotNull(message = "브랜드명은 필수 입력값입니다.")
    @Schema(name="name", defaultValue = "브랜드명", description = "브랜드명을 입력하세요.")
    private String name;

    public Brand toEntity() {
        return Brand.builder()
                .name(name)
                .build();
    }
}
