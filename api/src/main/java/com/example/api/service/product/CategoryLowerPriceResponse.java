package com.example.api.service.product;


import com.example.api.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
@Setter
public class CategoryLowerPriceResponse {

    private List<ProductResponse> products;

    private Integer totalPrice;

    public Integer getTotalPrice() {
        return products.stream()
                .distinct()
                .map(ProductResponse::getPrice)
                .reduce(0, Integer::sum);
    }

    public CategoryLowerPriceResponse(List<Product> products) {
        this.products = products.stream()
                .filter(distinctByKey(Product::getCategory))
                .map(data -> new ProductResponse(data.getCategory().getName(), data.getPrice()))
                .toList();
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
