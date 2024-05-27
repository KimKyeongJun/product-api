package com.example.api.repository.product;

import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findLowestPriceByBrand();

}
