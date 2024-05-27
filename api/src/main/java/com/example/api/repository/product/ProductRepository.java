package com.example.api.repository.product;


import com.example.api.entity.category.Category;
import com.example.api.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {

    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT MIN(p2.price) FROM Product p2 WHERE p2.category = p.category)")
    List<Product> findLowestPriceByCategory();
}
