package com.example.api.service.product;

import com.example.api.entity.product.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryMinMaxProductResponse {

    private final String categoryName;
    private final List<ProductResponse> minProducts;
    private final List<ProductResponse> maxProducts;




    public CategoryMinMaxProductResponse(String categoryName, List<Product> minProducts, List<Product> maxProducts) {
        this.categoryName = categoryName;
        this.minProducts = minProducts.stream().map(data -> new ProductResponse(data.getCategory().getName(), data.getPrice())).toList();
        this.maxProducts = maxProducts.stream().map(data -> new ProductResponse(data.getCategory().getName(), data.getPrice())).toList();
    }

}
