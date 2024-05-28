package com.example.api.repository.product.vo;


import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MinPriceByBrandVO {

    private final Brand brand;

    private final Category category;

    private final Integer price;

    @QueryProjection
    public MinPriceByBrandVO(Brand brand, Category category, Integer price) {
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }
}
