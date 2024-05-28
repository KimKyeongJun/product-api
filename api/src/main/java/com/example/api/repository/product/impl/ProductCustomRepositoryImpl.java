package com.example.api.repository.product.impl;


import com.example.api.entity.product.ProductStatus;
import com.example.api.entity.product.QProduct;
import com.example.api.repository.product.ProductCustomRepository;
import com.example.api.repository.product.vo.MinPriceByBrandVO;
import com.example.api.repository.product.vo.QMinPriceByBrandVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MinPriceByBrandVO> findLowestPriceByBrand() {
        QProduct product = QProduct.product;
        return jpaQueryFactory
                .select(
                        new QMinPriceByBrandVO(
                            product.brand,
                            product.category,
                            product.price.min()
                        )
                )
                .from(product)
                .where(product.status.eq(ProductStatus.ACTIVE))
                .groupBy(product.brand, product.category)
                .fetch();
    }
}
