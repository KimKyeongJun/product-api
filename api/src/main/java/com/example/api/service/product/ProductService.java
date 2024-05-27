package com.example.api.service.product;


import com.example.api.controller.product.request.ProductModifyRequest;
import com.example.api.controller.product.request.ProductRegisterRequest;
import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import com.example.api.entity.product.ProductStatus;
import com.example.api.repository.brand.BrandRepository;
import com.example.api.repository.category.CategoryRepository;
import com.example.api.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * readOnly = true : 읽기전용
 * CRUD 에서 CUD 동작 X / only Read
 * JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
 *
 * CQRS - Command / Read 를 분리
 *
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    public CategoryLowerPriceResponse getLowestPriceByCategory() {
        List<Product> products = productRepository.findLowestPriceByCategory();
        return new CategoryLowerPriceResponse(products);
    }

    public void getMinPriceBrand() {
        List<Product> products = productRepository.findLowestPriceByBrand();

        for(Product product : products) {

            System.out.println(product.getBrand());
        }
    }

    public CategoryMinMaxProductResponse getMinMaxProductByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);

        List<Product> products = productRepository.findByCategory(category);

        Optional<Integer> maxAge = products.stream()
                .map(Product::getPrice)
                .max(Integer::compareTo);

        Optional<Integer> minAge = products.stream()
                .map(Product::getPrice)
                .min(Integer::compareTo);

        List<Product> minProducts = products.stream().filter(p -> p.getPrice() == minAge.orElseThrow()).toList();
        List<Product> maxProducts = products.stream().filter(p -> p.getPrice() == maxAge.orElseThrow()).toList();

        CategoryMinMaxProductResponse categoryMinMaxProductResponse = new CategoryMinMaxProductResponse(category.getName(), minProducts, maxProducts);

        return categoryMinMaxProductResponse;

    }


    @Transactional
    public void createProduct(ProductRegisterRequest productRegisterRequest) {

        Brand brand = brandRepository.findById(productRegisterRequest.getBrandId()).orElseThrow();
        Category category = categoryRepository.findById(productRegisterRequest.getCategoryId()).orElseThrow();
        Product product = productRegisterRequest.toEntity(brand, category);
        productRepository.save(product);
    }

    @Transactional
    public void modifyProduct(Long id, ProductModifyRequest productModifyRequest) {
        Product product = productRepository.findById(id).orElseThrow();
        product.updatePrice(productModifyRequest.getPrice());
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        Long productCount = productRepository.countByCategory(product.getCategory());
        if(productCount == 1) {
            throw new IllegalArgumentException("해당 카테고리에 상품이 하나뿐이라 삭제할 수 없습니다.");
        }

        product.updateStatus(ProductStatus.INACTIVE);
    }
}
