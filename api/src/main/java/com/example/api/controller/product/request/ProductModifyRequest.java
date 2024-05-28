package com.example.api.controller.product.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ProductModifyRequest {

    @NotNull(message = "상품 아이디는 필수 입력값입니다.")
    @Schema(name="productId", defaultValue = "0", description = "상품 아이디")
    private Long productId;

    @Positive(message = "상품의 가격은 0보다 커야합니다.")
    @Schema(name="price", defaultValue = "0", description = "가격")
    private Integer price;

}
