package com.example.api.repository.product.vo;

import com.example.api.entity.brand.Brand;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


@Getter
public class BrandTotalProductPriceVO {

    private final Integer totalPrice;

    private final Brand brand;

    @QueryProjection
    public BrandTotalProductPriceVO(Integer totalPrice, Brand brand) {
        this.totalPrice = totalPrice;
        this.brand = brand;
    }
}
