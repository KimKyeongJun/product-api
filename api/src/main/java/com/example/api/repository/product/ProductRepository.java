package com.example.api.repository.product;


import com.example.api.entity.brand.Brand;
import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import com.example.api.entity.product.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {

    List<Product> findByCategoryAndStatus(Category category, ProductStatus status);

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT MIN(p2.price) FROM Product p2 WHERE p2.category = p.category)")
    List<Product> findLowestPriceByCategory();

    Long countByCategory(Category category);

    Long countByCategoryAndBrand(Category category, Brand brand);
}
