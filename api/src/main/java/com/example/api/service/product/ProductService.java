package com.example.api.service.product;


import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import com.example.api.repository.brand.BrandRepository;
import com.example.api.repository.category.CategoryRepository;
import com.example.api.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


}
