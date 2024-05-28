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
import com.example.api.repository.product.vo.MinPriceByBrandVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * readOnly = true : 읽기전용
 * CRUD 에서 CUD 동작 X / only Read
 * JPA : CUD 스냅샷 저장, 변경감지 X (성능 향상)
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

    public MinPriceByBrandResponse getMinPriceByBrand() {
        List<MinPriceByBrandVO> products = productRepository.findLowestPriceByBrand();
        Map<Brand, Integer> totalPricesByBrand = products.stream()
                .collect(Collectors.groupingBy(
                        MinPriceByBrandVO::getBrand, // 그룹핑 기준인 Brand를 추출
                        Collectors.summingInt(MinPriceByBrandVO::getPrice) // 각 그룹의 가격 합계를 계산
                ));

        // 총 가격 중 최소 값을 가진 Brand를 추출
        Optional<Map.Entry<Brand, Integer>> minPriceBrand = totalPricesByBrand.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        Brand brand = minPriceBrand.get().getKey();
        int price = minPriceBrand.get().getValue();
        List<ProductResponse> filteredProducts = products.stream()
                .filter(product -> product.getBrand().equals(brand))
                .map(minPriceByBrandVO -> new ProductResponse(minPriceByBrandVO.getCategory().getName(), minPriceByBrandVO.getPrice()))
                .toList();

        return new MinPriceByBrandResponse(brand.getName(), filteredProducts, price);

    }

    public CategoryMinMaxProductResponse getMinMaxProductByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);

        List<Product> products = productRepository.findByCategoryAndStatus(category, ProductStatus.ACTIVE);

        Optional<Integer> maxAge = products.stream()
                .map(Product::getPrice)
                .max(Integer::compareTo);

        Optional<Integer> minAge = products.stream()
                .map(Product::getPrice)
                .min(Integer::compareTo);

        List<Product> minProducts = products.stream().filter(p -> Objects.equals(p.getPrice(), minAge.orElseThrow())).toList();
        List<Product> maxProducts = products.stream().filter(p -> Objects.equals(p.getPrice(), maxAge.orElseThrow())).toList();

        return new CategoryMinMaxProductResponse(category.getName(), minProducts, maxProducts);
    }


    @Transactional
    public Product createProduct(ProductRegisterRequest productRegisterRequest) {
        Brand brand = brandRepository.findById(productRegisterRequest.getBrandId()).orElseThrow(() -> new NoSuchElementException("등록하려는 브랜드가 존재하지 않습니다."));
        Category category = categoryRepository.findById(productRegisterRequest.getCategoryId()).orElseThrow(() -> new NoSuchElementException("등록하려는 브랜드가 존재하지 않습니다."));
        Product product = productRegisterRequest.toEntity(brand, category);
        return productRepository.save(product);
    }

    @Transactional
    public Product modifyProduct(Long id, ProductModifyRequest productModifyRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
        product.updatePrice(productModifyRequest.getPrice());
        return product;
    }

    @Transactional
    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
        Long productCount = productRepository.countByCategoryAndBrand(product.getCategory(), product.getBrand());
        if(productCount == 1) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 카테고리에 상품이 하나뿐이라 삭제할 수 없습니다.");
        }

        product.updateStatus(ProductStatus.INACTIVE);
        return product;
    }
}
