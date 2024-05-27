package com.example.api.service.product;


import com.example.api.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryLowerPriceResponse {

    private List<Product> products;

    private Integer totalPrice;

    public Integer getTotalPrice() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(0, Integer::sum);
    }

    public CategoryLowerPriceResponse(List<Product> products) {
        this.products = products;
    }
}
