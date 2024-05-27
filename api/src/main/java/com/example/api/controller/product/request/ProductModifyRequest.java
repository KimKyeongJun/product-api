package com.example.api.controller.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProductModifyRequest {

    @NotNull(message = "상품 아이디는 필수 입력값입니다.")
    private Long productId;

    @Positive(message = "상품의 가격은 0보다 커야합니다.")
    private Integer price;

}
