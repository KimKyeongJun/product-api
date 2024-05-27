package com.example.api.repository.product.impl;


import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import com.example.api.entity.product.QProduct;
import com.example.api.repository.product.ProductCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.group.GroupBy.min;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Product> findLowestPriceByBrand() {
        QProduct product = QProduct.product;
        return jpaQueryFactory
                .selectFrom(product)
                .groupBy(product.brand, product.category)
                .fetch();
    }
}
