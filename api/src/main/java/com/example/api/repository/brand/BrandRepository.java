package com.example.api.repository.brand;


import com.example.api.entity.brand.Brand;
import com.example.api.repository.product.vo.BrandTotalProductPriceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
