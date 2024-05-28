package com.example.api.controller.product.request;


import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ProductRegisterRequest {

    @NotNull(message = "브랜드 아이디는 필수 입력값입니다.")
    @Schema(name="brandId", defaultValue = "1", description = "브랜드 아이디")
    private Long brandId;

    @NotNull(message = "카테고리 아이디는 필수 입력값입니다.")
    @Schema(name="categoryId", defaultValue = "1", description = "카테고리 아이디")
    private Long categoryId;

    @Positive(message = "상품의 가격은 0보다 커야합니다.")
    @Schema(name="price", defaultValue = "0", description = "가격")
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
