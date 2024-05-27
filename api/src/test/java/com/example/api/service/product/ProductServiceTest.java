package com.example.api.service.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @DisplayName("카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회한다")
    @Test
    void getLowestPriceByCategory(){
        // given
        // when
        CategoryLowerPriceResponse lowestPriceByCategory = productService.getLowestPriceByCategory();
        // then
        assertThat(lowestPriceByCategory.getTotalPrice()).isEqualTo(34100);
    }

    @DisplayName("카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회한다.")
    @Test
    void getMinMaxProductByCategoryName() {
        // given
        // when
        CategoryMinMaxProductResponse categoryMinMaxProductResponse = productService.getMinMaxProductByCategoryName("상의");
        // then
        assertThat(categoryMinMaxProductResponse.getCategoryName()).isEqualTo("상의");
        assertThat(categoryMinMaxProductResponse.getMinProducts()).hasSize(1);
        assertThat(categoryMinMaxProductResponse.getMinProducts().get(0).getPrice()).isEqualTo(10000);
        assertThat(categoryMinMaxProductResponse.getMaxProducts()).hasSize(1);
        assertThat(categoryMinMaxProductResponse.getMaxProducts().get(0).getPrice()).isEqualTo(11400);
    }

}