package com.example.api.service.product;

import com.example.api.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryMinMaxProductResponse {

    private final String categoryName;
    private final List<Product> minProducts;
    private final List<Product> maxProducts;

}
