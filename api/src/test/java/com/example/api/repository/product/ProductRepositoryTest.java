package com.example.api.repository.product;

import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import com.example.api.entity.product.ProductStatus;
import com.example.api.repository.category.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;




    @DisplayName("등록된 카테고리의 상품 갯수를 확인한다")
    @Test
    void selectCategoryCount() {
        // given
        Category category = categoryRepository.findById(1L).orElse(null);
        // when
        List<Product> products = productRepository.findByCategoryAndStatus(category, ProductStatus.ACTIVE);
        // then
        assertThat(products).hasSize(9);
    }

}