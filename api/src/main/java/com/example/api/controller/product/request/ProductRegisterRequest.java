package com.example.api.controller.product.request;


import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProductRegisterRequest {

    @NotNull(message = "브랜드 아이디는 필수 입력값입니다.")
    private Long brandId;

    @NotNull(message = "카테고리 아이디는 필수 입력값입니다.")
    private Long categoryId;

    @Positive(message = "상품의 가격은 0보다 커야합니다.")
    private Integer price;

    public ProductRegisterRequest(Long brandId, Long categoryId, int price) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
    }

    public Product toEntity(Brand brand, Category category) {
        return Product.builder()
                .brand(brand)
                .category(category)
                .price(price)
                .build();
    }
}
