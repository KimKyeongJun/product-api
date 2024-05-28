package com.example.api.service.product;

import com.example.api.controller.product.request.ProductModifyRequest;
import com.example.api.controller.product.request.ProductRegisterRequest;
import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import com.example.api.entity.product.ProductStatus;
import com.example.api.exception.BusinessException;
import com.example.api.repository.brand.BrandRepository;
import com.example.api.repository.category.CategoryRepository;
import com.example.api.repository.product.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

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

    @DisplayName("상품을 등록한다.")
    @Test
    void createProduct() {
        // given
        Brand brand = brandRepository.findById(1L).orElse(null);
        Category category = categoryRepository.findById(1L).orElse(null);
        ProductRegisterRequest productRegisterRequest = new ProductRegisterRequest(brand.getId(), category.getId(), 10000);
        // when
        Product product = productService.createProduct(productRegisterRequest);
        // then
        assertThat(product.getId()).isNotNull();
        assertThat(product.getPrice()).isEqualTo(10000);
    }

    @DisplayName("상품을 수정한다.")
    @Test
    void modifyProduct() {
        // given
        Long productId = 1L;
        ProductModifyRequest productModifyRequest = new ProductModifyRequest(productId, 20000);
        // when
        Product product = productService.modifyProduct(productId, productModifyRequest);
        // then
        assertThat(product.getPrice()).isEqualTo(20000);
    }


    @DisplayName("존재하지 않는 상품을 수정하면 에러가 발생한다.")
    @Test
    void modifyNonExistProduct() {
        // given
        Long productId = 100L;
        ProductModifyRequest productModifyRequest = new ProductModifyRequest(productId, 20000);
        // when
        // then
        assertThatThrownBy(() -> productService.modifyProduct(productId, productModifyRequest))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품이 존재하지 않습니다.");
    }


    @DisplayName("상품을 삭제한다.")
    @Test
    void deleteProduct() {
        // given
        Long productId = 1L;

        Brand brand = brandRepository.findById(1L).orElse(null);
        Category category = categoryRepository.findById(1L).orElse(null);
        productRepository.save(Product.builder()
                .brand(brand)
                .category(category)
                .price(10000)
                .status(ProductStatus.ACTIVE)
                .build());
        // when
        Product product = productService.deleteProduct(productId);
        // then
        assertThat(product.getStatus()).isEqualTo(ProductStatus.INACTIVE);
    }

    @DisplayName("카테고리에 1개의 상품만 존재한다면 상품을 삭제할 수 없다.")
    @Test
    void deleteProductOneProductExist() {
        // given
        Long productId = 1L;
        // when
        // then
        assertThatThrownBy(() -> productService.deleteProduct(productId))
                .isInstanceOf(BusinessException.class)
                .hasMessage("해당 카테고리에 상품이 하나뿐이라 삭제할 수 없습니다.");
    }

    @DisplayName("존재하지 않는 상품을 삭제하면 에러가 발생한다.")
    @Test
    void deleteProductNonExist() {
        // given
        Long productId = 100L;
        // when
        // then
        assertThatThrownBy(() -> productService.deleteProduct(productId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품이 존재하지 않습니다.");
    }

}