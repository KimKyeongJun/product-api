package com.example.api.repository.product;

import com.example.api.repository.product.vo.MinPriceByBrandVO;

import java.util.List;

public interface ProductCustomRepository {
    List<MinPriceByBrandVO> findLowestPriceByBrand();

}
